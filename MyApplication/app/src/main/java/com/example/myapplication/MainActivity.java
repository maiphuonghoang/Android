package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSubmit;
    private TextView textResult;
    private EditText textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        addAction();
    }

    private void addAction() {
        btnSubmit.setOnClickListener(this);
    }

    private void initView() {
        btnSubmit = findViewById(R.id.button_submit);
        textInput = findViewById(R.id.edit_input);
        textResult = findViewById(R.id.text_result);
    }


    @Override
    public void onClick(View v) {
        String input = textInput.getText().toString();
        textResult.setText(getResources().getString(R.string.message) + ' ' + input
            + getResources().getString(R.string.nice_day_msg));
    }
}