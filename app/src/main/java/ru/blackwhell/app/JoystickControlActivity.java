package ru.blackwhell.app;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class JoystickControlActivity extends Activity implements CompoundButton.OnCheckedChangeListener {
    ToggleButton left;
    ToggleButton right;

    AnimationDrawable left_anim = null;
    AnimationDrawable right_anim = null;

    ImageView left_bg;
    ImageView right_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick_control);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        left = (ToggleButton) findViewById(R.id.left_button);
        left.setOnCheckedChangeListener(this);

        right = (ToggleButton)findViewById(R.id.right_button);
        right.setOnCheckedChangeListener(this);

        left_bg = (ImageView) findViewById(R.id.left_bg);
        right_bg = (ImageView)findViewById(R.id.right_bg);

    }

    public void onClickRight(View view){
        if (((ToggleButton)view).isChecked()) {
            right_bg.setBackgroundResource(R.drawable.right_anim);
            right_anim = (AnimationDrawable) right_bg.getBackground();
            right_anim.start();
        } else {
            right_anim.stop();
            right_bg.setBackgroundResource(R.drawable.right_off);
        }
    }

    public void onClickLeft(View view){
        if (((ToggleButton)view).isChecked()) {
            left_bg.setBackgroundResource(R.drawable.left_anim);
            left_anim = (AnimationDrawable) left_bg.getBackground();
            left_anim.start();
        } else {
            left_anim.stop();
            left_bg.setBackgroundResource(R.drawable.left_off);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {}
}
