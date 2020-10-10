package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.model.Form;

public class ResultForm extends AppCompatActivity {

    EditText edtUserName, edtPassWord, edtBirthDate, edtGender, edtHobbies;
    Button btnExit;
    Form form;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_form);
        this.getSupportActionBar().hide();

        mapping();
        Intent intent = getIntent();
        form = (Form) intent.getSerializableExtra("FORM");
        edtUserName.setText(form.getUserName());
        edtPassWord.setText(form.getPassword());
        edtBirthDate.setText(form.getBirthdate());
        edtGender.setText(form.getGender());
        edtHobbies.setText(form.getHobbies());

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mapping() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassWord = findViewById(R.id.edtPassWord);
        edtBirthDate = findViewById(R.id.edtBirthDate);
        edtGender = findViewById(R.id.edtGender);
        edtHobbies = findViewById(R.id.edtHobbies);
        btnExit = findViewById(R.id.btnExit);
    }


}
