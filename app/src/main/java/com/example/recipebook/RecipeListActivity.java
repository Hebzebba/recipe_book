package com.example.recipebook;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.recipe_list);

        GridView gridView= findViewById(R.id.gridView);
        SearchView searchView = findViewById(R.id.searchView);

        // Create a list of 10 items
        List<String> items = new ArrayList<>();
        for (int i=1; i <= 20; i++) {
            items.add("Item " + i);
        }

        // Create and set the adapter
        GridAdapter adapter =new GridAdapter(this, items);
        gridView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
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