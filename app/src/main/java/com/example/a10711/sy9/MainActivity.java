package com.example.a10711.sy9;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    ArrayAdapter<String> adapter;

    List<String> contactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  Button createDatabase = (Button)findViewById(R.id.button1);
     //   Button b = (Button)findViewById(R.id.button2);
        dbHelper = new MyDatabaseHelper(this ,"db1",null,2) ;

      //  createDatabase.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View v) {
      //          dbHelper.getWritableDatabase();
      //      }
      //  });

     /*  b.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
              Uri uri = Uri.parse("content://com.example.a10711.sy9/contacts");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
               Log.d("MainActivity",cursor.toString());
                if(cursor!=null){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String phone = cursor.getString(cursor.getColumnIndex("phone"));
                      //  String sex = cursor.getString(cursor.getColumnIndex("sex"));
                        Log.d("MainActivity",name);
                        Log.d("MainActivity",phone);
                    //    Log.d("MainActivity",sex);
                        Log.d("MainActivity","AAAAAA");

                    }
                    cursor.close();
                }
            }
        });*/
        ListView contactsView = (ListView)findViewById(R.id.contacts_view);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactsList);
        contactsView.setAdapter(adapter);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_CONTACTS},1);
        }else {
            readContacts();
        }
    }
    private  void readContacts(){
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,null,null,null);
            if(cursor != null){
                while(cursor.moveToNext()){
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add(displayName+"/n"+number);
                }
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();

            }
        }
    }

    public void onRequestPerminssionResult(int requestCode,String[] permissions,int[]grantResults){
        switch(requestCode){
            case 1:
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                readContacts();
            }else{
                Toast.makeText(this,"You denied the pwemission",Toast.LENGTH_SHORT).show();
            }
            break;
            default:
        }
    }
}
