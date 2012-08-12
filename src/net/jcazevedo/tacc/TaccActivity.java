package net.jcazevedo.tacc;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ToggleButton;

public class TaccActivity extends Activity {
    private ToggleButton whitePlayerButton;
    private ToggleButton blackPlayerButton;
    private int whiteTimeSeconds;
    private int blackTimeSeconds;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        whitePlayerButton = (ToggleButton) findViewById(R.id.white_player_button);
        blackPlayerButton = (ToggleButton) findViewById(R.id.black_player_button);
        addListenerOnButtons();
    }


    private void pause() {
    }

    private void toggle(ToggleButton buttonPressed) {
    }

    private void reset() {
    }
    
    
    public void addListenerOnButtons() {
    	createListener(this.whitePlayerButton);
    	createListener(this.blackPlayerButton);
    }
 
    
    private void createListener(ToggleButton button){
    	blackPlayerButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				toggle((ToggleButton) v);				
			}
    	});
    }
    
    
    
}
