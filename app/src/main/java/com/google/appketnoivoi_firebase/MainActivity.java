package com.google.appketnoivoi_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://bt10firebase-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtphone = (EditText) findViewById(R.id.txtPhone);
        EditText txtpass = (EditText) findViewById(R.id.txtPassWord);

        LinearLayout btnRegister= (LinearLayout) findViewById(R.id.layoutSigup);
        Button btnLogin=(Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneTXT=txtphone.getText().toString();
                final String passTXT=txtpass.getText().toString();

                if(phoneTXT.isEmpty() || passTXT.isEmpty()){
                    Toast.makeText(MainActivity.this,"vui lòng nhập email hoặc password",Toast.LENGTH_SHORT).show();
                }else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(phoneTXT)){
                                final String getPassword = snapshot.child(phoneTXT).child("password").getValue(String.class);


                                if(getPassword.equals(passTXT)){
                                    Toast.makeText(MainActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                                    finish();
                                }else{
                                    Toast.makeText(MainActivity.this,"Sai mật khẩu!",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(MainActivity.this,"Sai số điện thoại!",Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });

    }
}