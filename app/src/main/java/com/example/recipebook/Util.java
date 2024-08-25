package com.example.recipebook;

import android.content.Context;
import android.widget.Toast;

public class Util {
    public static void showToastMessage(Context context, String message){
        Toast toast = new Toast(context);
        toast.setText(message);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
