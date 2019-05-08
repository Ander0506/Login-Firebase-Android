package com.example.mylogin;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText TxtUser, TxtPassword;
    private FirebaseAuth Auth;
    private Resources Res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TxtUser = (EditText) findViewById(R.id.TextEmail);
        TxtPassword = (EditText) findViewById(R.id.TextPassword);
        Res = this.getResources();

        Auth = FirebaseAuth.getInstance();
    }

    public void Login(View view){
        String email = TxtUser.getText().toString();
        String pass = TxtPassword.getText().toString();

        if(email.isEmpty() || pass.isEmpty()){
            Toast toast = Toast.makeText(MainActivity.this, Res.getString(R.string.err_empty),Toast.LENGTH_LONG);
            TxtUser.setError("*");
            TxtPassword.setError("*");
        }else{

            Auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            startActivity(intent);
                        } else {
                            Toast toast = Toast.makeText(MainActivity.this, Res.getString(R.string.err_user),Toast.LENGTH_LONG);
                            TxtUser.setError("*");
                            TxtPassword.setError("*");
                        }

                        // ...
                    }
                });
        }
    }

    public void Register(View view){
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
    }
}
