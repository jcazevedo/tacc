package net.jcazevedo.tacc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Handler;
import android.view.Window;
import android.widget.ToggleButton;

public class TaccActivity extends Activity {
    private ToggleButton whitePlayerButton;
    private ToggleButton blackPlayerButton;

    private Integer whitePlayerSeconds;
    private Integer blackPlayerSeconds;

    private Handler timerHandler = new Handler();

    private class TimerRunnable implements Runnable {
        private ToggleButton button;
        private Integer timer;

        public TimerRunnable(ToggleButton _button, Integer _timer) {
            button = _button;
            timer = _timer;
        }

        public void run() {
            button.setText(timer / 60 + ":" + timer % 60);
            timer--;
            timerHandler.postDelayed(this, 1000);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        whitePlayerSeconds = 300;
        blackPlayerSeconds = 300;

        whitePlayerButton = (ToggleButton) findViewById(R.id.white_player_button);
        blackPlayerButton = (ToggleButton) findViewById(R.id.black_player_button);
        addListenerOnButtons();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
    }

    private void pause() {
    }

    private void toggle(ToggleButton buttonPressed) {
        timerHandler.removeCallbacksAndMessages(null);
        if (buttonPressed == whitePlayerButton) {
            timerHandler.postDelayed(new TimerRunnable(blackPlayerButton,
                                                       blackPlayerSeconds),
                                     1000);
        } else {
            timerHandler.postDelayed(new TimerRunnable(whitePlayerButton,
                                                       whitePlayerSeconds),
                                     1000);
        }
    }

    private void reset() {
    }

    public void addListenerOnButtons() {
    	createListener(this.whitePlayerButton);
    	createListener(this.blackPlayerButton);
    }

    private void createListener(ToggleButton button){
    	button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				toggle((ToggleButton) v);
			}
    	});
    }
}
