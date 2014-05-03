package ru.blackwhell.app;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class StartActivity extends Activity implements View.OnClickListener {

    int previousState;
    String actionStateChanged;

    Button BluetoothButton;
    IntentFilter filter;

    public BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();

    //Метод, который проверяет состояние bluetooth
    public BroadcastReceiver bluetoothState = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String prevStateExtra = BluetoothAdapter.EXTRA_PREVIOUS_STATE;
            String stateExtra = BluetoothAdapter.EXTRA_STATE;
            int state = intent.getIntExtra(stateExtra, -1);
            previousState = intent.getIntExtra(prevStateExtra, -1);

            switch (state) {
                case BluetoothAdapter.STATE_TURNING_ON:
                    setProgressBarIndeterminateVisibility(true);
                    setTitle(R.string.loading);
                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    setProgressBarIndeterminateVisibility(true);
                    setTitle(R.string.loading);
                    break;
                case BluetoothAdapter.STATE_OFF:
                    setProgressBarIndeterminateVisibility(false);
                    setTitle(R.string.app_name);
                    break;
                case BluetoothAdapter.STATE_ON:
                    setProgressBarIndeterminateVisibility(false);
                    setTitle(R.string.app_name);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_start);

        BluetoothButton = (Button)findViewById(R.id.BluetoothButton);
        BluetoothButton.setOnClickListener(this);

        if(bluetooth == null) finish();                                                        //Если на устройстве нет Bluetooth, то завершаем приложение
        if(bluetooth.isEnabled()) BluetoothButton.setText(R.string.bluetooth_button_off);      //Проверяем включен или нет Bluetooth
        else BluetoothButton.setText(R.string.bluetooth_button_on);

        actionStateChanged = BluetoothAdapter.ACTION_STATE_CHANGED;
        filter = new IntentFilter(actionStateChanged);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.BluetoothButton:
                registerReceiver(bluetoothState, filter);

                if(bluetooth.isEnabled()){
                    bluetooth.disable();
                    BluetoothButton.setText(R.string.bluetooth_button_on);
                } else {
                    bluetooth.enable();
                    BluetoothButton.setText(R.string.bluetooth_button_off);
                }
                break;
        }
    }
}
