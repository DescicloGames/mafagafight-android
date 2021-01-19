package net.eduapps.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;

public class ScreenUtils {
    /**
     * retorna uma string contendo o tipo de DPI da tela.
     *
     * @param context
     * @return
     */
    public static String getDeviceDensity(Context context) {
        String deviceDensity = "";
        int dpi = context.getResources().getDisplayMetrics().densityDpi;
        System.out.println("DENSIDADE: " + dpi);

        if (dpi >= DisplayMetrics.DENSITY_XXXHIGH) {
            deviceDensity = "xxxhdpi";
        } else if (dpi >= DisplayMetrics.DENSITY_XXHIGH && dpi < DisplayMetrics.DENSITY_XXXHIGH) {
            deviceDensity = "xxhdpi";
        } else if (dpi >= DisplayMetrics.DENSITY_XHIGH && dpi < DisplayMetrics.DENSITY_XXHIGH) {
            deviceDensity = "xhdpi";
        } else if (dpi >= DisplayMetrics.DENSITY_HIGH && dpi < DisplayMetrics.DENSITY_XHIGH) {
            deviceDensity = "hdpi";
        } else if (dpi >= DisplayMetrics.DENSITY_MEDIUM && dpi < DisplayMetrics.DENSITY_HIGH) {
            deviceDensity = "mdpi";
        } else if (dpi < DisplayMetrics.DENSITY_MEDIUM) {
            deviceDensity = "ldpi";
        }

        return deviceDensity;
    }

    /**
     * O tamanho maximo do menor eixo da tela (pode ser configurado pelo usuário)
     */
    public static int getSmallestWidth(Context ctx) {
        Configuration config = ctx.getResources().getConfiguration();
        return config.smallestScreenWidthDp; // But it requires API level 13
    }

    /**
     * bota o jogo em tela cheia e esconde a UI
     */
    public static void setImmersiveMode(Activity ctx) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ctx.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            System.err.println("Sem suporte ao modo imersivo");
        }
    }

    /**
     * bota o jogo em tela cheia
     */
    public static void setFullscreen(Activity ctx) {
        ctx.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
