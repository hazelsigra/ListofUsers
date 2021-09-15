package com.example.listofusers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.Adapter;
import model.Users;

public class MainActivity extends AppCompatActivity implements OnCardListener{

    private FloatingActionButton floatingActionButton;
    private RecyclerView usersView;
    private TextView textView;
    static Adapter adapter;
    public static ArrayList<Users> listusers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        usersView = findViewById(R.id.usersView);
        textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        listusers = intent.getParcelableArrayListExtra("arraylist");
        if (listusers == null) {
            listusers = new ArrayList<>();
        }
        adapter = new Adapter(listusers, this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra("condition", "add");
                intent.putParcelableArrayListExtra("arraylist", listusers);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        usersView.setLayoutManager(manager);
        usersView.setAdapter(adapter);

        if (listusers.isEmpty()){
            textView.setVisibility(View.VISIBLE);
        }
        else{
            textView.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onCardClick(int position) {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        startActivity(intent);
    }
}