package net.jcazevedo.tacc.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Button;
import net.jcazevedo.tacc.R;

public class ClockButton extends Button {
    private boolean toggled;
    private boolean rotated;
    private long time;

    public ClockButton(Context context) {
        super(context);
        init(context, null);
    }

    public ClockButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ClockButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public void setToggled(boolean _toggled) {
        toggled = _toggled;
        this.invalidate();
    }

    public void setTime(long _time) {
        time = _time;
        long seconds = time / 1000;
        boolean negative = false;

        if (seconds < 0) {
            negative = true;
            seconds = -seconds;
        }

        this.setText((negative ? "-" : "") +
                     String.format("%02d:%02d", seconds / 60, seconds % 60));
    }

    public long getTime() {
        return time;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void onDraw(Canvas canvas) {
        if (rotated)
            canvas.rotate(180, getWidth() / 2, getHeight() / 2);
        super.onDraw(canvas);
    }

    private void init(Context context, AttributeSet attrs) {
        toggled = false;

        if (attrs != null && context != null) {
            TypedArray ta =
                context.obtainStyledAttributes(attrs, R.styleable.ClockButton);
            rotated = ta.getBoolean(R.styleable.ClockButton_rotated, false);
        }
    }
}
