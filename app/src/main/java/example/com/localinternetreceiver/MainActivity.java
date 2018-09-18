package example.com.localinternetreceiver;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements InternetReceiver.NetworkStatusChange {

    private InternetReceiver internetReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callNetworkReceiver();
    }

    private void callNetworkReceiver() {
        internetReceiver = new InternetReceiver(this);
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
//        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(internetReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(internetReceiver);
    }

    @Override
    public void onNetworkIsConnected() {
        showToast("INTERNET IS CONNECTED");
    }

    @Override
    public void onNetworkIsDisconnected() {
        showToast("INTERNET IS DISCONNECTED");
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, "" + message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
