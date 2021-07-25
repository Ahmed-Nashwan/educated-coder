package ahmed.ucas.edu.programmingway;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ahmed.ucas.edu.programmingway.databinding.ActivityNextBinding;


@Keep


public class next extends AppCompatActivity {
    ActivityNextBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNextBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), finish.class);
                startActivity(i);
            }
        });
    }
}