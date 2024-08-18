package com.example.recipebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ImageView detailImageView = findViewById(R.id.detailImageView);
        TextView detailTextView = findViewById(R.id.detailTextView);
        Intent  intent  = getIntent();
        String itemName = intent.getStringExtra("ITEM_NAME");
        detailImageView.setImageResource(R.drawable.bg1);
        detailTextView.setText(itemName);
    }

    public  void  backToRecipeList(View v){
        Intent intent = new Intent(this, RecipeListActivity.class);
        this.startActivity(intent);
    }
}