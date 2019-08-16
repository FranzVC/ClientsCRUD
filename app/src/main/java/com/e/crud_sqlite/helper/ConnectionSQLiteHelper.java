package com.e.crud_sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.e.crud_sqlite.model.Client;
import com.e.crud_sqlite.utility.ClientUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.e.crud_sqlite.utility.ClientUtility.COLUMN_EMAIL;
import static com.e.crud_sqlite.utility.ClientUtility.COLUMN_ID;
import static com.e.crud_sqlite.utility.ClientUtility.COLUMN_NAME;
import static com.e.crud_sqlite.utility.ClientUtility.COLUMN_TELEPHONE;
import static com.e.crud_sqlite.utility.ClientUtility.SQL_CREATE_CLIENTS;
import static com.e.crud_sqlite.utility.ClientUtility.TABLE_CLIENTS;

public class ConnectionSQLiteHelper extends SQLiteOpenHelper {

    public ConnectionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_CLIENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTS);
        onCreate(sqLiteDatabase);
    }

    public long insertClient(String name, String email, String telephone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_NAME, name);
        cValues.put(COLUMN_EMAIL, email);
        cValues.put(COLUMN_TELEPHONE, telephone);

        long newRowId = db.insert(TABLE_CLIENTS, null, cValues);
        db.close();
        return newRowId;
    }

    public ArrayList<Client> getClients() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Client> clientList = new ArrayList<>();
        String query = "SELECT name, email, telephone FROM " + TABLE_CLIENTS;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Client client = new Client( cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                                        cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
                                        cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE)));
            clientList.add(client);
        }
        cursor.close();
        return clientList;
    }

    public ArrayList<Client> getClientById(int clientId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Client> clientList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_CLIENTS, new String[]{COLUMN_NAME, COLUMN_EMAIL, COLUMN_TELEPHONE},
                            COLUMN_ID + "=?", new String[]{String.valueOf(clientId)}, null, null, null, null);
        if (cursor.moveToNext()) {
            Client client = new Client( cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                                        cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
                                        cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE)));
            clientList.add(client);
        }
        cursor.close();
        return clientList;
    }

    public boolean deleteClient(int clientId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int flag = db.delete(TABLE_CLIENTS, COLUMN_ID + " = ?", new String[]{String.valueOf(clientId)});
        db.close();
        return (flag > 0);
    }

    public boolean updateClient(String name, String email, String telephone, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(COLUMN_NAME,name);
        cValues.put(COLUMN_EMAIL, email);
        cValues.put(COLUMN_TELEPHONE, telephone);
        int flag = db.update(TABLE_CLIENTS, cValues, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        return (flag > 0);
    }
}