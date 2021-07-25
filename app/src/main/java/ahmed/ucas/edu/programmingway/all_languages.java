package ahmed.ucas.edu.programmingway;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ahmed.ucas.edu.programmingway.databinding.ActivityAllLanguagesBinding;


@Keep
public class all_languages extends AppCompatActivity {

    ActivityAllLanguagesBinding binding;


    Adapter adapter;
    ArrayList<Language_data> arrayList;
    FirebaseFirestore firestore;
    ProgressBar progressBar;
    String lang_type;
    int section_selected;
    String language_device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllLanguagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        arrayList = new ArrayList<>();
        firestore = FirebaseFirestore.getInstance();
        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle("Educated programmer");


        lang_type = "other";


        getFieldLanguagesFromDatabase();

        startReciver();


        adapter = new Adapter(arrayList, new onItemClick() {
            @Override
            public void itemClickedRecycler(String id) {
                Intent i = new Intent(getApplicationContext(), details.class);
                i.putExtra("docId", id);
                startActivity(i);


            }


        }, getApplicationContext());


        binding.tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                section_selected = 1;
                lang_type = "all";
                binding.tvAll.setBackground(getResources().getDrawable(R.drawable.empha_shape));
                binding.tvBestField.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvOtherLanguages.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvMobile.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvWeb.setBackground(getResources().getDrawable(R.drawable.all_shape));

