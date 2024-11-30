package com.example.demonstration_aula.view.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;

import com.example.demonstration_aula.MainActivity;
import com.example.demonstration_aula.R;
import com.example.demonstration_aula.db.DatabaseHelper;
import com.example.demonstration_aula.utils.TokenManager;
import com.example.demonstration_aula.view.register_page.RegisterActivity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvRegister;
    DatabaseHelper db;
    TokenManager tokenManager;
    boolean isPasswordVisible = false;  // Estado de visibilidade da senha

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        tokenManager = new TokenManager(this);

        etUsername = findViewById(R.id.etLoginUsername);
        etPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        ImageView ivTogglePassword = findViewById(R.id.ivTogglePassword);

        // Lógica para mostrar/ocultar a senha
        ivTogglePassword.setOnClickListener(v -> {
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

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (db.checkUser(username, password)) {
                Toast.makeText(getApplicationContext(), "Login bem-sucedido!", Toast.LENGTH_SHORT).show();

                // Cria um token após login bem-sucedido
                tokenManager.createToken();

                // Redirecionar para a MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Credenciais inválidas!", Toast.LENGTH_SHORT).show();
            }
        });

        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
