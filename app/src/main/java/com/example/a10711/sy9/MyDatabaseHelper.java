package com.example.a10711.sy9;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by 10711 on 2018/6/9.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper{

    public static  final String CREATE_CONTACTS = "create table Contacts ("
            +"phone varchar(11) primary key,"
            +"name text,"
            +"sex text)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext = context;

    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_CONTACTS);
        Toast.makeText(mContext, "Successful", Toast.LENGTH_SHORT).show();

    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("drop table if exists CONTACTS");
        onCreate(db);

    }


}
