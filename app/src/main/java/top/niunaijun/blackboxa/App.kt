package top.niunaijun.blackboxa

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Process
import android.util.Log

/**
 *
 * @Description:
 * @Author: wukaicheng
 * @CreateDate: 2021/4/29 21:21
 */
class App : Application() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private lateinit var mContext: Context

        @JvmStatic
        fun getContext(): Context {
            return mContext
        }
    }

    override fun attachBaseContext(base: Context?) {
        Log.d("nfh", "App.attachBaseContext: " + Process.myPid())
//        if (processName.endsWith("p0")) {
//                Log.d("nfh", processName);
//                android.os.Debug.waitForDebugger();
//        }
        super.attachBaseContext(base)
        mContext = base!!
        AppManager.doAttachBaseContext(base)

    }

    override fun onCreate() {
        Log.d("nfh", "App.onCreate")
        super.onCreate()
        AppManager.doOnCreate(mContext)
    }
}