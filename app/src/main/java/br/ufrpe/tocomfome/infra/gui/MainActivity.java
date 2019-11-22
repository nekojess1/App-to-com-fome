package br.ufrpe.tocomfome.infra.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.usuario.gui.HomeUsuario;
import br.ufrpe.revcare.profissional.gui.CadastroProfissional;
import br.ufrpe.revcare.profissional.gui.HomeProfissional;
import br.ufrpe.revcare.profissional.negocio.ProfissionalServices;
import br.ufrpe.revcare.usuario.gui.CadastroUsuario;
import br.ufrpe.revcare.usuario.negocio.UsuarioServices;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Button btnEntrar = findViewById(R.id.botaoEntrar);
        Button btnEsqueceuSenha = findViewById(R.id.esqueceuSenha);
        Button btnCadastrar = findViewById(R.id.botaoCadastro);
        final Switch switchUsuarioProfissional = findViewById(R.id.switchUsuarioProfissional);
        final EditText campoEmail = findViewById(R.id.caixatxtEmailLogin);
        final EditText campoSenha = findViewById(R.id.caixatxtSenhaLogin);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (switchUsuarioProfissional.isChecked()) {
                    finish();
                    startActivity(new Intent(MainActivity.this, CadastroProfissional.class));
                } else {
                    finish();
                    startActivity(new Intent(MainActivity.this, CadastroUsuario.class));
                }
            }

        });
        btnEsqueceuSenha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, EsqueceuSenha.class));
            }

        });


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrar(switchUsuarioProfissional, campoEmail, campoSenha);
            }
        });}

    private void entrar(Switch switchUsuarioProfissional, EditText campoEmail, EditText campoSenha) {
        if (switchUsuarioProfissional.isChecked()) {
            entrarProfissional(campoEmail,campoSenha);
        } else {

            entrarUsuario(campoEmail, campoSenha);
        }
    }


    private void entrarUsuario(EditText campoEmail, EditText campoSenha) {
        UsuarioServices services = new UsuarioServices(getBaseContext());
        Validacao validacao = new Validacao();
        if (validacao.isValido(campoEmail,campoSenha)){
            String email = campoEmail.getText().toString().trim();
            String senha = campoSenha.getText().toString().trim();
            try {
                services.logar(email,senha);
                finish();
                startActivity(new Intent(MainActivity.this, HomeUsuario.class));

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Email/senha incorretos.", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void entrarProfissional(EditText campoEmail, EditText campoSenha) {
        ProfissionalServices services = new ProfissionalServices(getBaseContext());
        Validacao validacao = new Validacao();
        if (validacao.isValido(campoEmail,campoSenha)){
            String email = campoEmail.getText().toString().trim();
            String senha = campoSenha.getText().toString().trim();
            try {
                services.logar(email,senha);
                finish();
                startActivity(new Intent(MainActivity.this, HomeProfissional.class));

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Email/senha incorretos.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
