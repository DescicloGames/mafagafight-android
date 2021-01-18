package net.eduapps.mafagafight;

import android.app.Activity;
import android.os.Vibrator;
import android.webkit.JavascriptInterface;

/**
 * Classe que vai ajudar o jogo nativamente com os vibradores, e etc.
 */
public class Natives {
    private Activity act;
    private Vibrator dildo;
    
    public Natives(Activity act) {
        this.act = act;
        this.dildo = (Vibrator) this.act.getSystemService(Activity.VIBRATOR_SERVICE);
    }
    
    /**
     * Retorna o nome do Sistema Operacional
     * 
     * @return "Android" || "Windows" || "Linux" || "Darwin"
     */
    @JavascriptInterface public static final String OS() {
        return "Android";
    }

    /**
     * Verifica se o dispositivo pode vibrar, em muitos casos
     * é verdadeiro.
     */
    @JavascriptInterface public final boolean canDeviceVibrate() {
        return dildo.hasVibrator();
    }

    /**
     * Verifica se o controle pode vibrar, isso varia de fabricante
     * para fabricante. alguns possuem 2 vibradores, outros nenhum.
     * 
     * @// TODO: 18/01/21 Arranjar um jeito de checar se o controle vibra. 
     */
    @JavascriptInterface public final boolean canControllerVibrate() {
        return dildo.hasVibrator();
    }
}
