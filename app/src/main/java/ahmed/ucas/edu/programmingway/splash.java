package ahmed.ucas.edu.programmingway;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


@Keep

public class splash extends AppCompatActivity {

    Thread thread;
    SharedPreferences preferences;
    int is_start_try;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        is_start_try = getSharedPreferences("pr", MODE_PRIVATE).getInt("is_start_true", 5);

        thread = new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(3000);

                    if (is_start_try == 55) {


                        Intent i = new Intent(getApplicationContext(), all_languages.class);
                        startActivity(i);
                        finish();
                        //  Toast.makeText(splash.this, is_start_try+"", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent i = new Intent(getApplicationContext(), next.class);
                        startActivity(i);
                        finish();
                        //Toast.makeText(splash.this, is_start_try+"", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {

                }

            }

        };

        thread.start();
    }
}