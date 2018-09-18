package example.com.localinternetreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetReceiver extends BroadcastReceiver {

    private NetworkStatusChange listener;

    public InternetReceiver(NetworkStatusChange listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnected();

        if (listener != null) {
            if (isConnected) {
                listener.onNetworkIsConnected();
            } else {
                listener.onNetworkIsDisconnected();
            }
        }
    }


    public interface NetworkStatusChange {
        void onNetworkIsConnected();

        void onNetworkIsDisconnected();
    }

}