                getAllDataFromFirebase();


            }
        });


        binding.tvWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                section_selected = 2;
                lang_type = "web";
                binding.tvAll.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvBestField.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvOtherLanguages.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvMobile.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvWeb.setBackground(getResources().getDrawable(R.drawable.empha_shape));


                getWebLanguagesFromDatabase();


            }
        });

        binding.tvMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                section_selected = 3;
                lang_type = "mobile";
                binding.tvAll.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvBestField.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvOtherLanguages.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvMobile.setBackground(getResources().getDrawable(R.drawable.empha_shape));
                binding.tvWeb.setBackground(getResources().getDrawable(R.drawable.all_shape));


                getMobileLanguagesFromDatabase();


            }
        });

        binding.tvOtherLanguages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                section_selected = 4;
                lang_type = "other";
                binding.tvAll.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvBestField.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvOtherLanguages.setBackground(getResources().getDrawable(R.drawable.empha_shape));
                binding.tvMobile.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvWeb.setBackground(getResources().getDrawable(R.drawable.all_shape));


                getOtherLanguagesFromDatabase();


            }
        });

        binding.tvBestField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                section_selected = 5;
                lang_type = "field";
                binding.tvAll.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvBestField.setBackground(getResources().getDrawable(R.drawable.empha_shape));
                binding.tvOtherLanguages.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvMobile.setBackground(getResources().getDrawable(R.drawable.all_shape));
                binding.tvWeb.setBackground(getResources().getDrawable(R.drawable.all_shape));

                getFieldLanguagesFromDatabase();


            }
        });


    }


    public void startReciver() {
        MyReceiver_wifi_status receiver_wifi_status = new MyReceiver_wifi_status();

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(receiver_wifi_status, filter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                show_dialog();

                search(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }


        });
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                switch (section_selected) {

                    case 1:
                        getAllDataFromFirebase();

                        break;

                    case 2:
                        getWebLanguagesFromDatabase();
                        break;

                    case 3:


                        getMobileLanguagesFromDatabase();


                        break;

                    case 4:


                        getOtherLanguagesFromDatabase();


                        break;

                    case 5:


                        getFieldLanguagesFromDatabase();


                        break;


                    default:

                        getFieldLanguagesFromDatabase();
                        break;


                }

                return false;
            }
        });

        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(getApplicationContext(), settings.class);
                startActivity(intent);
                return true;
            case R.id.menu_about:
                Intent intent1 = new Intent(getApplicationContext(), about.class);
                startActivity(intent1);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void search(String query) {

        if (lang_type.equals("all")) {
            arrayList.clear();
            firestore.collection("languages").whereEqualTo("languageName", query.toLowerCase())
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                        Language_data language_data = new Language_data();
                        language_data.setDocId(documentSnapshot.getString("docId"));
                        language_data.setImageUrl(documentSnapshot.getString("imageUrl"));
                        language_data.setLanguageName(documentSnapshot.getString("languageName"));

                        arrayList.add(language_data);
                        adapter.notifyDataSetChanged();


                    }


                    dismess_dialog();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(all_languages.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        } else {

            arrayList.clear();
            firestore.collection("languages").whereEqualTo("languageName", query.toLowerCase())
                    .whereEqualTo("languageType", lang_type)
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Language_data language_data = new Language_data();
                        language_data.setDocId(documentSnapshot.getString("docId"));
                        language_data.setImageUrl(documentSnapshot.getString("imageUrl"));
                        language_data.setLanguageName(documentSnapshot.getString("languageName"));

                        arrayList.add(language_data);
                        adapter.notifyDataSetChanged();


                    }

                    dismess_dialog();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(all_languages.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });


        }

    }

    public void show_dialog() {
        progressBar = new ProgressBar(all_languages.this, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        binding.relativeApp.addView(progressBar, params);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,  // not able to touch
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        // getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE); able tp touch

        progressBar.setVisibility(View.VISIBLE);
    }

    public void dismess_dialog() {
        progressBar.setVisibility(View.GONE);
    }


    public void getAllDataFromFirebase() {
        arrayList.clear();

        firestore.collection("languages")
                //   .whereEqualTo("languageType","web")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot document : documents) {
                            Language_data language_data = new Language_data();
                            language_data.setDocId(document.getString("docId"));
                            language_data.setImageUrl(document.getString("imageUrl"));
                            language_data.setLanguageName(document.getString("languageName"));

                            arrayList.add(language_data);


                        }

                        binding.recyclerAllLanguages.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        binding.recyclerAllLanguages.setAdapter(adapter);

                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(all_languages.this, e.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

    }


    public void getWebLanguagesFromDatabase() {
        arrayList.clear();

        firestore.collection("languages")
                .whereEqualTo("languageType", "web")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot document : documents) {
                            Language_data language_data = new Language_data();
                            language_data.setDocId(document.getString("docId"));
                            language_data.setImageUrl(document.getString("imageUrl"));
                            language_data.setLanguageName(document.getString("languageName"));

                            arrayList.add(language_data);


                        }

                        binding.recyclerAllLanguages.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        binding.recyclerAllLanguages.setAdapter(adapter);

                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(all_languages.this, e.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

    }


    public void getMobileLanguagesFromDatabase() {
        arrayList.clear();

        firestore.collection("languages")
                .whereEqualTo("languageType", "mobile")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot document : documents) {
                            Language_data language_data = new Language_data();
                            language_data.setDocId(document.getString("docId"));
                            language_data.setImageUrl(document.getString("imageUrl"));
                            language_data.setLanguageName(document.getString("languageName"));

                            arrayList.add(language_data);


                        }

                        binding.recyclerAllLanguages.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        binding.recyclerAllLanguages.setAdapter(adapter);

                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(all_languages.this, e.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

    }


    public void getOtherLanguagesFromDatabase() {
        arrayList.clear();

        firestore.collection("languages")
                .whereEqualTo("languageType", "other")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot document : documents) {
                            Language_data language_data = new Language_data();
                            language_data.setDocId(document.getString("docId"));
                            language_data.setImageUrl(document.getString("imageUrl"));
                            language_data.setLanguageName(document.getString("languageName"));

                            arrayList.add(language_data);


                        }

                        binding.recyclerAllLanguages.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        binding.recyclerAllLanguages.setAdapter(adapter);

                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(all_languages.this, e.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

    }


    public void getFieldLanguagesFromDatabase() {

        arrayList.clear();

        firestore.collection("languages")
                .whereEqualTo("languageType", "field")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot document : documents) {
                            Language_data language_data = new Language_data();
                            language_data.setDocId(document.getString("docId"));
                            language_data.setImageUrl(document.getString("imageUrl"));
                            language_data.setLanguageName(document.getString("languageName"));

                            arrayList.add(language_data);


                        }

                        binding.recyclerAllLanguages.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        binding.recyclerAllLanguages.setAdapter(adapter);

                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(all_languages.this, e.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

    }
}

