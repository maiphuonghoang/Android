package com.example.secondapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.secondapplication.model.Account;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvUser, tvPass;
    private Button btRegister, btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        btCancel.setOnClickListener(this);
        btRegister.setOnClickListener(this);
    }
    private void initView() {
        tvUser = findViewById(R.id.txtUsername);
        tvPass = findViewById(R.id.txtPassword);
        btCancel = findViewById(R.id.btnCancel);
        btRegister = findViewById(R.id.btnRegister);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                Account account = new Account(tvUser.getText().toString(), tvPass.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("data", account);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btnCancel:
                setResult(RESULT_CANCELED, null);
                finish();
                break;

        }
    }
}