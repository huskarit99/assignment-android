package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.assignment.model.Form;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    DatePickerDialog picker;
    EditText edtUserName, edtPassWord, edtReType, edtBirthDate;
    CheckBox cbTennis, cbFutbal, cbOthers;
    RadioButton rbMale, rbFemale;
    Button btnSelect, btnReset, btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);
        this.getSupportActionBar().hide();

        mapping();
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtUserName.setText("");
                edtPassWord.setText("");
                edtReType.setText("");
                edtBirthDate.setText("");
                cbTennis.setChecked(false);
                cbOthers.setChecked(false);
                cbFutbal.setChecked(false);
                rbMale.setChecked(true);
                rbFemale.setChecked(false);
            }
        });

        rbFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbFemale.setChecked(true);
                rbMale.setChecked(false);
            }
        });

        rbMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbMale.setChecked(true);
                rbFemale.setChecked(false);
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                edtBirthDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(edtUserName.getText()).equals("") || String.valueOf(edtPassWord.getText()).equals("") ||
                        String.valueOf(edtReType.getText()).equals("") || String.valueOf(edtBirthDate.getText()).equals("") ||
                        (rbFemale.isChecked() == false && rbMale.isChecked() == false) ||
                        (cbFutbal.isChecked() == false && cbTennis.isChecked() == false && cbOthers.isChecked() == false)){
                    Toast.makeText(getApplication().getBaseContext(), (String)"You may miss some informations !!!", Toast.LENGTH_LONG).show();
                } else {
                    if (!isValidFormat("dd/MM/yyyy",String.valueOf(edtBirthDate.getText()), Locale.ENGLISH)){
                        Toast.makeText(getApplication().getBaseContext(), (String)"Format of BirthDate is wrong, please try again !!!", Toast.LENGTH_LONG).show();
                    } else {
                        if (edtPassWord.getText().equals(edtReType.getText())) {
                            Toast.makeText(getApplication().getBaseContext(), (String) "Password and Retype password are not the same, please try again !!!", Toast.LENGTH_LONG).show();
                        } else {
                            String gender = "";
                            if (rbMale.isChecked())
                                gender = "Male";
                            if (rbFemale.isChecked())
                                gender = "Female";
                            ArrayList<String> hobbies = new ArrayList<>();
                            if (cbTennis.isChecked())
                                hobbies.add("Tennis");
                            if (cbFutbal.isChecked())
                                hobbies.add("Futbal");
                            if (cbOthers.isChecked())
                                hobbies.add("Others");
                            Form form = new Form(String.valueOf(edtUserName.getText()),
                                    String.valueOf(edtPassWord.getText()),
                                    String.valueOf(edtBirthDate.getText()),
                                    gender,
                                    hobbies);
                            Intent intent = new Intent(MainActivity.this, ResultForm.class);
                            intent.putExtra("FORM", form);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }
        });
    }

    public static boolean isValidFormat(String format, String value, Locale locale) {
        LocalDateTime ldt = null;
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);

        try {
            ldt = LocalDateTime.parse(value, fomatter);
            String result = ldt.format(fomatter);
            return result.equals(value);
        } catch (DateTimeParseException e) {
            try {
                LocalDate ld = LocalDate.parse(value, fomatter);
                String result = ld.format(fomatter);
                return result.equals(value);
            } catch (DateTimeParseException exp) {
                try {
                    LocalTime lt = LocalTime.parse(value, fomatter);
                    String result = lt.format(fomatter);
                    return result.equals(value);
                } catch (DateTimeParseException e2) {
                    // Debugging purposes
                    //e2.printStackTrace();
                }
            }
        }

        return false;
    }

    private void mapping() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassWord = findViewById(R.id.edtPassWord);
        edtReType = findViewById(R.id.edtReType);
        edtBirthDate = findViewById(R.id.edtBirthDate);
        cbTennis = findViewById(R.id.cbTennis);
        cbFutbal = findViewById(R.id.cbFutbal);
        cbOthers = findViewById(R.id.cbOthers);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btnSelect = findViewById(R.id.btnSelect);
        btnReset = findViewById(R.id.btnReset);
        btnSignUp = findViewById(R.id.btnSignUp);
    }

}