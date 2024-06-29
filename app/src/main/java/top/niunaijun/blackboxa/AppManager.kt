package top.niunaijun.blackboxa

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object AppManager {
    @JvmStatic
    val mLoader by lazy {
        Loader()
    }

    @JvmStatic
    val mBlackBoxCore by lazy {
        mLoader.getBlackBoxCore()
    }

    @JvmStatic
    val mRemarkSharedPreferences: SharedPreferences by lazy {
        App.getContext().getSharedPreferences("UserRemark",Context.MODE_PRIVATE)
    }

    fun doAttachBaseContext(context: Context) {
        try {
            Log.d("nfh", "AppManager.attachBaseContext")
            mLoader.attachBaseContext(context)
            mLoader.addLifecycleCallback()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun doOnCreate(context: Context) {
        Log.d("nfh", "AppManager.doOnCreate")
        mLoader.doOnCreate(context)
        initThirdService(context)
    }

    private fun initThirdService(context: Context) {}
}
