package br.ufrpe.tocomfome.usuario.gui;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacao {

    public boolean isCampoValido(EditText editText) {
        boolean result = true;
        if (editText.getText().toString().trim().length()==0) {
            editText.requestFocus();
            editText.setError("Preencha o Campo.");
            result = false;
        }
        return result;
    }

    public boolean isValido(EditText... editTexts) {
        boolean result = true;
        for (EditText editText : editTexts) {
            if (!isCampoValido(editText)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean senhaCorreta(EditText senha){
        String senhaText = senha.getText().toString();
        if (senhaText.length() <8){
            senha.requestFocus();
            senha.setError("Digita uma senha de 8 caracteres");
            return false;
        }
        return true;

    }
    public boolean confirmarSenha(Context context,String nSenha, String nConfirmarSenha) {
        boolean result = true;
        if (!nSenha.equals(nConfirmarSenha)) {
            result = false;
            Toast.makeText(context,"Senhas diferentes", Toast.LENGTH_LONG).show();
        }
        return result;

    }
    public boolean validarEmail(EditText email) {
        String stringEmail = email.getText().toString().trim();
        boolean isEmailIdValid = false;
        if (stringEmail.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(stringEmail);
            if (matcher.matches()) {
                isEmailIdValid = true;
                return isEmailIdValid;

            }
        }
        email.requestFocus();
        email.setError("Email Invalido");
        return isEmailIdValid;


    }
}