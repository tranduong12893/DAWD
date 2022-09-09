package com.example.tranvanduong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner select_spinner;
    EditText edName, edEmail, edDescription;
    CheckBox cbCheck;
    Button btnSend;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AppDatabase.getAppDatabase(this);
        Spinner_Main();
        initView();
        setBtnSend();

    }

    public void Spinner_Main() {
        select_spinner = (Spinner) findViewById(R.id.select_spinner);
        ArrayList<String> arraySelect = new ArrayList<String>();
        arraySelect.add("C");
        arraySelect.add("C#");
        arraySelect.add("C++");
        arraySelect.add("Java");
        arraySelect.add("HTML");
        arraySelect.add(".net");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arraySelect);
        select_spinner.setAdapter(arrayAdapter);
    }

    private void initView() {
        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edDescription = findViewById(R.id.edDescription);
        cbCheck = findViewById(R.id.cbCheck);
        btnSend = findViewById(R.id.btnSend);
    }

    private void setBtnSend() {
        FeedbackEntity feedback = new FeedbackEntity();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString().toLowerCase().trim();
                String email = edEmail.getText().toString().toLowerCase().trim();
                String description = edDescription.getText().toString().toLowerCase().trim();
                if (name.isEmpty() || email.isEmpty() || description.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "hãy điền đầy đủ tên, email, nhận xét!", Toast.LENGTH_SHORT).show();
                } else {
                    if (!cbCheck.isChecked()) {
                        Toast.makeText(MainActivity.this,
                                "Vui lòng tích vào check box", Toast.LENGTH_SHORT).show();
                    } else {
                        feedback.setName(name);
                        feedback.setEmail(email);
                        feedback.setDescription(description);
                        feedback.setLanguage(select_spinner.toString());
                        db.feedbackDao().insertFeedback(feedback);
                        Toast.makeText(MainActivity.this,
                                "Có " + db.feedbackDao().getAllFeedback().size() + " feedback trong database",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}