#include <dex_kit.h>
#include "dex_kit_jni_helper.h"
#include "log.h"

extern "C"
JNIEXPORT jlong JNICALL
Java_me_teble_xposed_autodaily_dexkit_DexKitHelper_initDexKit(JNIEnv *env, jobject thiz,
                                                              jobject class_loader) {
    if (!class_loader) {
        return 0;
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
    std::string hostApkPath = filePathStr.substr(5, filePathStr.size() - 26);
    LOGI("hostApkPath: %s", hostApkPath.c_str());
    auto dexkit = new dexkit::DexKit(hostApkPath);
    env->ReleaseStringUTFChars(file, cStr);
    return (jlong) dexkit;
}

extern "C"
JNIEXPORT void JNICALL
Java_me_teble_xposed_autodaily_dexkit_DexKitHelper_release(JNIEnv *env, jobject thiz, jlong token) {
    if (!token) {
        return;
    }
    ReleaseDexKitInstance(env, token);
}

extern "C"
JNIEXPORT jobject JNICALL
Java_me_teble_xposed_autodaily_dexkit_DexKitHelper_batchFindClassesUsedStrings(JNIEnv *env,
                                                                               jobject thiz,
                                                                               jlong token,
                                                                               jobject map,
                                                                               jboolean advanced_match,
                                                                               jintArray dex_priority) {
    return BatchFindClassesUsingStrings(env, token, map, advanced_match, dex_priority);
}

extern "C"
JNIEXPORT jobject JNICALL
Java_me_teble_xposed_autodaily_dexkit_DexKitHelper_batchFindMethodsUsedStrings(JNIEnv *env,
                                                                               jobject thiz,
                                                                               jlong token,
                                                                               jobject map,
                                                                               jboolean advanced_match,
                                                                               jintArray dex_priority) {
    return BatchFindMethodsUsingStrings(env, token, map, advanced_match, dex_priority);
}

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_me_teble_xposed_autodaily_dexkit_DexKitHelper_findMethodBeInvoked(JNIEnv *env, jobject thiz,
                                                                       jlong token,
                                                                       jstring method_descriptor,
                                                                       jstring method_declare_class,
                                                                       jstring method_declare_name,
                                                                       jstring method_return_type,
                                                                       jobjectArray method_param_types,
                                                                       jstring caller_method_declare_class,
                                                                       jstring caller_method_declare_name,
                                                                       jstring caller_method_return_type,
                                                                       jobjectArray caller_method_param_types,
                                                                       jintArray dex_priority) {
    return FindMethodCaller(env, token, method_descriptor, method_declare_class,
                            method_declare_name, method_return_type, method_param_types,
                            caller_method_declare_class, caller_method_declare_name,
                            caller_method_return_type, caller_method_param_types, dex_priority);
}

extern "C"
JNIEXPORT jobject JNICALL
Java_me_teble_xposed_autodaily_dexkit_DexKitHelper_findMethodInvoking(JNIEnv *env, jobject thiz,
                                                                      jlong token,
                                                                      jstring method_descriptor,
                                                                      jstring method_declare_class,
                                                                      jstring method_declare_name,
                                                                      jstring method_return_type,
                                                                      jobjectArray method_param_types,
                                                                      jstring be_called_method_declare_class,
                                                                      jstring be_called_method_declare_name,
                                                                      jstring be_called_method_return_type,
                                                                      jobjectArray be_called_method_param_types,
                                                                      jintArray dex_priority) {
    return FindMethodInvoking(env, token, method_descriptor, method_declare_class,
                              method_declare_name, method_return_type, method_param_types,
                              be_called_method_declare_class, be_called_method_declare_name,
                              be_called_method_return_type, be_called_method_param_types,
                              dex_priority);
}

extern "C"
JNIEXPORT jobject JNICALL
Java_me_teble_xposed_autodaily_dexkit_DexKitHelper_findMethodUsedField(JNIEnv *env, jobject thiz,
                                                                       jlong token,
                                                                       jstring field_descriptor,
                                                                       jstring field_declare_class,
                                                                       jstring field_name,
                                                                       jstring field_type,
                                                                       jint used_flags,
                                                                       jstring caller_method_declare_class,
                                                                       jstring caller_method_name,
                                                                       jstring caller_method_return_type,
                                                                       jobjectArray caller_method_param_types,
                                                                       jintArray dex_priority) {
    return FindMethodUsingField(env, token, field_descriptor, field_declare_class, field_name,
                                field_type, used_flags, caller_method_declare_class,
                                caller_method_name, caller_method_return_type,
                                caller_method_param_types, dex_priority);
}

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_me_teble_xposed_autodaily_dexkit_DexKitHelper_findMethodUsedString(JNIEnv *env, jobject thiz,
                                                                        jlong token,
                                                                        jstring used_string,
                                                                        jboolean advanced_match,
                                                                        jstring method_declare_class,
                                                                        jstring method_name,
                                                                        jstring method_return_type,
                                                                        jobjectArray method_param_types,
                                                                        jintArray dex_priority) {
    return FindMethodUsingString(env, token, used_string, advanced_match, method_declare_class,
                                 method_name, method_return_type, method_param_types, dex_priority);
}

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_me_teble_xposed_autodaily_dexkit_DexKitHelper_findMethod(JNIEnv *env, jobject thiz,
                                                              jlong token,
                                                              jstring method_declare_class,
                                                              jstring method_name,
                                                              jstring method_return_type,
                                                              jobjectArray method_param_types,
                                                              jintArray dex_priority) {
    return FindMethod(env, token, method_declare_class, method_name, method_return_type,
                      method_param_types, dex_priority);
}

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_me_teble_xposed_autodaily_dexkit_DexKitHelper_findSubClasses(JNIEnv *env, jobject thiz,
                                                                  jlong token, jstring parent_class,
                                                                  jintArray dex_priority) {
    return FindSubClasses(env, token, parent_class, dex_priority);
}

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_me_teble_xposed_autodaily_dexkit_DexKitHelper_findMethodOpPrefixSeq(JNIEnv *env, jobject thiz,
                                                                         jlong token,
                                                                         jintArray op_prefix_seq,
                                                                         jstring method_declare_class,
                                                                         jstring method_name,
                                                                         jstring method_return_type,
                                                                         jobjectArray method_param_types,
                                                                         jintArray dex_priority) {
    return FindMethodOpPrefixSeq(env, token, op_prefix_seq, method_declare_class, method_name,
                                 method_return_type, method_param_types, dex_priority);
}