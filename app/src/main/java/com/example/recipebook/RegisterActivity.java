package com.example.recipebook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recipebook.db.DatabaseHelper;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

    }

    public void backToLandingPage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void register(View view){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);

        EditText email = findViewById(R.id.editTextTextEmailAddress2Register);
        EditText password = findViewById(R.id.editTextTextPasswordRegister);
        EditText confirmPassword = findViewById(R.id.editTextTextPasswordRegister1);

        if (!password.getText().toString().equals(confirmPassword.getText().toString())){
            Util.showToastMessage(this,"Password does not match");
        }

        if(email.getText().toString().equals("") || password.getText().toString().equals("") || confirmPassword.getText().toString().equals("")){
            Util.showToastMessage(this, "All input feilds are required");
        }
        else {

                long status = databaseHelper.insertUserData(email.getText().toString(), password.getText().toString());
                if (status == -1){
                    Util.showToastMessage(this, "User already exist");
                }else {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
        }
    }
}