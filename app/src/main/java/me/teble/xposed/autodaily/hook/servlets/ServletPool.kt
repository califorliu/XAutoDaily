package me.teble.xposed.autodaily.hook.servlets

import me.teble.xposed.autodaily.hook.utils.QApplicationUtil.appRuntime
import me.teble.xposed.autodaily.utils.fieldValueAs
import me.teble.xposed.autodaily.utils.invoke
import mqq.app.Servlet
import mqq.app.ServletContainer
import java.util.concurrent.ConcurrentHashMap

object ServletPool {

    private val servletArray = arrayOf<Class<out Servlet>>(
        FavoriteServlet::class.java,
    )

    private val servletMap = ConcurrentHashMap<Class<out Servlet>, Servlet>()

    fun injectServlet() {
        val servletContainer = appRuntime.invoke("getServletContainer") as ServletContainer
        val managedServlet = servletContainer
            .fieldValueAs<ConcurrentHashMap<String, Servlet>>("managedServlet")!!
        for (servletClass in servletArray) {
            val servlet = servletClass.newInstance() as Servlet
            servlet.invoke("init", appRuntime, servletContainer)
            servlet.invoke("onCreate")
            managedServlet[servlet::class.java.name] = servlet
            servletMap[servlet::class.java] = servlet
        }
    }

    @Synchronized
    @Suppress("UNCHECKED_CAST")
    fun <T : Servlet> getServlet(servletClass: Class<T>): T {
        return servletMap[servletClass] as T
    }

    val favoriteServlet: FavoriteServlet by lazy { getServlet(FavoriteServlet::class.java) }
}

