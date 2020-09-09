package com.example.nmpetry.myapp.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nmpetry.myapp.connection.DadosOpenHelper;
import com.example.nmpetry.myapp.models.Palestrante;

import java.util.ArrayList;

import static com.example.nmpetry.myapp.tools.Ferramentas.ultimoErro;

public class PalestranteDao {

    private SQLiteDatabase conexao;
    private static final String TABELA = "palestrantes";
    private String TAG = "PalestranteDao";

    public PalestranteDao(Context ctx) {

        DadosOpenHelper db = new DadosOpenHelper(ctx);

        conexao = db.getWritableDatabase();
    }
    public boolean inserir(Palestrante palestrante)
    {
        try {
            ContentValues valores = new ContentValues();
            valores.put("nome", palestrante.getNome());

            long result = conexao.insertOrThrow(TABELA, null, valores);
            if (result >0)
            {
                Log.i(TAG, "INSERIDO COM SUCESSO");
                return true;
            }
            return false;
        }
        catch (Exception ex)
        {
            ultimoErro=ex.getMessage();
            Log.e(TAG, ex.getMessage());
            return false;
        }
    }

    public boolean excluir(int codigo)
    {

        try
        {
            String[] parametros = new String[1];
            parametros[0] = String.valueOf(codigo);
            int resul = conexao.delete(TABELA, "codigo = ?", parametros);
            if (resul > 0)
            {
                Log.i(TAG,"EXCLUÍDO COM SUCESSO");
                return true;
            }
            return false;
        }
        catch (SQLException ex)
        {
            ultimoErro=ex.getMessage();
            Log.e(TAG, ex.getMessage());
            return false;
        }
    }

    public boolean alterar(Palestrante palestrante){
        try {
            ContentValues valores = new ContentValues();
            valores.put("nome", palestrante.getNome());

            String[] parametros = new String[1];

            parametros[0] = String.valueOf(palestrante.getId());

            long result = conexao.update(TABELA, valores, "codigo = ? ", parametros);
            if (result>0)
            {
                Log.i(TAG, "ALTERADO COM SUCESSO");
                return true;
            }
            return false;
        }
        catch (Exception ex)
        {
            ultimoErro=ex.getMessage();
            Log.e(TAG, ex.getMessage());
            return false;
        }
    }
    public ArrayList<Palestrante> buscarTodos(){

        try {
            ArrayList<Palestrante> lista = new ArrayList<Palestrante>();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM " + TABELA);
            Cursor resultado = conexao.rawQuery(sql.toString(), null);

            if (resultado.getCount() > 0) {
                resultado.moveToFirst();
                Palestrante palestrante;
                do {
                    palestrante = new Palestrante();
                    palestrante.setId(resultado.getInt(resultado.getColumnIndexOrThrow("codigo")));
                    palestrante.setNome(resultado.getString(resultado.getColumnIndexOrThrow("nome")));


                    lista.add(palestrante);
                } while (resultado.moveToNext());
            }
            return lista;
        }
        catch (Exception ex)
        {
            ultimoErro=ex.getMessage();
            Log.e(TAG, ex.getMessage());
            return null;
        }
    }

    public Palestrante buscarPalestrante(int codigo){
        Palestrante palestrante = new Palestrante();
        try
        {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT * FROM " + TABELA);
            sql.append(" WHERE codigo = " + codigo);

            Cursor resultado = conexao.rawQuery(sql.toString(), null);

            if(resultado.getCount() > 0)
            {
                resultado.moveToFirst();
                palestrante.setId(resultado.getInt(resultado.getColumnIndex("codigo")));
                palestrante.setNome(resultado.getString(resultado.getColumnIndex("nome")));
            }
        }
        catch (Exception ex)
        {
            ultimoErro=ex.getMessage();
            Log.e(TAG, ex.getMessage());
            return null;
        }
        return palestrante;
    }

}
