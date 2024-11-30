package com.example.demonstration_aula.view.register_page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.demonstration_aula.R;
import com.example.demonstration_aula.db.DatabaseHelper;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etPassword, etConfirmPassword;
    Button btnRegister, btnVoltar;
    DatabaseHelper db;
    boolean isPasswordVisible = false;
    boolean isPasswordConfirmVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        etUsername = findViewById(R.id.etRegisterUsername);
        etPassword = findViewById(R.id.etRegisterPassword);
        etConfirmPassword = findViewById(R.id.etRegisterConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnVoltar = findViewById(R.id.btnVoltar);
        ImageView ivTogglePassword = findViewById(R.id.ivToggleRegisterPassword);
        ImageView ivToggleConfirmPassword = findViewById(R.id.ivToggleRegisterConfirmPassword);

        btnVoltar.setOnClickListener(view -> finish());

        // Lógica para mostrar/ocultar a senha
        ivTogglePassword.setOnClickListener(view -> {
            if (isPasswordVisible) {
                // Ocultar a senha
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                ivTogglePassword.setImageResource(R.drawable.ic_visibility_off); // Ícone de "olho fechado"
            } else {
                // Mostrar a senha
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                ivTogglePassword.setImageResource(R.drawable.ic_visibility); // Ícone de "olho aberto"
            }
            isPasswordVisible = !isPasswordVisible;
            // Move o cursor para o final do texto
            etPassword.setSelection(etPassword.getText().length());
        });

        // Lógica para mostrar/ocultar a senha
        ivToggleConfirmPassword.setOnClickListener(view -> {
            if (isPasswordConfirmVisible) {
                // Ocultar a senha
                etConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                ivToggleConfirmPassword.setImageResource(R.drawable.ic_visibility_off); // Ícone de "olho fechado"
            } else {
                // Mostrar a senha
                etConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                ivToggleConfirmPassword.setImageResource(R.drawable.ic_visibility); // Ícone de "olho aberto"
            }
            isPasswordConfirmVisible = !isPasswordConfirmVisible;
            // Move o cursor para o final do texto
            etConfirmPassword.setSelection(etConfirmPassword.getText().length());
        });

        btnRegister.setOnClickListener(view -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 8) {
                Toast.makeText(getApplicationContext(), "A senha deve ter pelo menos 8 caracteres", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(getApplicationContext(), "As senhas não coincidem!", Toast.LENGTH_SHORT).show();
            } else {
                if (db.insertUser(username, password)) {
                    Toast.makeText(getApplicationContext(), "Registrado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish(); // Voltar para tela de login
                } else {
                    Toast.makeText(getApplicationContext(), "Falha no registro", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
