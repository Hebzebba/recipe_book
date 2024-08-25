package com.example.recipebook.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import model.RecipeModel;
import model.UserModel;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name and Version
    private static final String DATABASE_NAME = "recipe_book.db";
    private static final int DATABASE_VERSION = 1;

    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Table Names
    private static final String RECIPE_TABLE = "recipe";
    private static final String USER_TABLE = "users";

    // Column Names (RECIPE)
    private static final String RECIPE_COLUMN_ID = "id";
    private static final String RECIPE_COLUMN_NAME = "name";
    private static final String RECIPE_IMAGE_URL = "image_url";
    private static final String RECIPE_INSTRUCTION = "instruction";

    // Column Names (USER)
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";

    // SQL Query to Create Table (Recipe)
    private static final String CREATE_RECIPE_TABLE =
            "CREATE TABLE " + RECIPE_TABLE + " (" +
                    RECIPE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RECIPE_COLUMN_NAME + " TEXT, " +
                    RECIPE_IMAGE_URL + " TEXT, " +
                    RECIPE_INSTRUCTION + " TEXT)";

    // SQL Query to Create Table (User)
    private static final String CREATE_USER_TABLE =
            "CREATE TABLE " + USER_TABLE + " (" +
                    USER_EMAIL + " TEXT PRIMARY KEY, " +
                    USER_PASSWORD + " TEXT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table when the database is created
        db.execSQL(CREATE_RECIPE_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table and create a new one if the database version is updated
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    public long insertRecipeData(String name, String imageUrl, String instructions) {
        long result = -1;
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(RECIPE_COLUMN_NAME, name);
            values.put(RECIPE_IMAGE_URL, imageUrl);
            values.put(RECIPE_INSTRUCTION, instructions);
            result = db.insert(RECIPE_TABLE, null, values);

            if (result == -1) {
                throw new SQLException("Failed to insert row into " + RECIPE_TABLE);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return result;
    }

    public long insertUserData(String email, String password) {
        long result = -1;
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(USER_EMAIL, email);
            values.put(USER_PASSWORD, password);
            result = db.insert(USER_TABLE, null, values);

            if (result == -1) {
                throw new SQLException("Failed to insert row into " + USER_TABLE);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return result;
    }

    public List<RecipeModel> getRecipeData() {
        List<RecipeModel> dbListItems = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = this.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM " + RECIPE_TABLE, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(RECIPE_COLUMN_NAME));
                    @SuppressLint("Range") String imageUrl = cursor.getString(cursor.getColumnIndex(RECIPE_IMAGE_URL));
                    @SuppressLint("Range") String instruction = cursor.getString(cursor.getColumnIndex(RECIPE_INSTRUCTION));
                    dbListItems.add(new RecipeModel(name, imageUrl, instruction));
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }

        return dbListItems;
    }

    public UserModel getUserDataById(String userEmail) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        UserModel userModel = null;

        try {
            db = this.getReadableDatabase();
            cursor = db.query(USER_TABLE, new String[]{USER_EMAIL, USER_PASSWORD},
                    USER_EMAIL + "=?", new String[]{userEmail}, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(USER_EMAIL));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
                userModel = new UserModel(email, password);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }

        return userModel;
    }

    public void dropTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
    }
}
