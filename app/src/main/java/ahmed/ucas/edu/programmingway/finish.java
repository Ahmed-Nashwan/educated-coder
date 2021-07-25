package ahmed.ucas.edu.programmingway;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import ahmed.ucas.edu.programmingway.databinding.ActivityFinishBinding;

@Keep

public class finish extends AppCompatActivity {

    ActivityFinishBinding binding;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFinishBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnStartTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences("pr", MODE_PRIVATE).edit().putInt("is_start_true", 55).apply();
                Intent i = new Intent(getApplicationContext(), all_languages.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            }
        });
    }
}