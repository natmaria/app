    package com.example.nmpetry.myapp.controller;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.SQLException;
    import android.database.sqlite.SQLiteDatabase;
    import android.util.Log;

    import com.example.nmpetry.myapp.connection.DadosOpenHelper;
    import com.example.nmpetry.myapp.models.Evento;
    import java.util.ArrayList;

    import static com.example.nmpetry.myapp.tools.Ferramentas.*;

    public class EventoDao {

        private SQLiteDatabase conexao;
        private static final String TABELA = "eventos";
        private String TAG = "EventoDao";

        public EventoDao(Context ctx) {

            DadosOpenHelper db = new DadosOpenHelper(ctx);

            conexao = db.getWritableDatabase();
        }

        public boolean inserir(Evento evento)
        {
            try {
                ContentValues valores = new ContentValues();
                valores.put("titulo", evento.getTitulo());
                valores.put("descricao", evento.getDescricao());
                valores.put("data_evento", evento.getData());
                valores.put("vagas", evento.getVagas());
                valores.put("id_palestrante", evento.getId_palestrante());

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

        public boolean alterar(Evento evento){
            try {
                ContentValues valores = new ContentValues();
                valores.put("titulo", evento.getTitulo());
                valores.put("descricao", evento.getDescricao());
                valores.put("data_evento", evento.getData());
                valores.put("vagas", evento.getVagas());
                valores.put("id_palestrante", evento.getId_palestrante());

                String[] parametros = new String[1];

                parametros[0] = String.valueOf(evento.getId());

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

        public ArrayList<Evento> buscarTodos(){

           try {
               ArrayList<Evento> lista = new ArrayList<Evento>();
               StringBuilder sql = new StringBuilder();
               sql.append("SELECT * FROM " + TABELA);
               Cursor resultado = conexao.rawQuery(sql.toString(), null);

               if (resultado.getCount() > 0) {
                   resultado.moveToFirst();
                   Evento evento;
                   do {
                       evento = new Evento();
                       evento.setId(resultado.getInt(resultado.getColumnIndexOrThrow("codigo")));
                       evento.setTitulo(resultado.getString(resultado.getColumnIndexOrThrow("titulo")));
                       evento.setDescricao(resultado.getString(resultado.getColumnIndexOrThrow("descricao")));
                       evento.setData(resultado.getString(resultado.getColumnIndexOrThrow("data_evento")));
                       evento.setVagas(resultado.getInt(resultado.getColumnIndexOrThrow("vagas")));
                       evento.setId_palestrante(resultado.getInt(resultado.getColumnIndexOrThrow("id_palestrante")));

                       lista.add(evento);
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

        public Evento buscarEvento(int codigo){
            Evento evento = new Evento();
           try
           {

                StringBuilder sql = new StringBuilder();
                sql.append(" SELECT * FROM " + TABELA);
                sql.append(" WHERE codigo = " + codigo);

                Cursor resultado = conexao.rawQuery(sql.toString(), null);

                if(resultado.getCount() > 0)
                {
                    resultado.moveToFirst();
                    evento.setId(resultado.getInt(resultado.getColumnIndex("codigo")));
                    evento.setTitulo(resultado.getString(resultado.getColumnIndex("titulo")));
                    evento.setDescricao(resultado.getString(resultado.getColumnIndexOrThrow("descricao")));
                    evento.setData(resultado.getString(resultado.getColumnIndexOrThrow("data_evento")));
                    evento.setVagas(resultado.getInt(resultado.getColumnIndexOrThrow("vagas")));
                    evento.setId_palestrante(resultado.getInt(resultado.getColumnIndexOrThrow("id_palestrante")));
                }
            }
               catch (Exception ex)
            {
                ultimoErro=ex.getMessage();
                Log.e(TAG, ex.getMessage());
                return null;
            }
            return evento;
        }
    }
