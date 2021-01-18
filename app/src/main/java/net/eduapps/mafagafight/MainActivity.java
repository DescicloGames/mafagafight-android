package net.eduapps.mafagafight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.eduapps.util.ScreenUtils;

public class MainActivity extends AppCompatActivity {
    WebView jogo;
    WebViewClient crente = new WebViewClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScreenUtils.setImmersiveMode(this);
        prepareWebView();
        load();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            ScreenUtils.setImmersiveMode(this);
        }
    }

    /**
     * Inicializa a WebView
     */
    public void prepareWebView() {
        jogo = findViewById(R.id.GAME_FRAME);
        
        //configuração do navegador
        jogo.getSettings().setAllowFileAccess(true);
        jogo.getSettings().setJavaScriptEnabled(true);
        jogo.getSettings().setBuiltInZoomControls(false);
        jogo.getSettings().setSupportZoom(false);

        jogo.addJavascriptInterface(new Natives(this),"Natives");
        jogo.setWebViewClient(crente); //previne de abrir uma aba no Chrome
    }

    /**
     * carrega o jogo
     */
    public void load() {
        if (Global.DEBUG) {
            jogo.loadUrl("https://desciclogames.github.io/mafagafight");
        }
    }
}