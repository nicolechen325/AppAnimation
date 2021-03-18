package com.set.view.surface.video;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.set.view.MyApplication;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Zhenhua on 2018/1/2.
 * @email zhshan@ctrip.com ^.^
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    public interface NetworkChangeListener {
        void onNetworkChange(VideoPlayer.NetworkStatus networkStatus);
    }

    private NetworkChangeListener mListener;
    public AtomicBoolean mAtomicBoolean = null;

    public NetworkChangeReceiver(NetworkChangeListener mListener) {
        this.mListener = mListener;
        this.mAtomicBoolean = new AtomicBoolean(false);
    }

    public void registerNetworkChangeBroadcast() {
        if (mAtomicBoolean.get()) {
            return;
        }
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            MyApplication.getInstance().registerReceiver(this, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unRegisterNetworkChangeBroadcast() {
        if (!mAtomicBoolean.get()) {
            return;
        }
        MyApplication.getInstance().unregisterReceiver(this);
        mAtomicBoolean.set(false);
    }

    public static boolean isWifi() {
        return getNetworkStatus(MyApplication.getInstance()) == VideoPlayer.NetworkStatus.CONNECTED_WIFI;
    }

    public static boolean is4G() {
        return getNetworkStatus(MyApplication.getInstance()) == VideoPlayer.NetworkStatus.CONNECTED_4G;
    }

    public static boolean isDisconnect() {
        return getNetworkStatus(MyApplication.getInstance()) == VideoPlayer.NetworkStatus.NOT_CONNECTED;
    }

    public static VideoPlayer.NetworkStatus getNetworkStatus(Context context) {
        VideoPlayer.NetworkStatus networkStatus = VideoPlayer.NetworkStatus.CONNECTED_WIFI;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {
                String name = info.getTypeName();
                if (TextUtils.equals(name, "WIFI")) {
                    networkStatus = VideoPlayer.NetworkStatus.CONNECTED_WIFI;
                } else {
                    networkStatus = VideoPlayer.NetworkStatus.CONNECTED_4G;
                }
            } else {
                networkStatus = VideoPlayer.NetworkStatus.NOT_CONNECTED;
            }
        }
        return networkStatus;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if (mListener != null) {
                mListener.onNetworkChange(getNetworkStatus(context));
            }
        }
    }
}
