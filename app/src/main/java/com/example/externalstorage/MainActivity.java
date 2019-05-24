package com.example.externalstorage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button internalcache, externalcache, externalprivate, externalpublic;
    EditText etdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1212);
        }
        internalcache = findViewById(R.id.internalCacheBtn);
        externalcache = findViewById(R.id.externalCacheBtn);
        externalprivate = findViewById(R.id.externalPrivateBtn);
        externalpublic = findViewById(R.id.externalPublicBtn);
        etdata = findViewById(R.id.editTxt1);
        internalcache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = getCacheDir();//find the location of the folder where file have to be saved
                Log.d("hello",file.getAbsolutePath());
                File file1 = new File(file, "Aariz.txt");
                writeData(file1);


            }
        });

        externalpublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File file1 = new File(file, "Aarizexternalpublic.txt");
                writeData(file1);
            }
        });
        externalprivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = getExternalFilesDir("external");
                File file1 = new File(file, "Aarizexternalprivate.txt");
                writeData(file1);
            }
        });

        externalcache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = getExternalCacheDir();
                File file1 = new File(file, "Aarizexternal.txt");
                writeData(file1);
            }
        });


    }

    private void writeData(File file) {
        File file1 = file;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(etdata.getText().toString().getBytes());
            Toast.makeText(this, file1.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
