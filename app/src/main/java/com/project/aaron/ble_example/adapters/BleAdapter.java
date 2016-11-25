package com.project.aaron.ble_example.adapters;

import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by User on 11/25/2016.
 */

public class BleAdapter extends RecyclerView.Adapter<BleAdapter.BleViewHolder> {

    private List<BluetoothDevice> deviceList;

    public BleAdapter(List<BluetoothDevice> deviceList){
        this.deviceList = deviceList;
    }

    @Override
    public BleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public class BleViewHolder extends RecyclerView.ViewHolder{

        public BleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
