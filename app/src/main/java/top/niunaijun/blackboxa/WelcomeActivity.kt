package top.niunaijun.blackboxa

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.android.support.Main
import top.niunaijun.blackbox.BlackBoxCore

class WelcomeActivity : Activity() {
    var package_name = "com.mobirix.zombiefire";
    var user_id = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && !android.provider.Settings.canDrawOverlays(this))
        {
            Toast.makeText(
                this.getApplicationContext(),
                "Overlay permission is required in order to show mod menu. Restart the game after you allow permission",
                Toast.LENGTH_LONG
            ).show()
            Toast.makeText(
                this.getApplicationContext(),
                "Overlay permission is required in order to show mod menu. Restart the game after you allow permission",
                Toast.LENGTH_LONG
            ).show()
            this.startActivity(
                Intent(
                    "android.settings.action.MANAGE_OVERLAY_PERMISSION",
                    Uri.parse("package:" + this.getPackageName())
                )
            )
            val handler = Handler()
            handler.postDelayed({ System.exit(1) }, 5000)
            return
        }
        jump()
    }

    private fun jump() {
        var hasApp = BlackBoxCore.get().isInstalled(package_name, user_id);
        if(!hasApp) {
            BlackBoxCore.get().installPackageAsUser(package_name, user_id);
        }
        BlackBoxCore.get().launchApk(package_name, user_id);
        Main.Start(this);
    }
}