package com.project.aaron.ble_example;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Aaron Revilla on 11/24/2016.
 */

public class BleReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(BluetoothAdapter.ACTION_STATE_CHANGED)){
            final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

            switch (state){

                case BluetoothAdapter.STATE_ON:
                    Toast.makeText(context, "BT ON", Toast.LENGTH_SHORT).show();
                    break;

                case BluetoothAdapter.STATE_OFF:
                    Toast.makeText(context, "BT OFF", Toast.LENGTH_SHORT).show();
                    break;

                case BluetoothAdapter.STATE_TURNING_ON:
                    Toast.makeText(context, "BT TURNING ON", Toast.LENGTH_SHORT).show();
                    break;

                case BluetoothAdapter.STATE_TURNING_OFF:
                    Toast.makeText(context, "BT TURNING OFF", Toast.LENGTH_SHORT).show();
                    break;

                case BluetoothAdapter.STATE_CONNECTING:
                    Toast.makeText(context, "BT CONNECTING", Toast.LENGTH_SHORT).show();
                    break;

                case BluetoothAdapter.ERROR:
                    Toast.makeText(context, "BT OFF", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }
}
