package net.jcazevedo.tacc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ToggleButton;

public class TaccActivity extends Activity {
    private ToggleButton whitePlayerButton;
    private ToggleButton blackPlayerButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        whitePlayerButton = (ToggleButton) findViewById(R.id.white_player_button);
        blackPlayerButton = (ToggleButton) findViewById(R.id.black_player_button);
    }

    private void pause() {
    }

    private void toggle(ToggleButton buttonPressed) {
    }

    private void reset() {
    }
}
