package com.e.crud_sqlite.utility;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.e.crud_sqlite.R;

public class ClientUtility {
    public static final int VERSION = 1;
    public static final String TABLE_CLIENTS = "clients";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_TELEPHONE = "telephone";

    public static final String SQL_CREATE_CLIENTS =
            "CREATE TABLE " + TABLE_CLIENTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_EMAIL + " TEXT," +
                    COLUMN_TELEPHONE + " TEXT)";

    public static final String SQL_DELETE_CLIENTS =
            "DROP TABLE IF EXISTS " + TABLE_CLIENTS;


    public static void showToast(LayoutInflater inflater, Context context, String message) {
        // Get the custom layout view.
        View toastView = inflater.inflate(R.layout.custom_toast_view, null);
        TextView textView = toastView.findViewById(R.id.customToastText);
        textView.setText(message);
        // Initiate the Toast instance.
        Toast toast = new Toast(context);
        // Set custom view in toast.
        toast.setView(toastView);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

}
