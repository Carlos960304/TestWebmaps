package com.example.projecttestwebmaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projecttestwebmaps.presenter.DbHelper;
import com.example.projecttestwebmaps.presenter.DbRegistration;
import com.example.projecttestwebmaps.view.ListRegistrationAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvListaRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListaRegistros = findViewById(R.id.rvRegistros);
        rvListaRegistros.setLayoutManager(new LinearLayoutManager(this));

        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        if(db != null) {
            Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "ERROR AL CREAR BASE DE DATOS", Toast.LENGTH_SHORT).show();
        }

        DbRegistration dbRegistration = new DbRegistration(MainActivity.this);
        dbRegistration.insertData();

        ListRegistrationAdapter adapter = new ListRegistrationAdapter(dbRegistration.showData());
        rvListaRegistros.setAdapter(adapter);
    }
}