package com.reveny.virtualinject.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.DialogFragment;

import com.android.support.Main;
import com.reveny.virtualinject.BuildConfig;
import com.reveny.virtualinject.R;
import com.reveny.virtualinject.databinding.DialogAboutBinding;
import com.reveny.virtualinject.databinding.FragmentHomeBinding;
import com.reveny.virtualinject.ui.dialog.BlurBehindDialogBuilder;
import com.reveny.virtualinject.util.Utility;
import com.reveny.virtualinject.util.chrome.LinkTransformationMethod;
import com.vcore.BlackBoxCore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

import rikka.material.app.LocaleDelegate;

public class HomeFragment extends BaseFragment {
    private static final String TAG = "VirtualInjectLog";

    private String selectedApp = "com.noodlecake.altosadventure";
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.about).setOnMenuItemClickListener(item -> {
            showAbout();
            return true;
        });
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.appBar.setLiftable(true);
        binding.nestedScrollView.getBorderViewDelegate().setBorderVisibilityChangedListener((top, oldTop, bottom, oldBottom) -> binding.appBar.setLifted(!top));
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);


        binding.installButton.setOnClickListener(v -> {
            if (selectedApp != null) {

                boolean isInstalled = BlackBoxCore.get().isInstalled(selectedApp, 0);
                if (!isInstalled) {

                    BlackBoxCore.get().installPackageAsUser(selectedApp, 0);
                    isInstalled = BlackBoxCore.get().isInstalled(selectedApp, 0);
                    Log.i(TAG, "isInstalled: " + isInstalled);
                    if (!isInstalled) {
                        Toast.makeText(requireContext(), "Failed to install", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Log.i(TAG, "Launching: " + selectedApp);
                BlackBoxCore.get().launchApk(selectedApp, 0);
                Main.Start(requireContext());
            } else {
                Toast.makeText(requireContext(), "Please select a valid app and library path", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }


    public static class AboutDialog extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            DialogAboutBinding binding = DialogAboutBinding.inflate(getLayoutInflater(), null, false);
            setupAboutDialog(binding);
            return new BlurBehindDialogBuilder(requireContext()).setView(binding.getRoot()).create();
        }

        private void setupAboutDialog(DialogAboutBinding binding) {
            binding.designAboutTitle.setText(R.string.app_name);
            binding.designAboutInfo.setMovementMethod(LinkMovementMethod.getInstance());
            binding.designAboutInfo.setTransformationMethod(new LinkTransformationMethod(requireActivity()));
            binding.designAboutInfo.setText(HtmlCompat.fromHtml(getString(
                    R.string.about_view_source_code,
                    "<b><a href=\"https://t.me/revenyy\">Telegram</a></b>",
                    "<b><a href=\"https://github.com/reveny/\">Reveny</a></b>"), HtmlCompat.FROM_HTML_MODE_LEGACY));
            binding.designAboutVersion.setText(String.format(LocaleDelegate.getDefaultLocale(), "%s (%d)", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
        }
    }


    private void showAbout() {
        // Showing the About Dialog
        new AboutDialog().show(getChildFragmentManager(), "about");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
