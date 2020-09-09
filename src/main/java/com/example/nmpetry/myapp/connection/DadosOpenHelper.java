package com.example.nmpetry.myapp.connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DadosOpenHelper extends SQLiteOpenHelper {

    private static final String NM_BANCO = "dados.db";
    private static final int VERSION = 2;
    private static final String TABELA_EVENTOS = "eventos";
    private static final String TABELA_PALESTRANTES = "palestrantes";
    private String TAG = "DadosOpenHelper";

    public DadosOpenHelper(Context context) {
        super(context, NM_BANCO, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        //Cria as tabelas do banco de dados
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" CREATE TABLE IF NOT EXISTS " + TABELA_EVENTOS + "( ");
            sql.append(" codigo INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append(" titulo VARCHAR(50) NOT NULL, ");
            sql.append(" descricao VARCHAR(150) NOT NULL, ");
            sql.append(" data_evento VARCHAR(20) NOT NULL, ");
            sql.append(" vagas INTEGER NOT NULL, ");
            sql.append(" id_palestrante INTEGER NOT NULL ");
            sql.append(" ); ");
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append(" CREATE TABLE IF NOT EXISTS " + TABELA_PALESTRANTES + "( ");
            sql.append(" codigo INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append(" nome VARCHAR(150) NOT NULL ");
            sql.append(" ); ");
            db.execSQL(sql.toString());
        }
        catch (Exception ex)
        {
            Log.e(TAG, ex.getMessage());
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try
        {
            StringBuilder sql = new StringBuilder();
            if (newVersion==2)
            {
                sql = new StringBuilder();
                sql.append(" CREATE TABLE IF NOT EXISTS " + TABELA_PALESTRANTES + "( ");
                sql.append(" codigo INTEGER PRIMARY KEY AUTOINCREMENT, ");
                sql.append(" nome VARCHAR(150) NOT NULL ");
                sql.append(" ); ");
                db.execSQL(sql.toString());
            }
        }
        catch (Exception ex)
        {
            Log.e(TAG, ex.getMessage());
        }

    }

}
