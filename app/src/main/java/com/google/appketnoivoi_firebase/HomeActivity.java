package com.google.appketnoivoi_firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.appketnoivoi_firebase.Model.SinhVienAdapter;
import com.google.appketnoivoi_firebase.Model.SinhVienModel;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rvsv;
    SinhVienAdapter sinhVienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rvsv= (RecyclerView) findViewById(R.id.rv);
        rvsv.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<SinhVienModel> options =
                new FirebaseRecyclerOptions.Builder<SinhVienModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SinhVien"),SinhVienModel.class)
                        .build();

        sinhVienAdapter = new SinhVienAdapter(options);
        rvsv.setAdapter(sinhVienAdapter);

        FloatingActionButton btnsangadd = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        btnsangadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,AddActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        sinhVienAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sinhVienAdapter.stopListening();
    }
}