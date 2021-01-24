package net.eduapps.mafagafight;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.erz.joysticklibrary.JoyStick;

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
        //setupGamepad();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
            ScreenUtils.setImmersiveMode(this);
    }

    /**
     * Inicializa a WebView
     */
    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    public void prepareWebView() {
        jogo = findViewById(R.id.GAME_FRAME);
        //jogo.setFocusable(true);
        //jogo.requestFocus();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //jogo.setFocusedByDefault(true);
        }

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
            jogo.loadUrl("file:///android_asset/test-device.html"); //https://desciclogames.github.io/mafagafight
        }
    }

    /**
     * configura o gamepad virtual
     */
    public void setupGamepad() {
        setupButton(R.id.A,"KeyJ");
        setupButton(R.id.B,"KeyK");
        setupButton(R.id.X,"KeyU");
        setupButton(R.id.Y,"KeyI");
        setupButton(R.id.LB,"KeyO");
        setupButton(R.id.RB,"KeyL");
        setupJoystick();
    }

    /**
     * Assina uma tecla a um botão virtual
     *
     * @param id o id do botão virtual R.id.X
     * @param key o id do botão físico KeyEvent.KEYCODE_X
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setupButton(int id, String key) {
        ImageView button = findViewById(id);
        button.setFocusable(false);

        button.setOnTouchListener((v, event) -> {
            boolean returns = false;

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                //jogo.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, key));
                jogo.loadUrl("javascript:RAM.IO[\"" + key + "\"] = true");
                System.out.println("Pressionado " + key);

                returns = true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                //jogo.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, key));
                jogo.loadUrl("javascript:RAM.IO[\"" + key + "\"] = false");
                System.out.println("Largado " + key);
            }

            return returns;
        });

        System.out.println("Configurado " + id + " para " + key);
    }

    public void setupJoystick() {
        JoyStick j = findViewById(R.id.LS);

        j.setListener(new JoyStick.JoyStickListener() {
            @Override
            public void onMove(JoyStick joyStick, double angle, double power, int direction) {
                if (direction == JoyStick.DIRECTION_RIGHT) {
                    jogo.loadUrl("javascript:RAM.IO[\"KeyW\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyA\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyS\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyD\"] = true");

                    System.out.println("Joystick está em [KeyD]");
                } else if (direction == JoyStick.DIRECTION_RIGHT_DOWN) {
                    jogo.loadUrl("javascript:RAM.IO[\"KeyW\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyA\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyS\"] = true");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyD\"] = true");

                    System.out.println("Joystick está em [\"KeyD\",\"KeyS\"]");
                } else if (direction == JoyStick.DIRECTION_UP_RIGHT) {
                    jogo.loadUrl("javascript:RAM.IO[\"KeyW\"] = true");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyA\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyS\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyD\"] = true");

                    System.out.println("Joystick está em [\"KeyD\",\"KeyW\"]");
                } else if (direction == JoyStick.DIRECTION_UP) {
                    jogo.loadUrl("javascript:RAM.IO[\"KeyW\"] = true");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyA\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyS\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyD\"] = false");

                    System.out.println("Joystick está em [\"KeyW\"]");
                } else if (direction == JoyStick.DIRECTION_DOWN) {
                    jogo.loadUrl("javascript:RAM.IO[\"KeyW\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyA\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyS\"] = true");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyD\"] = false");

                    System.out.println("Joystick está em [\"KeyS\"]");
                } else if (direction == JoyStick.DIRECTION_DOWN_LEFT) {
                    jogo.loadUrl("javascript:RAM.IO[\"KeyW\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyA\"] = true");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyS\"] = true");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyD\"] = false");

                    System.out.println("Joystick está em [\"KeyA\",\"KeyS\"]");
                } else if (direction == JoyStick.DIRECTION_LEFT) {
                    jogo.loadUrl("javascript:RAM.IO[\"KeyW\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyA\"] = true");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyS\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyD\"] = false");

                    System.out.println("Joystick está em [\"KeyA\"]");
                } else if (direction == JoyStick.DIRECTION_LEFT_UP) {
                    jogo.loadUrl("javascript:RAM.IO[\"KeyW\"] = true");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyA\"] = true");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyS\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyD\"] = false");

                    System.out.println("Joystick está em [\"KeyA\",\"KeyW\"]");
                } else {
                    jogo.loadUrl("javascript:RAM.IO[\"KeyW\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyA\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyS\"] = false");
                    jogo.loadUrl("javascript:RAM.IO[\"KeyD\"] = false");

                    System.out.println("Joystick está em null");
                }
            }

            @Override
            public void onTap() {

            }

            @Override
            public void onDoubleTap() {

            }
        });
    }
}