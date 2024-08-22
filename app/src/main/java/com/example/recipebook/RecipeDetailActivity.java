package com.example.recipebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ImageView detailImageView = findViewById(R.id.detailImageView);
        TextView detailTextView = findViewById(R.id.detailTextView);
        TextView instructionsView = findViewById(R.id.instructionsView);


        Intent  intent  = getIntent();
        String itemImageURl = intent.getStringExtra("ITEM_IMAGE");
        String itemName = intent.getStringExtra("ITEM_NAME");
        String instructions= intent.getStringExtra("INSTRUCTIONS");

        Picasso.get()
                .load(itemImageURl)
                .into(detailImageView);
        detailTextView.setText(itemName);
        instructionsView.setText(instructions);
    }

    public  void  backToRecipeList(View v){
        Intent intent = new Intent(this, RecipeListActivity.class);
        this.startActivity(intent);
    }
}