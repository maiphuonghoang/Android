package com.example.secondapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.secondapplication.model.Account;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvUser, tvPass;
    private Button btLogin, btRegister;
    private final static int REQUEST_CODE = 0;
    private Account user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        btLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);
    }

    private void initView() {
        tvUser = findViewById(R.id.txtUsername);
        tvPass = findViewById(R.id.txtPassword);
        btLogin = findViewById(R.id.btnLogin);
        btRegister = findViewById(R.id.btnRegister);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                Intent loginIntent = new Intent(LoginActivity.this, LoginResultActivity.class);
                Account account = new Account(tvUser.getText().toString(), tvPass.getText().toString());
                loginIntent.putExtra("account", account);
                loginIntent.putExtra("user", user);
                startActivity(loginIntent);
                break;
            case R.id.btnRegister:
                Intent regisIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                //Lấy dữ liệu ở activity regis
                startActivityForResult(regisIntent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "User cancelled registration", Toast.LENGTH_SHORT).show();
            } else {
                user = (Account) data.getSerializableExtra("data");
                tvUser.setText(user.getUsername());
                tvPass.setText(user.getPassword());
            }
        } else {
            user = null;
        }
    }
}