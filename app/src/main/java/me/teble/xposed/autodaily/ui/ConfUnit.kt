package me.teble.xposed.autodaily.ui

import me.teble.xposed.autodaily.hook.config.Config.accountConfig
import me.teble.xposed.autodaily.hook.config.Config.xaConfig
import me.teble.xposed.autodaily.task.model.Task
import me.teble.xposed.autodaily.task.model.VersionInfo
import me.teble.xposed.autodaily.task.util.Const.BLOCK_UPDATE_ONE_DAY
import me.teble.xposed.autodaily.task.util.Const.CONFIG_VERSION
import me.teble.xposed.autodaily.task.util.Const.ENABLE
import me.teble.xposed.autodaily.task.util.Const.ENV_VARIABLE
import me.teble.xposed.autodaily.task.util.Const.GLOBAL_ENABLE
import me.teble.xposed.autodaily.task.util.Const.LAST_EXEC_MSG
import me.teble.xposed.autodaily.task.util.Const.LAST_EXEC_TIME
import me.teble.xposed.autodaily.task.util.Const.NEED_SHOW_LOG
import me.teble.xposed.autodaily.task.util.Const.NEXT_SHOULD_EXEC_TIME
import me.teble.xposed.autodaily.task.util.Const.SHOW_TASK_TOAST
import me.teble.xposed.autodaily.task.util.Const.TASK_EXCEPTION_COUNT
import me.teble.xposed.autodaily.task.util.Const.USED_THREAD_POOL

object ConfUnit {
    var needUpdate: Boolean = false
    var lastFetchTime: Long = 0
    var versionInfoCache: VersionInfo? = null

    // -------------------------------------------------- //
    var configVersion: Int
        get() = xaConfig.getInt(CONFIG_VERSION, 1)
        set(value) = xaConfig.putInt(CONFIG_VERSION, value)
    var blockUpdateOneDay: String?
        get() = xaConfig.getString(BLOCK_UPDATE_ONE_DAY)
        set(value) = xaConfig.putString(BLOCK_UPDATE_ONE_DAY, value)
    var needShowUpdateLog: Boolean
        get() = xaConfig.getBoolean(NEED_SHOW_LOG, false)
        set(value) = xaConfig.putBoolean(NEED_SHOW_LOG, value)
    var usedThreadPool: Boolean
        get() = xaConfig.getBoolean(USED_THREAD_POOL, true)
        set(value) = xaConfig.putBoolean(USED_THREAD_POOL, value)
    var showTaskToast: Boolean
        get() = xaConfig.getBoolean(SHOW_TASK_TOAST, true)
        set(value) = xaConfig.putBoolean(SHOW_TASK_TOAST, value)

    // -------------------------------------------------- //
    var globalEnable: Boolean
        get() = accountConfig.getBoolean(GLOBAL_ENABLE, false)
        set(value) = accountConfig.putBoolean(GLOBAL_ENABLE, value)
}

// -------------------------------------------------- //
var Task.enable: Boolean
    get() = accountConfig.getBoolean("${this.id}#${ENABLE}", false)
    set(value) = accountConfig.putBoolean("${this.id}#${ENABLE}", value)

var Task.lastExecTime: String?
    get() = accountConfig.getString("${this.id}#${LAST_EXEC_TIME}")
    set(value) = accountConfig.putString("${this.id}#${LAST_EXEC_TIME}", value)

var Task.nextShouldExecTime: String?
    get() = accountConfig.getString("${this.id}#${NEXT_SHOULD_EXEC_TIME}")
    set(value) = accountConfig.putString("${this.id}#${NEXT_SHOULD_EXEC_TIME}", value)

var Task.lastExecMsg: String?
    get() = accountConfig.getString("${this.id}#${LAST_EXEC_MSG}")
    set(value) = accountConfig.putString("${this.id}#${LAST_EXEC_MSG}", value)

var Task.taskExceptionFlag: String?
    get() = accountConfig.getString("${this.id}#${TASK_EXCEPTION_COUNT}")
    set(value) = accountConfig.putString("${this.id}#${TASK_EXCEPTION_COUNT}", value)

fun Task.getVariable(name: String, default: String): String
    = accountConfig.getString("${this.id}#${ENV_VARIABLE}#${name}", default)

fun Task.setVariable(name: String, value: String?)
    = accountConfig.putString("${this.id}#${ENV_VARIABLE}#${name}", value)