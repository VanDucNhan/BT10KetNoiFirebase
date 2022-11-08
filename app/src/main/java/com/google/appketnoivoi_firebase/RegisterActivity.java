package com.google.appketnoivoi_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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

public class RegisterActivity extends AppCompatActivity {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://bt10firebase-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText txtemaildk=(EditText) findViewById(R.id.txtEmaildk);
        final EditText txtnamedk=(EditText) findViewById(R.id.txtTendk);
        final EditText txtphonedk=(EditText) findViewById(R.id.txtPhonedk);
        final EditText txtconPass=(EditText) findViewById(R.id.txtconPassWorddk);
        final EditText txtPass=(EditText) findViewById(R.id.txtPassWorddk);

        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        LinearLayout btnBackLogin = (LinearLayout) findViewById(R.id.backLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String txtnamdkk=txtnamedk.getText().toString();
                final String txtphonedkk=txtphonedk.getText().toString();
                final String txtemaildkk=txtemaildk.getText().toString();
                final String txtpassdkk=txtPass.getText().toString();
                final String txtconpassdkk=txtconPass.getText().toString();

                if(txtnamdkk.isEmpty() || txtphonedkk.isEmpty() || txtemaildkk.isEmpty() || txtpassdkk.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                }else if(!txtpassdkk.equals(txtconpassdkk)){
                    Toast.makeText(RegisterActivity.this,"PassWord is not matching",Toast.LENGTH_SHORT).show();
                }else{

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(txtphonedkk)){
                                Toast.makeText(RegisterActivity.this,"số điện thoại đã được đăng ký",Toast.LENGTH_SHORT).show();
                            }else{
                                databaseReference.child("users").child(txtphonedkk).child("fullname").setValue(txtnamdkk);
                                databaseReference.child("users").child(txtphonedkk).child("email").setValue(txtemaildkk);
                                databaseReference.child("users").child(txtphonedkk).child("password").setValue(txtpassdkk);

                                Toast.makeText(RegisterActivity.this,"Tài khoản đăng ký thành công",Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        });

    }
}
