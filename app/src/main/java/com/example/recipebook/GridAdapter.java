package com.example.recipebook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter  extends BaseAdapter implements Filterable {
    private final Context context;
    private final List<String> itemList;
    private   List<String> filteredItemList;

    public  GridAdapter(Context context, List<String> itemList){
        this.context = context;
        this.itemList = itemList;
        this.filteredItemList = itemList;
    }

    @Override
    public  int getCount(){
        return filteredItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }

        ImageView imageView= convertView.findViewById(R.id.imageView);
        TextView textView= convertView.findViewById(R.id.textView);

        // Set data based on the item
        textView.setText(itemList.get(position));
        // Optionally set an image based on the item
        imageView.setImageResource(R.drawable.bg1);
        convertView.setOnClickListener(v->{
            Intent intent = new Intent(context, RecipeDetailActivity.class);
            intent.putExtra("ITEM_NAME", filteredItemList.get(position));
            context.startActivity(intent);
        });
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString= constraint == null ? "" : constraint.toString().toLowerCase();
                if (charString.isEmpty()) {
                    filteredItemList = itemList;
                } else {
                    List<String> filteredList = new ArrayList<>();
                    for (String item : itemList) {
                        if (item.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(item);
                        }
                    }
                    filteredItemList = filteredList;
                }

                FilterResults filterResults=new FilterResults();
                filterResults.values = filteredItemList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredItemList = (List<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
