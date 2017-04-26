package com.cclz.myapp_170426_05;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    String outFilename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outFilename = getFilesDir().getAbsolutePath() + File.separator + "test.sqlite";
        try{
            copyDatabase();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void click1(View v){
        SQLiteDatabase db;
        db = SQLiteDatabase.openDatabase(outFilename, null, MODE_PRIVATE);
        Cursor c = db.rawQuery("Select * from phone", null);
        c.moveToFirst();
        String n = c.getString(1);
        Toast.makeText(MainActivity.this, n, Toast.LENGTH_SHORT).show();
        db.close();
    }

    private void copyDatabase() throws IOException{
        // Path to the empty database.

        // Open the empty database as the output stream.
        OutputStream outputDatabase=new FileOutputStream(outFilename);
        // Transfer bytes from the input file to the output file.
        byte[] buffer=new byte[1024];
        InputStream is=getResources().openRawResource(R.raw.test);
        int length;
        while((length = is.read(buffer)) > 0){
            outputDatabase.write(buffer, 0, length);
        }
        is.close();
        outputDatabase.flush();
        outputDatabase.close();
    }
}
