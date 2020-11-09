package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {
    ProgressBar myBarHorizontal;
    EditText txtDataBox;
    TextView tvAccum;
    Button btnDoItAgain;
    int globalVar = 0, accum = 0, progressStep = 1; // progressStep = 1
    final int MAX_PROGRESS = 100;
    int timeDelay = 0;
    Handler myHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        myBarHorizontal = findViewById(R.id.myBarHor);
        txtDataBox = findViewById(R.id.txtBox1);
        btnDoItAgain = findViewById(R.id.btnDoItAgain);
        tvAccum = findViewById(R.id.tvAccum);
        btnDoItAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtDataBox.getText().toString().equals(""))
                    return;
                timeDelay = round(Integer.parseInt(txtDataBox.getText().toString()) / 10);
                btnDoItAgain.setTextColor(Integer.parseInt("999999", 16) + 0xFF000000);
                btnDoItAgain.setEnabled(false);
                txtDataBox.setEnabled(false);
                start();
            }
        });
    }

    protected void start() {
        txtDataBox.setText("");
        btnDoItAgain.setEnabled(false);
        accum = 0;
        myBarHorizontal.setMax(MAX_PROGRESS);
        myBarHorizontal.setProgress(0);
        myBarHorizontal.setVisibility(View.VISIBLE);
        Thread myBackgroundThread = new Thread(backgroundTask, "backAlias1");
        myBackgroundThread.start();
    }

    private Runnable foregroundRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                myBarHorizontal.incrementProgressBy(progressStep);
                accum += progressStep;
                tvAccum.setText(Integer.toString(accum) + "%");
                if (accum >= myBarHorizontal.getMax()) {
                    btnDoItAgain.setTextColor(Integer.parseInt("000000", 16) + 0xFF000000);
                    btnDoItAgain.setEnabled(true);
                    txtDataBox.setEnabled(true);
                }
            } catch (Exception e) {
                Log.e("<<foregroundTask>>", e.getMessage());
            }
        }
    };

    private Runnable backgroundTask = new Runnable() {
        @Override
        public void run() { // busy work goes here...
            try {
                for (int n = 0; n < 100; n++) {
                    Thread.sleep(timeDelay);
                    globalVar++;
                    myHandler.post(foregroundRunnable);
                }
            } catch (InterruptedException e) {
                Log.e("<<foregroundTask>>", e.getMessage());
            }
        }
    };
}