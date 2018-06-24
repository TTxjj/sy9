package com.example.a10711.sy9;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by 10711 on 2018/6/24.
 */

public class MyProvider extends ContentProvider {

    public static final int TABLE_DIR = 0;

    public static final int TABLE_ITEM = 1;

    private MyDatabaseHelper dbHelper;

    public static UriMatcher uriMatcher;

    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.a10711.sy9","contacts",TABLE_DIR);
        uriMatcher.addURI("com.example.a10711.sy9","contacts/#",TABLE_ITEM);
    }



    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch(uriMatcher.match(uri)){
            case TABLE_DIR:
                cursor = db.query("contacts",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case TABLE_ITEM:
                String phone = uri.getPathSegments().get(1);
                cursor = db.query("contacts",projection,"phone=?",new String[]{phone},null,null,sortOrder);
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch ((uriMatcher.match(uri))){
            case TABLE_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.a10711.sy9.contacts";
            case TABLE_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.a10711.sy9.contacts";
            default:
                break;

        }
        return null;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext(),"db",null,1);
        return true;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
