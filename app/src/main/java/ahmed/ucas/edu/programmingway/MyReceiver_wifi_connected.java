package ahmed.ucas.edu.programmingway;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import androidx.annotation.Keep;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.WIFI_SERVICE;

@Keep
public class MyReceiver_wifi_connected extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {

            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);

            NetworkInfo network = manager.getActiveNetworkInfo();


            WifiManager manager1 = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
            WifiInfo info = manager1.getConnectionInfo();

            if (network != null && info != null && network.isConnected()) {

                //   Toast.makeText(context, "تم الإتصال بالإنترنت", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(context, all_languages.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent1);


            } else {
                Toast.makeText(context, "لا يوجد إنترنت", Toast.LENGTH_SHORT).show();

            }


        }
    }
}
