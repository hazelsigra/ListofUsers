package com.example.listofusers;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.fonts.FontStyle;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import model.Users;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar2, toolbar3;
    private TextView textView4, textView5, textView6;
    private ImageButton editButton, deleteButton;
    private ArrayList<Users> listusers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar2 = findViewById(R.id.toolbar2);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);
        toolbar3 = findViewById(R.id.toolbar3);

        Intent intent = getIntent();
        listusers = intent.getParcelableArrayListExtra("arraylist");
        int position = intent.getIntExtra("position", -1);
        textView4.setText(listusers.get(position).getNama());
        textView5.setText(listusers.get(position).getUmur());
        textView6.setText(listusers.get(position).getAlamat());

        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, AddActivity.class);
                intent.putExtra("condition", "edit");
                intent.putExtra("position", position);
                intent.putParcelableArrayListExtra("arraylist", listusers);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(DetailActivity.this)
                        .setIcon(R.drawable.ic_fingerprint)
                        .setTitle("Konfirmasi")
                        .setMessage("Are you sure to delete " + listusers.get(position).getNama() + " data ?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listusers.remove(position);
                                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                                intent.putParcelableArrayListExtra("arraylist", listusers);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
    }
}