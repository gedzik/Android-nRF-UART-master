package com.nordicsemi.nrfUARTv2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jjoe64.graphview.series.DataPoint;

public class UARTStatusChangeReceiver extends BroadcastReceiver {
    UARTStatusChangeReceiver(MainActivity activity) {
        this.mActivity = activity;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        final Intent mIntent = intent;

        if (action.equals(UartService.ACTION_GATT_CONNECTED)) {
            mActivity.adjustUiOnConnected();
        }

        if (action.equals(UartService.ACTION_GATT_DISCONNECTED)) {
            mActivity.adjustUiOnDisconnected();
        }


        if (action.equals(UartService.ACTION_GATT_SERVICES_DISCOVERED)) {
            mActivity.mService.enableTXNotification();
        }

        if (action.equals(UartService.ACTION_DATA_AVAILABLE)) {

            final byte[] txValue = intent.getByteArrayExtra(UartService.EXTRA_DATA);

            try {
                String text = new String(txValue, "UTF-8");
                Double dbl = Double.parseDouble(text);

                mActivity.mResults.add(dbl);
                mActivity.mSeries.appendData(new DataPoint(0, dbl), true, 40);
                mActivity.adjustUiOnDataAvailable(text);
            } catch (Exception e) {
            }
        }
        //*********************//
        if (action.equals(UartService.DEVICE_DOES_NOT_SUPPORT_UART)) {
//            showMessage("Device doesn't support UART. Disconnecting");
            mActivity.mService.disconnect();
        }


    }

    private MainActivity mActivity;
}
