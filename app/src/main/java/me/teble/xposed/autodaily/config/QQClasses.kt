package me.teble.xposed.autodaily.config

interface QQClasses {
    companion object {
        // Activity
        const val AppActivity = "mqq.app.AppActivity"
        const val SplashActivity = "com.tencent.mobileqq.activity.SplashActivity"
        const val QQSettingSettingActivity = "com.tencent.mobileqq.activity.QQSettingSettingActivity"
        const val NewRunTime = "com.tencent.mobileqq.startup.step.NewRuntime"
        const val LoadData = "com.tencent.mobileqq.startup.step.LoadData"
        const val LoadDex = "com.tencent.mobileqq.startup.step.LoadDex"
        const val LoadModule = "com.tencent.mobileqq.startup.step.LoadModule"
        const val NewRuntime = "com.tencent.mobileqq.startup.step.NewRuntime"
        const val AppRuntime = "mqq.app.AppRuntime"
        const val MobileQQ = "mqq.app.MobileQQ"
        const val BaseApplicationImpl = "com.tencent.common.app.BaseApplicationImpl"
        const val QQAppInterface = "com.tencent.mobileqq.app.QQAppInterface"
        const val BaseApplication = "com.tencent.qphone.base.util.BaseApplication"
        const val ThirdAppReportImpl = "com.tencent.common.app.ThirdAppReportImpl"
        const val TicketManagerImpl = "mqq.app.TicketManagerImpl"
        const val QLog = "com.tencent.qphone.base.util.QLog"
        const val SimpleAccount = "com.tencent.qphone.base.remote.SimpleAccount"
        const val FormSimpleItem = "com.tencent.mobileqq.widget.FormSimpleItem"
        const val DialogUtil = "com.tencent.mobileqq.utils.DialogUtil"
        const val QQCustomDialog = "com.tencent.mobileqq.utils.QQCustomDialog"
        const val MsfService = "com.tencent.mobileqq.msf.service.MsfService"
        const val QFixApplication = "com.tencent.mobileqq.qfix.QFixApplication"
        const val QFixApplicationImpl = "com.tencent.common.app.QFixApplicationImpl"
        const val VisitorsActivity = "com.tencent.mobileqq.activity.VisitorsActivity"
        const val Friends = "com.tencent.mobileqq.data.Friends"
        const val TroopManager = "com.tencent.mobileqq.app.TroopManager"
        const val TroopInfo = "com.tencent.mobileqq.data.TroopInfo"
        const val AccountObserver = "mqq.observer.AccountObserver"
        const val NotificationActivity = "com.tencent.mobileqq.activity.NotificationActivity"
        const val FromServiceMsg = "com.tencent.qphone.base.remote.FromServiceMsg"
        const val StQWebRsp = "NS_QWEB_PROTOCAL.PROTOCAL\$StQWebRsp"
        const val StGetProfileRsp = "NS_MINI_INTERFACE.INTERFACE\$StGetProfileRsp"
        const val StApiUserInfo = "NS_MINI_INTERFACE.INTERFACE\$StApiUserInfo"
        const val StGetCodeRsp = "NS_MINI_INTERFACE.INTERFACE\$StGetCodeRsp"
        const val GuardInstructionDialog = "com.tencent.mobileqq.minigame.manager.GameGrowthGuardianManager\$GuardInstructionDialog"
        const val GetLoginCodeRequest = "com.tencent.mobileqq.mini.servlet.GetLoginCodeRequest"
        const val GetProfileRequest = "com.tencent.mobileqq.mini.servlet.GetProfileRequest"
        const val SendMenuEventRequest = "com.tencent.mobileqq.mp.mobileqq_mp\$SendMenuEventRequest"
        const val Packet = "mqq.app.Packet"
        const val MiniAppGetLoginCodeServlet = "com.tencent.mobileqq.mini.servlet.MiniAppGetLoginCodeServlet"
        const val MiniAppGetProfileServlet = "com.tencent.mobileqq.mini.servlet.MiniAppGetProfileServlet"
        const val MsfServiceSdk = "com.tencent.mobileqq.msf.sdk.MsfServiceSdk"
        const val ToServiceMsg = "com.tencent.qphone.base.remote.ToServiceMsg"
        const val CardHandler = "com.tencent.mobileqq.app.CardHandler"
        const val FriendsManager = "com.tencent.mobileqq.app.FriendsManager"
        const val MessengerService = "com.tencent.mobileqq.emosm.web.MessengerService"
        const val CoreService = "com.tencent.mobileqq.app.CoreService"
        const val SessionInfo = "com.tencent.mobileqq.activity.aio.SessionInfo"
        const val BaseSessionInfo = "com.tencent.mobileqq.activity.aio.BaseSessionInfo"
        const val ChatActivityFacade = "com.tencent.mobileqq.activity.ChatActivityFacade"
        const val CameraPreviewActivity = "com.tencent.mobileqq.activity.photo.CameraPreviewActivity"

        const val StQWebReq = "NS_QWEB_PROTOCAL.PROTOCAL\$StQWebReq"

        // OIDB
        // Group sign in
        const val ReqBody = "tencent.im.oidb.cmd0xeb7.oidb_0xeb7\$ReqBody"
        const val StSignInWriteReq = "tencent.im.oidb.cmd0xeb7.oidb_0xeb7\$StSignInWriteReq"
        const val StSignInStatusReq = "tencent.im.oidb.cmd0xeb7.oidb_0xeb7\$StSignInStatusReq"
        const val OIDBSSOPkg = "tencent.im.oidb.oidb_sso\$OIDBSSOPkg"
        const val ByteStringMicro = "com.tencent.mobileqq.pb.ByteStringMicro"
        const val TroopClockInHandler = "com.tencent.mobileqq.troop.clockin.handler.TroopClockInHandler"

        // TIM 2.3.1
        const val FormCommonSingleLineItem = "com.tencent.mobileqq.widget.FormCommonSingleLineItem"
    }
}