package com.example.demonstration_aula;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demonstration_aula.utils.TokenManager;
import com.example.demonstration_aula.view.login_page.LoginActivity;

public class MainActivity extends AppCompatActivity {
    TextView tvWelcome;
    Button btnLogout;
    TokenManager tokenManager;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);

        tokenManager = new TokenManager(this);

        // Verifica a validade do token periodicamente
        checkTokenValidity();

        // Ação do botão de logout
        btnLogout.setOnClickListener(v -> {
            // Limpar token e redirecionar para a tela de login
            tokenManager.clearToken();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void checkTokenValidity() {
        // Verifica a validade do token
        if (!tokenManager.isTokenValid()) {
            // Token expirou, redirecionar para a tela de login
            Toast.makeText(this, "A sessão expirou. Faça login novamente.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Revalidar o token a cada 30 segundos (ou conforme necessário)
            handler.postDelayed(this::checkTokenValidity, 30000);
        }
    }
}