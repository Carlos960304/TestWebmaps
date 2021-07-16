package com.example.projecttestwebmaps.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.projecttestwebmaps.model.Registration;

import java.util.ArrayList;
import java.util.Random;

public class DbRegistration extends DbHelper {

    Context context;

    public DbRegistration(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public void insertData() {

        Cursor cursorGender, cursorState, cursorCountry;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            for(int i=0;i<100;i++) {
                Random random = new Random();
                int resultCountry = random.nextInt(5) + 1;
                int resultState = random.nextInt(5) + 1;
                int resultGender = random.nextInt(2) + 1;

                cursorCountry = db.rawQuery("SELECT nombre FROM " + TABLE_COUNTRIES + " WHERE id_pais = " + resultCountry, null);
                cursorState = db.rawQuery("SELECT nombre FROM " + TABLE_STATES + " WHERE id_estado = " + resultState + " AND id_pais = " + resultCountry, null);
                cursorGender = db.rawQuery("SELECT sexo FROM " + TABLE_GENDER + " WHERE id_genero = " + resultGender, null);

                ContentValues values = new ContentValues();

                if(cursorCountry.moveToFirst()) {
                    values.put("pais", cursorCountry.getString(0));
                }
                if (cursorState.moveToFirst()) {
                    values.put("estado", cursorState.getString(0));
                }

                if(cursorGender.moveToFirst()) {
                    values.put("genero", cursorGender.getString(0));
                }

                if(values.get("pais") != null && values.get("estado") != null && values.get("genero") != null) {
                    long id = db.insert(TABLE_REGISTRATION, null, values);
                }
            }
        } catch (Exception ex) {
            ex.toString();
        }
    }

    public ArrayList<Registration> showData() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Registration> listRegistration = new ArrayList<>();
        Registration registration = null;
        Cursor cursorRegistration = null;

        cursorRegistration = db.rawQuery("SELECT * FROM " + TABLE_REGISTRATION, null);

        if(cursorRegistration.moveToFirst()) {
            do {
                registration = new Registration();
                registration.setUsuario(cursorRegistration.getInt(0));
                registration.setCountry(cursorRegistration.getString(1));
                registration.setState(cursorRegistration.getString(2));
                registration.setGender(cursorRegistration.getString(3));
                listRegistration.add(registration);
            } while(cursorRegistration.moveToNext());
        }

        cursorRegistration.close();

        return listRegistration;
    }
}
