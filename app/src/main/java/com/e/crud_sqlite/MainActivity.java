package com.e.crud_sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.e.crud_sqlite.helper.ConnectionSQLiteHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.widget.TextView;

import java.sql.SQLData;

import static com.e.crud_sqlite.utility.ClientUtility.TABLE_CLIENTS;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_register:
                    mTextMessage.setText(R.string.register_btn);
                    return true;
                case R.id.navigation_delete:
                    mTextMessage.setText(R.string.delete_btn);
                    return true;
                case R.id.navigation_show:
                    mTextMessage.setText(R.string.view_btn);
                    return true;
                case R.id.navigation_search:
                    mTextMessage.setText(R.string.search_btn);
                    return true;
                case R.id.navigation_update:
                    mTextMessage.setText(R.string.update_btn);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ConnectionSQLiteHelper connectionSQLiteHelper = new ConnectionSQLiteHelper(this,TABLE_CLIENTS,null,1);
        SQLiteDatabase sqLiteDatabase = connectionSQLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
    }

}
