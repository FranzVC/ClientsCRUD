package com.e.crud_sqlite.utility;

public class ClientUtility {
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

}
