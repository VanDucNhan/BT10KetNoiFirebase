package com.google.appketnoivoi_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddActivity extends AppCompatActivity {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://bt10firebase-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditText txtmsvadd = (EditText) findViewById(R.id.txtmsvadd);
        EditText txttenadd = (EditText) findViewById(R.id.txttenadd);
        EditText txturladd = (EditText) findViewById(R.id.txturladd);

        Button btnadd = (Button) findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtmasvadd=txtmsvadd.getText().toString();
                String txttenaddd=txttenadd.getText().toString();
                String txturladdd=txturladd.getText().toString();


                databaseReference.child("SinhVien").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.hasChild(txtmasvadd)){
                            Toast.makeText(AddActivity.this,"Sinh Viên đã được nhập",Toast.LENGTH_SHORT).show();
                        }else{
                            databaseReference.child("SinhVien").child(txtmasvadd).child("msv").setValue(txtmasvadd);
                            databaseReference.child("SinhVien").child(txtmasvadd).child("ten").setValue(txttenaddd);
                            databaseReference.child("SinhVien").child(txtmasvadd).child("url").setValue(txturladdd);

                            Toast.makeText(AddActivity.this,"Nhập sinh viên thành công",Toast.LENGTH_SHORT).show();
                            txtmsvadd.setText("");
                            txttenadd.setText("");
                            txturladd.setText("");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        Button btncanceladd = (Button) findViewById(R.id.btncanceladd);

        btncanceladd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this,HomeActivity.class));
            }
        });
    }

}