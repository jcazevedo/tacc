package net.jcazevedo.tacc;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ToggleButton;

public class TaccActivity extends Activity {
    private ToggleButton whitePlayerButton;
    private ToggleButton blackPlayerButton;

    private Map<ToggleButton, Integer> seconds = new HashMap<ToggleButton, Integer>();

    private Handler timerHandler = new Handler();

    private class TimerRunnable implements Runnable {
        private ToggleButton button;

        public TimerRunnable(ToggleButton _button) {
            button = _button;
        }

        public void run() {
            int time = seconds.get(button);

            button.setText(time / 60 + ":" + time % 60);
            seconds.put(button, --time);
            timerHandler.postDelayed(this, 1000);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        whitePlayerButton = (ToggleButton) findViewById(R.id.white_player_button);
        blackPlayerButton = (ToggleButton) findViewById(R.id.black_player_button);
        seconds.put(whitePlayerButton, 300);
        seconds.put(blackPlayerButton, 300);

        addListenerOnButtons();
    }

    private void pause() {
    }

    private void toggle(ToggleButton buttonPressed) {
        timerHandler.removeCallbacksAndMessages(null);
        if (buttonPressed == whitePlayerButton) {
            timerHandler.post(new TimerRunnable(blackPlayerButton));
        } else {
            timerHandler.post(new TimerRunnable(whitePlayerButton));
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
                ToggleButton button = (ToggleButton) v;
				toggle(button);
			}
    	});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        Log.i("tacc", "entering onCreateContextMenu");
        return true;
    }
}
