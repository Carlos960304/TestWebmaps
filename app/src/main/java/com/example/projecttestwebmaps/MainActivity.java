package com.example.projecttestwebmaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.projecttestwebmaps.presenter.DbHelper;
import com.example.projecttestwebmaps.presenter.DbRegistration;
import com.example.projecttestwebmaps.view.ListRegistrationAdapter;
import com.example.projecttestwebmaps.view.LoginActivity;
import com.example.projecttestwebmaps.view.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    RecyclerView rvListaRegistros;
    ListRegistrationAdapter adapter;
    SearchView svSearchCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListaRegistros = findViewById(R.id.rvRegistros);
        svSearchCountry = findViewById(R.id.svSearchCountry);

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

        adapter = new ListRegistrationAdapter(dbRegistration.showData());
        rvListaRegistros.setAdapter(adapter);

        svSearchCountry.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filterCountry(newText);
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_log_out, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iLogOut: {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}