package net.jcazevedo.tacc;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import net.jcazevedo.tacc.view.ClockButton;

public class ClockActivity extends Activity {
    private static int REFRESH_MS = 50;

    private ClockButton whitePlayerButton;
    private ClockButton blackPlayerButton;
    private Handler timerHandler = new Handler();

    private class TimerRunnable implements Runnable {
        private ClockButton button;
        private Long prevTimestamp;

        public TimerRunnable(ClockButton _button) {
            button = _button;
            prevTimestamp = null;
        }

        public void run() {
            Long currentTimestamp = System.currentTimeMillis();
            long refresh = 0;
            if (prevTimestamp != null)
                refresh = currentTimestamp - prevTimestamp;
            prevTimestamp = currentTimestamp;

            button.setTime(button.getTime() - refresh);
            timerHandler.postDelayed(this, REFRESH_MS);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.clock);

        whitePlayerButton = (ClockButton) findViewById(R.id.white_player_button);
        blackPlayerButton = (ClockButton) findViewById(R.id.black_player_button);

        reset();
        addListenerOnButtons();
    }

    private void pause() {
        timerHandler.removeCallbacksAndMessages(null);
    }

    private void toggle(ClockButton buttonPressed) {
        timerHandler.removeCallbacksAndMessages(null);
        if (buttonPressed == whitePlayerButton) {
            timerHandler.postDelayed(new TimerRunnable(blackPlayerButton), REFRESH_MS);
            blackPlayerButton.setToggled(true);
            whitePlayerButton.setToggled(false);
        } else {
            timerHandler.postDelayed(new TimerRunnable(whitePlayerButton), REFRESH_MS);
            blackPlayerButton.setToggled(false);
            whitePlayerButton.setToggled(true);
        }
    }

    private void reset() {
        whitePlayerButton.setTime(300000);
        blackPlayerButton.setTime(300000);
        whitePlayerButton.setToggled(false);
        whitePlayerButton.setToggled(false);
    }

    public void addListenerOnButtons() {
        createListener(this.whitePlayerButton);
        createListener(this.blackPlayerButton);
    }

    private void createListener(ClockButton button){
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ClockButton button = (ClockButton) v;
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
