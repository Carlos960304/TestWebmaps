package com.example.projecttestwebmaps.presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 39;
    private static final String DATABASE_NAME = "registro.db";
    public static final String TABLE_REGISTRATION = "t_registros";
    public static final String TABLE_COUNTRIES = "t_countries";
    public static final String TABLE_STATES = "t_states";
    public static final String TABLE_GENDER = "t_gender";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        int i;
        final Object[][] countryList = {{1, "España"}, {2, "Estados Unidos"}, {3, "Francia"}, {4, "Italia"}, {5, "China"}};
        final Object[][] stateList = {{1, 1, "Madrid"}, {1, 2, "Sevilla"}, {1, 3, "Málaga"}, {1, 4, "Cádiz"}, {1, 5, "Granada"},
                {2, 1, "Nueva York"}, {2, 2, "California"}, {2, 3, "Texas"}, {2, 4, "Illinois"}, {2, 5, "Arizona"},
                {3, 1, "París"}, {3, 2, "Lille"}, {3, 3, "Marsella"}, {3, 4, "Burdeos"}, {3, 5, "Lyon"},
                {4, 1, "Milán"}, {4, 2, "Turín"}, {4, 3, "Roma"}, {4, 4, "Venecia"}, {4, 5, "Florencia"},
                {5, 1, "Shandong"}, {5, 2, "Sichuan"}, {5, 3, "Zhejiang"}, {5, 4, "Guizhou"}, {5, 5, "Fujian"}
        };
        final String[][] genderList = {{"M", "Masculino"}, {"F", "Femenino"}};

        db.execSQL("CREATE TABLE " + TABLE_REGISTRATION + "(" +
                "usuario INTEGER PRIMARY KEY," +
                "pais TEXT NOT NULL," +
                "estado TEXT NOT NULL," +
                "genero CHAR NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_COUNTRIES + "(" +
                "id_pais INTEGER PRIMARY KEY," +
                "nombre TEXT NOT NULL)");

        for(i=0;i<countryList.length;i++) {
            db.execSQL("INSERT INTO " + TABLE_COUNTRIES + " ('id_pais', 'nombre') VALUES ( " +
                    countryList[i][0] + ", '" + countryList[i][1] + "')");
        }

        db.execSQL("CREATE TABLE " + TABLE_STATES + "(" +
                "id_pais INTEGER NOT NULL," +
                "id_estado INTEGER NOT NULL," +
                "nombre TEXT NOT NULL)");

        for(i=0;i<stateList.length;i++) {
            db.execSQL("INSERT INTO " + TABLE_STATES + " ('id_pais', 'id_estado', 'nombre') VALUES ( " +
                    stateList[i][0] + ", " + stateList[i][1] + ", '" + stateList[i][2] + "')");
        }

        db.execSQL("CREATE TABLE " + TABLE_GENDER + "(" +
                "id_genero INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sexo CHAR NOT NULL)");

        for(i=0;i<genderList.length;i++) {
            db.execSQL("INSERT INTO " + TABLE_GENDER + " ('sexo') VALUES ( '"  +
                    genderList[i][0] + "')");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GENDER);
        onCreate(db);
    }
}
