package com.example.recipebook;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recipebook.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.RecipeModel;

public class RecipeListActivity extends AppCompatActivity {

    private final List<RecipeModel> items = new ArrayList<>();
    private final List<RecipeModel> dbListItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);

        GridView gridView = findViewById(R.id.gridView);
        SearchView searchView = findViewById(R.id.searchView);


//        items.add(new RecipeModel("Spaghetti Carbonara", "https://images.unsplash.com/photo-1633337474564-1d9478ca4e2e?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8JTIyU3BhZ2hldHRpJTIwQ2FyYm9uYXJhJTIyfGVufDB8fDB8fHww", "Cook spaghetti. In a separate pan, cook pancetta until crispy. Beat eggs and mix with Parmesan cheese. Combine cooked pasta with pancetta, remove from heat, and stir in egg mixture. Serve with black pepper."));
//        items.add(new RecipeModel("Chicken Alfredo", "https://images.unsplash.com/photo-1670508142255-f119391c4213?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8JTIyQ2hpY2tlbiUyMEFsZnJlZG8lMjJ8ZW58MHx8MHx8fDA%3D", "Cook fettuccine. In a pan, cook chicken until browned. Add garlic and sauté. Stir in cream and Parmesan cheese until thickened. Combine with pasta and serve."));
//        items.add(new RecipeModel("Beef Tacos", "https://images.unsplash.com/photo-1640983743761-4f0e0204bc58?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8QmVlZiUyMFRhY29zfGVufDB8fDB8fHww", "Cook ground beef with taco seasoning. Warm tortillas. Fill with beef, lettuce, cheese, and salsa. Serve with lime wedges."));
//        items.add(new RecipeModel("Vegetable Stir Fry", "https://images.unsplash.com/photo-1543826173-cfe2ca17577d?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8VmVnZXRhYmxlJTIwU3RpciUyMEZyeXxlbnwwfHwwfHx8MA%3D%3D", "Heat oil in a wok. Stir-fry broccoli, bell peppers, and carrots. Add soy sauce and a touch of sugar. Serve with steamed rice."));
//        items.add(new RecipeModel("Margherita Pizza", "https://images.unsplash.com/photo-1649688423692-308d2fc1027d?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8TWFyZ2hlcml0YSUyMFBpenphfGVufDB8fDB8fHww", "Roll out pizza dough. Spread tomato sauce, add mozzarella, and top with basil. Bake in a preheated oven at 475°F (245°C) for 10-12 minutes."));
//        items.add(new RecipeModel("Greek Salad", "https://images.unsplash.com/photo-1657014193301-54e4442fa2fe?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8R3JlZWslMjBTYWxhZHxlbnwwfHwwfHx8MA%3D%3D", "Chop cucumbers, tomatoes, and red onions. Add olives and feta cheese. Toss with olive oil, lemon juice, salt, and pepper."));
//        items.add(new RecipeModel("Chicken Curry", "https://images.unsplash.com/photo-1708658767771-718c38b75a9b?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8Q2hpY2tlbiUyMEN1cnJ5fGVufDB8fDB8fHww", "Sauté onions, garlic, and ginger. Add chicken pieces and cook until browned. Stir in curry powder, tomatoes, and coconut milk. Simmer until chicken is tender. Serve with rice."));
//        items.add(new RecipeModel("Pancakes", "https://images.unsplash.com/photo-1499638309848-e9968540da83?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8UGFuY2FrZXN8ZW58MHx8MHx8fDA%3D", "Mix flour, sugar, baking powder, and salt. Add milk, eggs, and melted butter. Cook on a hot griddle until bubbles form. Flip and cook until golden brown. Serve with syrup."));
//        items.add(new RecipeModel("Caesar Salad", "https://images.unsplash.com/photo-1512852939750-1305098529bf?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8Q2Flc2FyJTIwU2FsYWR8ZW58MHx8MHx8fDA%3D", "Toss romaine lettuce with Caesar dressing, croutons, and Parmesan cheese. Top with grilled chicken if desired."));
//        items.add(new RecipeModel("Tomato Soup", "https://images.unsplash.com/photo-1629978448078-c94a0ab6500f?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8VG9tYXRvJTIwU291cHxlbnwwfHwwfHx8MA%3D%3D", "Sauté onions and garlic. Add canned tomatoes, broth, and basil. Simmer and blend until smooth. Serve with a drizzle of cream."));

//        Log.d("RECIPE", Arrays.toString(items.toArray()));

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
//        databaseHelper.dropTable();

//        for(RecipeModel recipeModel : items){
//            databaseHelper.insertData(recipeModel.getName(), recipeModel.getImageUrl(), recipeModel.getInstructions());
//        }


        Cursor cursor = databaseHelper.getData();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String image_url = cursor.getString(cursor.getColumnIndex("image_url"));
                @SuppressLint("Range") String instruction = cursor.getString(cursor.getColumnIndex("instruction"));
                dbListItems.add(new RecipeModel(name, image_url, instruction));

//                Log.d("MainActivity", "Name: " + name + "ImageLink: "+image_url +", instruction: " + instruction);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Create and set the adapter
        GridAdapter adapter = new GridAdapter(this, dbListItems);
        gridView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}