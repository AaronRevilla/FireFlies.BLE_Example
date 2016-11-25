package com.project.aaron.ble_example;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.project.aaron.ble_example.adapters.BleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron Revilla on 11/24/2016.
 */


public class MainActivity extends AppCompatActivity {

    private IntentFilter ifBleReceiver;
    private BleReceiver mBleReceiver;
    private RecyclerView mRecyclerView;
    private BleAdapter mAdapter;
    private ProgressBar mProgressBar;


    private static final long SCAN_PERIOD = 10 * 1000;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final String TAG = "MainActivityTAG_";

    //Thread
    private Handler mHandler;
    //BTle
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothScanner;
    private BluetoothManager mBluetoothManager;
    private ScanCallback mScanCallBack;
    private List<BluetoothDevice> deviceList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if BLE is supported in this device
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            Toast.makeText(this, "BLE not supported in this device", Toast.LENGTH_SHORT).show();
            finish();
        }

        //progress bar
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        //create a broadcast receiver
        mBleReceiver = new BleReceiver();
        ifBleReceiver = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mBleReceiver, ifBleReceiver);

        //Recycler View
        deviceList = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.ble_list);
        mRecyclerView.setLayoutManager( new LinearLayoutManager(this));
        mAdapter = new BleAdapter(deviceList);
        mRecyclerView.setAdapter(mAdapter);

        //Handler
        mHandler = new Handler();

        //Init Bluetooth Manager
        mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();
        mBluetoothScanner = mBluetoothAdapter.getBluetoothLeScanner();

        //Create Scanner
        mScanCallBack = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);
                Log.d(TAG, "onScanResult: " + result.toString());
                BluetoothDevice device = result.getDevice();
                deviceList.add( device);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                super.onBatchScanResults(results);
                Log.d(TAG, "onBatchScanResults: " + results.toString());
            }

            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
                Log.d(TAG, "onScanFailed: " + errorCode);
            }
        };

        //Verify if bluetooth is enable
        if(mBluetoothAdapter != null || !mBluetoothAdapter.isEnabled()){
            Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult( enableBluetoothIntent, REQUEST_ENABLE_BT);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void scanDevices(final boolean enable){
        if(enable){
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mBluetoothScanner.stopScan(mScanCallBack);
                }
            }, SCAN_PERIOD);
            mProgressBar.setVisibility(View.VISIBLE);
            mBluetoothScanner.startScan(mScanCallBack);
        }
        else{
            if(mProgressBar.getVisibility() == View.VISIBLE){
                mProgressBar.setVisibility(View.INVISIBLE);
            }
            mBluetoothScanner.stopScan(mScanCallBack);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mBleReceiver != null){
            unregisterReceiver(mBleReceiver);
        }
    }

    public void scanDevices(View view) {

    }




}
