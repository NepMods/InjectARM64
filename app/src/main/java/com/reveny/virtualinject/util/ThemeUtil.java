package com.reveny.virtualinject.util;

import android.content.SharedPreferences;

import androidx.annotation.StyleRes;

import com.google.android.material.color.DynamicColors;

import com.reveny.virtualinject.App;
import com.reveny.virtualinject.R;

import java.util.HashMap;
import java.util.Map;

public class ThemeUtil {
    private static final Map<String, Integer> colorThemeMap = new HashMap<>();
    private static final SharedPreferences preferences = App.getPreferences();

    static {
        colorThemeMap.put("MATERIAL_INDIGO", R.style.ThemeOverlay_MaterialSakura);
    }
    public static boolean isSystemAccent() {
        return DynamicColors.isDynamicColorAvailable() && preferences.getBoolean("follow_system_accent", true);
    }
    public static String getColorTheme() {
        return isSystemAccent() ? "SYSTEM" : preferences.getString("theme_color", "COLOR_SAKURA");
    }
    @StyleRes
    public static int getColorThemeStyleRes() {
        return colorThemeMap.getOrDefault(getColorTheme(), R.style.ThemeOverlay_MaterialSakura);
    }
}