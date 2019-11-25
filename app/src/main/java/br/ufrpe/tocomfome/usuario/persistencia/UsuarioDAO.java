package br.ufrpe.tocomfome.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.ufrpe.tocomfome.infra.persistencia.DBHelper;
import br.ufrpe.tocomfome.usuario.dominio.Usuario;

import static br.ufrpe.tocomfome.infra.persistencia.DBHelper.COL_EMAIL_USUARIO;
import static br.ufrpe.tocomfome.infra.persistencia.DBHelper.COL_SENHA_USUARIO;
import static br.ufrpe.tocomfome.infra.persistencia.DBHelper.TABELA_USUARIO;


public class UsuarioDAO  {

    private DBHelper dbHelper;

    public UsuarioDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long cadastrar(Usuario usuario){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_NOME_USUARIO, usuario.getNome());
        values.put(COL_EMAIL_USUARIO, usuario.getEmail());
        values.put(COL_SENHA_USUARIO, usuario.getSenha());

        long id = db.insert(TABELA_USUARIO, null, values);
        db.close();
        return id;

    }

    public Usuario consultar(String email,String senha){
        Usuario result = null;
        String query =
                " SELECT * " +
                        " FROM " + TABELA_USUARIO +
                        " WHERE " + COL_EMAIL_USUARIO + " = ? " +
                        " AND " + COL_SENHA_USUARIO + " = ? ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{email,senha});
        if (cursor.moveToFirst()){
            result = criarUsuario(cursor);
        }
        cursor.close();
        db.close();
        return result;
    }

    public Usuario consultar(String email) {
        Usuario result = null;
        String query =
                " SELECT * " +
                        " FROM " + TABELA_USUARIO +
                        " WHERE " + COL_EMAIL_USUARIO + " = ? ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{email});
        if (cursor.moveToFirst()){
            result = criarUsuario(cursor);
        }
        cursor.close();
        db.close();
        return result;
    }
    public ArrayList<Usuario> carregarUsuarios(){
        String query = "SELECT * FROM Tabela_Usuario";
        ArrayList<Usuario> usuarios = new ArrayList<>();
        SQLiteDatabase leitorBanco = dbHelper.getWritableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                usuarios.add(this.criarUsuario(cursor));
            } while (cursor.moveToNext());
        }
        return usuarios;
    }

    private Usuario criarUsuario(Cursor cursor) {
        Usuario result = new Usuario();
        result.setId(cursor.getInt(0));
        result.setNome(cursor.getString(1));
        result.setEmail(cursor.getString(2));
        result.setSenha(cursor.getString(3));

        return result;
    }
}