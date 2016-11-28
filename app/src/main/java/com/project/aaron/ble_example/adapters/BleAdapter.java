package com.project.aaron.ble_example.adapters;

import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.aaron.ble_example.R;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ble_device_info, parent, false);

        BleAdapter.BleViewHolder vh = new BleAdapter.BleViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(BleViewHolder holder, int position) {
        BluetoothDevice currentDevice = deviceList.get(position);

        holder.name.setText(currentDevice.getName());
        holder.address.setText(currentDevice.getAddress());

        switch (currentDevice.getType()){

            case BluetoothDevice.DEVICE_TYPE_CLASSIC:
                holder.type.setText( "DEVICE_TYPE_CLASSIC");
                break;

            case BluetoothDevice.DEVICE_TYPE_DUAL :
                holder.type.setText( "DEVICE_TYPE_DUAL");
                break;

            case BluetoothDevice.DEVICE_TYPE_LE:
                holder.type.setText( "DEVICE_TYPE_LE");
                break;

            case BluetoothDevice.DEVICE_TYPE_UNKNOWN:
                holder.type.setText( "DEVICE_TYPE_UNKNOWN");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public class BleViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView address;
        public TextView type;

        public BleViewHolder(View itemView) {
            super(itemView);

            name = ((TextView) itemView.findViewById(R.id.ble_name));
            address = ((TextView) itemView.findViewById(R.id.ble_address));
            type = ((TextView) itemView.findViewById(R.id.ble_type));

        }
    }
}
