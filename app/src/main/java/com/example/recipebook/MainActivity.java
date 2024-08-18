package com.example.recipebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View v) {
        String email = "test@gmail.com";
        String password = "test";

        EditText emailField = findViewById(R.id.editTextTextEmailAddress2);
        EditText passwordField = findViewById(R.id.editTextTextPassword);

        if (emailField.getText().toString().equals(email) && passwordField.getText().toString().equals(password)) {
            Intent intent = new Intent(this, RecipeListActivity.class);
            this.startActivity(intent);
        }else {
            Toast toast = new Toast(this);
            toast.setText("Incorrect username or password");
            toast.show();
        }
    }
}