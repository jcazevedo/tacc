package net.jcazevedo.tacc;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import java.util.HashMap;
import java.util.Map;

public class ClockActivity extends Activity {
    private static int REFRESH_MS = 10;

    private Button whitePlayerButton;
    private Button blackPlayerButton;
    private Map<Button, Integer> timers;
    private Map<Button, Boolean> toggled;
    private Handler timerHandler = new Handler();

    private class TimerRunnable implements Runnable {
        private Button button;

        public TimerRunnable(Button _button) {
            button = _button;
        }

        public void run() {
            updateButtonText(button);
            timers.put(button, timers.get(button) - REFRESH_MS);
            timerHandler.postDelayed(this, REFRESH_MS);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.clock);

        timers = new HashMap<Button, Integer>();
        toggled = new HashMap<Button, Boolean>();

        whitePlayerButton = (Button) findViewById(R.id.white_player_button);
        blackPlayerButton = (Button) findViewById(R.id.black_player_button);

        reset();
        addListenerOnButtons();
    }

    private void updateButtonText(Button button) {
        int time = timers.get(button);
        int seconds = time / 1000;
        boolean negative = false;

        if (seconds < 0) {
            negative = true;
            seconds = -seconds;
        }

        button.setText((negative ? "-" : "") + String.format("%02d:%02d", seconds / 60, seconds % 60));
    }

    private void pause() {
        timerHandler.removeCallbacksAndMessages(null);
    }

    private void toggle(Button buttonPressed) {
        timerHandler.removeCallbacksAndMessages(null);
        if (buttonPressed == whitePlayerButton) {
            timerHandler.post(new TimerRunnable(blackPlayerButton));
            toggled.put(blackPlayerButton, true);
            toggled.put(whitePlayerButton, false);
        } else {
            timerHandler.post(new TimerRunnable(whitePlayerButton));
            toggled.put(blackPlayerButton, false);
            toggled.put(whitePlayerButton, true);
        }
    }

    private void reset() {
        timers.put(whitePlayerButton, 300000);
        timers.put(blackPlayerButton, 300000);
        toggled.put(whitePlayerButton, false);
        toggled.put(blackPlayerButton, false);
        updateButtonText(whitePlayerButton);
        updateButtonText(blackPlayerButton);
    }

    public void addListenerOnButtons() {
        createListener(this.whitePlayerButton);
        createListener(this.blackPlayerButton);
    }

    private void createListener(Button button){
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Button button = (Button) v;
                toggle(button);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
