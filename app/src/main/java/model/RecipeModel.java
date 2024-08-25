package model;

import androidx.annotation.NonNull;

public class RecipeModel {
    private int Id;
    private String name;
    private String imageUrl;
    private String instructions;


    public RecipeModel(String name, String imageUrl, String instructions) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.instructions = instructions;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }


    @NonNull
    @Override
    public String toString() {
        return "RecipeModel{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
