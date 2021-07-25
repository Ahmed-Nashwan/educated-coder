package ahmed.ucas.edu.programmingway;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

@Keep

public class noInternet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);


        startReciver();


    }


    public void startReciver() {
        MyReceiver_wifi_connected receiver_wifi_status = new MyReceiver_wifi_connected();

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(receiver_wifi_status, filter);
    }
//setAnimation(AnimationUtils.loadAnimation(this,R.anim.screen1)); // load animation in item
}