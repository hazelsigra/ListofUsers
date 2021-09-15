package com.example.listofusers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import model.Loading;
import model.Users;

public class AddActivity extends AppCompatActivity {

    private TextInputLayout namaText, umurText, alamatText;
    private Button saveButton;
    private ArrayList<Users> listusers;
    private Toolbar toolbar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        namaText = findViewById(R.id.namaText);
        umurText = findViewById(R.id.umurText);
        alamatText = findViewById(R.id.alamatText);
        saveButton = findViewById(R.id.saveButton);
        toolbar3 = findViewById(R.id.toolbar3);

        toolbar3.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        listusers = intent.getParcelableArrayListExtra("arraylist");
        if (listusers == null){
            listusers = new ArrayList<>();
        }
        String condition = intent.getStringExtra("condition");


        if (condition.equalsIgnoreCase("add")){
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Loading loading = new Loading(AddActivity.this);
                    String nama = namaText.getEditText().getText().toString().trim();
                    String umur = umurText.getEditText().getText().toString().trim();
                    String alamat = alamatText.getEditText().getText().toString().trim();
                    Users users = new Users(nama, umur, alamat);
                    listusers.add(users);
                    loading.startLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loading.dismissDialog();
                            Intent intent = new Intent(AddActivity.this, MainActivity.class);
                            intent.putExtra("arraylist", listusers);
                            startActivity(intent);
                            finish();
                        }
                    }, 1000);
                }
            });
        }else if(condition.equalsIgnoreCase("edit")){
            toolbar3.setTitle("Edit User");
            saveButton.setText("Update Data");
            namaText.getEditText().setText(listusers.get(position).getNama());
            umurText.getEditText().setText(listusers.get(position).getUmur());
            alamatText.getEditText().setText(listusers.get(position).getAlamat());
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Loading loading = new Loading(AddActivity.this);
                    String nama = namaText.getEditText().getText().toString().trim();
                    String umur = umurText.getEditText().getText().toString().trim();
                    String alamat = alamatText.getEditText().getText().toString().trim();
                    Users users = new Users(nama, umur, alamat);
                    listusers.set(position, users);
                    loading.startLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loading.dismissDialog();
                            Intent intent = new Intent(AddActivity.this, MainActivity.class);
                            intent.putExtra("arraylist", listusers);
                            startActivity(intent);
                            finish();
                        }
                    }, 1000);
                }
            });
        }
    }
}