package br.ufrpe.tocomfome.usuario.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.ufrpe.tocomfome.R;
import br.ufrpe.tocomfome.usuario.negocio.UsuarioServices;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Button btnEntrar = findViewById(R.id.botaoEntrar);
        Button btnCadastrar = findViewById(R.id.botaoCadastro);
        final EditText campoEmail = findViewById(R.id.caixatxtEmailLogin);
        final EditText campoSenha = findViewById(R.id.caixatxtSenhaLogin);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CadastroUsuario.class));

            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrarUsuario(campoEmail, campoSenha);
            }
        });}


    private void entrarUsuario(EditText campoEmail, EditText campoSenha) {
        UsuarioServices services = new UsuarioServices(getBaseContext());
        Validacao validacao = new Validacao();
        if (validacao.isValido(campoEmail,campoSenha)){
            String email = campoEmail.getText().toString().trim();
            String senha = campoSenha.getText().toString().trim();
            try {
                services.logar(email,senha);
                finish();
                startActivity(new Intent(MainActivity.this, RecyclerViewUsuario.class));

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Email/senha incorretos.", Toast.LENGTH_LONG).show();
            }
        }
    }
}