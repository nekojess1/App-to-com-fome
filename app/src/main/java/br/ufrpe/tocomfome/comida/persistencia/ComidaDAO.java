package br.ufrpe.tocomfome.comida.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.tocomfome.comida.dominio.Comida;
import br.ufrpe.tocomfome.infra.persistencia.DBHelper;
import static br.ufrpe.tocomfome.infra.persistencia.DBHelper.TABELA_COMIDA;


public class ComidaDAO {

    private static DBHelper dbHelper;

    public ComidaDAO(Context context) {

        dbHelper = new DBHelper(context);
    }

    public long cadastrar(Comida comida) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_NOME_COMIDA, comida.getNome());
        values.put(DBHelper.COL_DESCRICAO, comida.getDescricao());
        values.put(DBHelper.COL_FOTO_COMIDA, comida.getFoto());

        long id = db.insert(TABELA_COMIDA, null, values);
        db.close();
        return id;

    }


    private Comida criarComida(Cursor cursor) {
        Comida result = new Comida();
        result.setId(cursor.getInt(0));
        result.setNome(cursor.getString(1));
        result.setDescricao(cursor.getString(2));
        result.setFoto(cursor.getBlob(3));

        return result;
    }

    public List<Comida> getAllComida() {
        List<Comida> comidaArrayList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = " SELECT * " +
                " FROM " + TABELA_COMIDA;
        String[] args = {};
        Cursor cursor = db.rawQuery(query, args);
        Comida comida = null;
        if (cursor.moveToFirst()) {
            do {
                comida = criarComida(cursor);
                comidaArrayList.add(comida);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
            return comidaArrayList;
        }
        cursor.close();
        db.close();
        return comidaArrayList;
    }

    public void updateDescricaoComida(Comida comida) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("descricao", comida.getDescricao());
        db.update("Tabela_Comida", valores, "id = ?", new String[]{String.valueOf(comida.getId())});
        db.close();
    }

    public static void alteraFotoComida(Comida comida){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("foto", comida.getFoto());
        db.update(TABELA_COMIDA,values, "id = ?",
                new String[]{String.valueOf(comida.getId())});
        db.close();

    }

    private Comida carregarObjeto(String query, String[] args) {
        SQLiteDatabase leitorBanco = dbHelper.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Comida comida = null;
        if (cursor.moveToNext()) {
            comida = criarComida(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return comida;
    }
    public Comida getComidaById(long id) {
        String query = "SELECT * FROM Tabela_Comida " +
                "WHERE id = ?";
        String[] args = {String.valueOf(id)};
        return this.carregarObjeto(query, args);
    }
}