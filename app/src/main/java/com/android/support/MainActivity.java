package com.android.support;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

import com.android.support.Main;

import com.vcore.BlackBoxCore;

public class MainActivity extends Activity {

    private String packageName = "com.noodlecake.altosadventure";
    private int userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Toast.makeText(
                    getApplicationContext(),
                    "Overlay permission is required in order to show mod menu. Restart the game after you allow permission",
                    Toast.LENGTH_LONG
            ).show();
            Toast.makeText(
                    getApplicationContext(),
                    "Overlay permission is required in order to show mod menu. Restart the game after you allow permission",
                    Toast.LENGTH_LONG
            ).show();

            // Exit the application after 5 seconds
            new Handler().postDelayed(() -> System.exit(1), 5000);
            return;
        }

        jump();
    }

    private void jump() {
        boolean hasApp = BlackBoxCore.get().isInstalled(packageName, userId);
        if (!hasApp) {
            BlackBoxCore.get().installPackageAsUser(packageName, userId);
        }
        BlackBoxCore.get().launchApk(packageName, userId);
        Main.Start(this);
    }
}
