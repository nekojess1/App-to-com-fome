package br.ufrpe.tocomfome.usuario.gui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.ufrpe.tocomfome.comida.dominio.Comida;
import java.util.ArrayList;
import java.util.List;
import br.ufrpe.tocomfome.comida.persistencia.ComidaDAO;

import br.ufrpe.tocomfome.R;


public class RecyclerViewUsuario extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ArrayList<String> mNomes = new ArrayList<>();
    private ArrayList<String> mDescricao = new ArrayList<>();
    private List<Comida> comidas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_usuario);
        getSupportActionBar().hide();
        initComidas();
        Button adicionar = findViewById(R.id.btnadicionar);
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecyclerViewUsuario.this, CadastrarComida.class));
            }
        });
    }

    private void initComidas() {
        ComidaDAO dao = new ComidaDAO(getApplicationContext());
        comidas = dao.getAllComida();
        adicionaNoArray(dao, comidas);
        initRecyclerView();

    }

    private void adicionaNoArray(ComidaDAO dao, List<Comida> comidas) {
        for (int i = 0; i < comidas.size(); i++) {
            mNomes.add(comidas.get(i).getNome());
            mDescricao.add(comidas.get(i).getDescricao());
        }
    }


    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.usuariorecylcer);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNomes, mDescricao);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}