package com.reveny.virtualinject;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vcore.BlackBoxCore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Use package name to install provided that application has been install on device
        BlackBoxCore.get().installPackageAsUser("com.pixel.gun3d", 0);

        boolean isInstalled = BlackBoxCore.get().isInstalled("com.pixel.gun3d", 0);
        Log.i("VirtualInjectLog", "isInstalled: " + isInstalled);

        if (isInstalled) {
            // BlackBoxCore.get().launchApk("com.pixel.gun3d", 0);
        }
    }
}