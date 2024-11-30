package com.example.demonstration_aula.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;

public class TokenManager {

    private static final String PREF_NAME = "UserSession";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_TOKEN_CREATION_TIME = "token_creation_time";
    private static final int TOKEN_EXPIRATION_TIME = 1 * 60 * 1000; // 1 minuto em milissegundos

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public TokenManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Gera e salva um novo token
    public String createToken() {
        String token = UUID.randomUUID().toString();
        long currentTime = System.currentTimeMillis();
        editor.putString(KEY_TOKEN, token);
        editor.putLong(KEY_TOKEN_CREATION_TIME, currentTime);
        editor.apply();
        return token;
    }

    // Verifica se o token é válido
    public boolean isTokenValid() {
        String token = sharedPreferences.getString(KEY_TOKEN, null);
        long tokenCreationTime = sharedPreferences.getLong(KEY_TOKEN_CREATION_TIME, 0);
        long currentTime = System.currentTimeMillis();

        return token != null && (currentTime - tokenCreationTime) < TOKEN_EXPIRATION_TIME;
    }

    // Limpa o token (para logout)
    public void clearToken() {
        editor.remove(KEY_TOKEN);
        editor.remove(KEY_TOKEN_CREATION_TIME);
        editor.apply();
    }
}
