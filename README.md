# 📱 Demonstration Aula

Este é um projeto Android que demonstra um sistema básico de login, registro e gerenciamento de sessão com autenticação baseada em tokens.



## 📋 Funcionalidades

- **Login de Usuários**: Verificação de credenciais e autenticação do usuário.
- **Registro de Usuários**: Criação de novas contas com validação de senha.
- **Gerenciamento de Sessão**:
  - Geração de tokens após login.
  - Validação contínua do token para verificar sua validade.
  - Expiração de token com redirecionamento para tela de login.
- **Logout**: Encerramento de sessão e limpeza de token.
- **Banco de Dados Local**: Armazenamento de usuários usando SQLite.



## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java  
- **Framework Android**: SDK Nativo  
- **Banco de Dados**: SQLite (via `SQLiteOpenHelper`)  
- **Gerenciamento de Sessão**: `SharedPreferences`  
- **Interface do Usuário**: XML layouts  


## 📂 Estrutura do Projeto

### Pacotes Principais:

1. **`com.example.demonstration_aula`**
   - **`MainActivity`**: Tela principal pós-login com botão de logout e validação de sessão.

2. **`com.example.demonstration_aula.view.login_page`**
   - **`LoginActivity`**: Tela de login com validação de credenciais.

3. **`com.example.demonstration_aula.view.register_page`**
   - **`RegisterActivity`**: Tela de registro com validação de senha e integração com o banco de dados.

4. **`com.example.demonstration_aula.utils`**
   - **`TokenManager`**: Gerenciamento de tokens, incluindo geração, validação e remoção.

5. **`com.example.demonstration_aula.db`**
   - **`DatabaseHelper`**: Helper para interação com o banco SQLite.

### Estrutura de Pastas:

```plaintext
src/
├── main/
│   ├── java/com/example/demonstration_aula/
│   │   ├── MainActivity.java
│   │   ├── db/
│   │   │   ├── DatabaseHelper.java
│   │   ├── utils/
│   │   │   ├── TokenManager.java
│   │   ├── view/
│   │       ├── login_page/LoginActivity.java
│   │       ├── register_page/RegisterActivity.java
│   ├── res/
│       ├── layout/
│       │   ├── activity_main.xml
│       │   ├── activity_login.xml
│       │   ├── activity_register.xml
│       ├── drawable/
│           ├── ic_visibility.xml
│           ├── ic_visibility_off.xml
```

## 🖥️ Funcionalidades Principais do Código

### Login
- Verifica credenciais no banco de dados.
- Gera um token para autenticação de sessão.
- Redireciona para a `MainActivity` ao sucesso.

### Registro
- Valida campos obrigatórios.
- Verifica a correspondência das senhas.
- Adiciona um novo usuário ao banco de dados SQLite.

### Gerenciamento de Sessão
- `TokenManager` controla a criação, validação e remoção de tokens.
- Sessões expiram automaticamente após 1 minuto (tempo configurável).

## 🚀 Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-repositorio.git
   ```
2. Abra o projeto no Android Studio.
3. Execute o emulador ou conecte um dispositivo físico.
4. Compile e execute o aplicativo.

## 📝 Observações

- Certifique-se de que o dispositivo ou emulador tenha suporte para APIs mínimas exigidas pelo aplicativo.
- Modifique os ícones de visibilidade de senha (`drawable/ic_visibility.xml` e `drawable/ic_visibility_off.xml`) conforme a necessidade.
