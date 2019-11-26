package br.ufrpe.tocomfome.usuario.gui;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import br.ufrpe.tocomfome.comida.persistencia.ComidaDAO;
import br.ufrpe.tocomfome.comida.dominio.Comida;
import br.ufrpe.tocomfome.R;

public class CadastrarComida extends AppCompatActivity {

    Comida result = new Comida();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastrar_comida);
        Button btnFinalizarCadastro = findViewById(R.id.cadastrarccomida);
        btnFinalizarCadastro.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                cadastrar();
                finish();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Não foi possível cadastrar.", Toast.LENGTH_LONG).show();
            }
        }
    });
       }
    private void cadastrar() throws Exception {
        if (validarCampos()) {
            Comida comida = criarComida();
            ComidaDAO dao = new ComidaDAO(getBaseContext());
            dao.cadastrar(comida);
            Toast.makeText(getApplicationContext(), "Comida cadastrada com sucesso.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(CadastrarComida.this, RecyclerViewUsuario.class));
        }

    }

    private boolean validarCampos() {
        EditText nNome = findViewById(R.id.nomeTextField);
        EditText nDescricao = findViewById(R.id.decricaocomida);
        Validacao valido = new Validacao();
        boolean camposValidos = valido.isValido(nNome, nDescricao);
        return camposValidos;
    }

    private Comida criarComida() {
        EditText nNome = findViewById(R.id.nomeTextField);
        EditText nDescricao = findViewById(R.id.decricaocomida);
        result.setNome(nNome.getText().toString().trim());
        result.setDescricao(nDescricao.getText().toString());
        return result;
    }
}
