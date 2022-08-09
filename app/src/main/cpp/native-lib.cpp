#include <jni.h>
#include <iostream>
#include "log.h"
#include "v2sign.cpp"
#include "dex_kit.h"
#include <map>
#include <zlib.h>
#include <sys/mman.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <list>
//
// Created by teble on 2020/2/10.
//
namespace {
#define EXPORT extern "C" __attribute__((visibility("default")))
extern "C" jint MMKV_JNI_OnLoad(JavaVM *vm, void *reserved);

std::string hostApkPath;

EXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env = nullptr;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    }
    // 模块签名不正确拒绝加载 jni
    if (checkSignature(env) != JNI_TRUE) {
        return -2;
    }
    return MMKV_JNI_OnLoad(vm, reserved);
}

EXPORT void
Java_me_teble_xposed_autodaily_task_util_ConfigUtil_findDex(
        JNIEnv *env, jobject obj, jobject class_loader) {
    if (!class_loader) {
        return;
    }
    jclass cClassloader = env->FindClass("java/lang/ClassLoader");
    jmethodID mGetResource = env->GetMethodID(cClassloader, "findResource",
                                              "(Ljava/lang/String;)Ljava/net/URL;");
    jstring manifestPath = env->NewStringUTF("AndroidManifest.xml");
    jobject url = env->CallObjectMethod(class_loader, mGetResource, manifestPath);
    jclass cURL = env->FindClass("java/net/URL");
    jmethodID mGetPath = env->GetMethodID(cURL, "getPath", "()Ljava/lang/String;");
    auto file = (jstring) env->CallObjectMethod(url, mGetPath);
    const char *cStr = env->GetStringUTFChars(file, nullptr);
    std::string filePathStr(cStr);
    hostApkPath = filePathStr.substr(5, filePathStr.size() - 26);
    LOGD("host apk path -> %s", hostApkPath.c_str());
    env->ReleaseStringUTFChars(file, cStr);
    map<string_view, set<string_view>> obfuscate = {
            {"Lcom/tencent/mobileqq/activity/ChatActivityFacade;",               {"reSendEmo"}},
            {"Lcooperation/qzone/PlatformInfor;",                                {"52b7f2", "qimei"}},
            {"Lcom/tencent/mobileqq/troop/clockin/handler/TroopClockInHandler;", {"TroopClockInHandler"}},
            {"test",                                                             {"mark_uin_upload"}},
    };

    auto now = std::chrono::system_clock::now();
    auto now_ms = std::chrono::duration_cast<std::chrono::milliseconds>(now.time_since_epoch());

    dexkit::DexKit dexKit(hostApkPath);
    auto res = dexKit.LocationClasses(obfuscate);
    for (auto &[key, value]: res) {
        LOGI("%s -> \n", key.data());
        for (auto &v: value) {
            LOGI("\t%s\n", v.data());
        }
    }

    auto now1 = std::chrono::system_clock::now();
    auto now_ms1 = std::chrono::duration_cast<std::chrono::milliseconds>(now1.time_since_epoch());
    LOGI("used time: %lld ms", now_ms1.count() - now_ms.count());
}

}