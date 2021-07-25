package ahmed.ucas.edu.programmingway;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import com.synnapps.carouselview.ImageListener;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;


import ahmed.ucas.edu.programmingway.databinding.DetailsBinding;

public class details extends AppCompatActivity {

    FirebaseFirestore firestore;

    DetailsBinding binding;
    String url_arabic_course;
    String url_english_course;
    String url_premium_course;
    String language_description;
    String language_device;
    String lanuage_name;

    @Keep

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firestore = FirebaseFirestore.getInstance();
        language_device = Locale.getDefault().getDisplayLanguage(); // the language of device
        start_animation();

        setSupportActionBar(binding.toolbarDes);
        getSupportActionBar().setTitle("Details");


        final int images[] = {R.drawable.mot1, R.drawable.mot11, R.drawable.motnew, R.drawable.mot2,R.drawable.mot33};


        binding.carouselView.setPageCount(images.length);


        binding.carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(images[position]);
            }
        });


        Intent i = getIntent();
        String docId = i.getStringExtra("docId");
        firestore.collection("languages").document(docId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                lanuage_name = documentSnapshot.getString("languageName");
                binding.tvLanguageName.setText(lanuage_name);

                if (language_device.equals("English")) {
                    language_description = documentSnapshot.getString("languageDescription_en");
                } else {
                    language_description = documentSnapshot.getString("languageDescription");
                }
                binding.tvLanguageDiscription.setText(language_description);
                Glide.with(getApplicationContext()).load(documentSnapshot.getString("imageUrl")).into(binding.imgaeLanguage);
                url_arabic_course = documentSnapshot.getString("arabicCourse");
                url_english_course = documentSnapshot.getString("englishCourse");
                url_premium_course = documentSnapshot.getString("premiumCourse");


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(details.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        binding.tvCourseArabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url_arabic_course));
                startActivity(i);
            }
        });

        binding.tvCourseEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url_english_course));
                startActivity(i);
            }
        });

        binding.tvPremiumCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url_premium_course));
                startActivity(i);
            }
        });


    }


    public void start_animation() {


        binding.imgaeLanguage.setAnimation(AnimationUtils.loadAnimation(details.this, R.anim.screen1));
        binding.tvLanguageName.setAnimation(AnimationUtils.loadAnimation(details.this, R.anim.screen1));
        binding.tvLanguageDiscription.setAnimation(AnimationUtils.loadAnimation(details.this, R.anim.screen1));


    }
}


//        firestore.collection("languages").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//           List<DocumentSnapshot> documentSnapshots =  queryDocumentSnapshots.getDocuments();
//
//                  for(DocumentSnapshot documentSnapshot : documentSnapshots){
//
//
//                  }
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });