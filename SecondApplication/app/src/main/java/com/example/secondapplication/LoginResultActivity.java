package com.example.secondapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.secondapplication.model.Account;

public class LoginResultActivity extends AppCompatActivity {
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt = findViewById(R.id.txtInfo);
        Intent intent = getIntent();
        if (intent.getSerializableExtra("account") != null
                && intent.getSerializableExtra("user") != null) {
            Account log = (Account) intent.getSerializableExtra("account");
            Account user = (Account) intent.getSerializableExtra("user");
            if (log.getUsername().equals(user.getUsername()) && log.getPassword().equals(user.getPassword())) {
                txt.setText("Login successfully");
            } else {
                txt.setText("Account does not exist");
            }
        }

    }
}