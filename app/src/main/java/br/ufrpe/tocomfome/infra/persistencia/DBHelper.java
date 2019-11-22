package br.ufrpe.tocomfome.infra.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "tocomfome.bd";
    private static final int DB_VERSION = 1;

    public static final String TABELA_USUARIO = "Tabela_Usuario";
    public static final String COL_ID_USUARIO = "id";
    public static final String COL_NOME_USUARIO = "nome";
    public static final String COL_EMAIL_USUARIO = "email";
    public static final String COL_SENHA_USUARIO = "senha";

    public static final String TABELA_COMIDA = "Tabela_Comida";
    public static final String COL_ID_COMIDA = "id";
    public static final String COL_NOME_COMIDA = "nome";
    public static final String COL_DESCRICAO = "descricao";
    public static final String COL_FOTO_COMIDA = "foto";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE %1$s ";
    private static final String SQL_INTEGER_AUTOINCREMENT = "  %2$s INTEGER PRIMARY KEY AUTOINCREMENT, ";
    private static final String[] TABELAS = {
            TABELA_PROFISSIONAL, TABELA_COMIDA,
    };


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableUsuario(db);
        createTableComida(db);
    }

    private void createTableUsuario(SQLiteDatabase db) {
        String sqlTbUsuario = SQL_CREATE_TABLE + "( " +
                SQL_INTEGER_AUTOINCREMENT +
                " %3$s TEXT NOT NULL, " +
                " %4$s TEXT NOT NULL, " +
                " %5$s TEXT NOT NULL " +
                ");";
        sqlTbUsuario = String.format(sqlTbUsuario,
                TABELA_USUARIO, COL_ID_USUARIO, COL_NOME_USUARIO,
                COL_EMAIL_USUARIO, COL_SENHA_USUARIO);
        db.execSQL(sqlTbUsuario);
    }

    private void createTableComida(SQLiteDatabase db) {
        String sqlTbComida = SQL_CREATE_TABLE + "( " +
                SQL_INTEGER_AUTOINCREMENT +
                " %3$s TEXT NOT NULL, " +
                " %4$s TEXT NOT NULL, " +
                " %5$s BLOB " +
                ");";
        sqlTbComida= String.format(sqlTbComida,
                TABELA_PROFISSIONAL, COL_ID_PROFISSIONAL, COL_NOME_PROFISSIONAL,
                COL_DESCRICAO, COL_FOTO_PROFISSIONAL);

        db.execSQL(sqlTbComida);
    }



    public void dropTables(SQLiteDatabase db) {
        String dropSql = "DROP TABLE IF EXISTS %1$s;";
        for (String tabela : TABELAS) {
            db.execSQL(String.format(dropSql,tabela));
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        dropTables(db);
        onCreate(db);

    }

}