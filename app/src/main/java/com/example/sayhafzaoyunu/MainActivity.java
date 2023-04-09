package com.example.sayhafzaoyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv_level, tv_number;

    EditText et_number;

    Button b_confirem;

    Random r;

    int currentLevel = 1;
    String generatedNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_level = findViewById(R.id.tv_level);
        tv_number = findViewById(R.id.tv_number);

        et_number = findViewById(R.id.et_number);

        b_confirem = findViewById(R.id.b_confirm);

        r = new Random();

        //Hide the Input and the Button and show the number
        et_number.setVisibility(View.GONE);
        b_confirem.setVisibility(View.GONE);
        tv_number.setVisibility(View.VISIBLE);

        // Display the current leve
        tv_level.setText("Level : " + currentLevel);

        // Display random number according to the level
        generatedNumber = generateNumber(currentLevel);
        tv_number.setText(generatedNumber);

        //Display the elements after two seconds and hide the number
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                et_number.setVisibility(View.VISIBLE);
                b_confirem.setVisibility(View.VISIBLE);
                tv_number.setVisibility(View.GONE);

                et_number.requestFocus();
            }
        }, 2000);

        b_confirem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if the numbers are the same
                if (generatedNumber.equals(et_number.getText().toString())) {
                    //Hide the Input and the Button and show the number
                    et_number.setVisibility(View.GONE);
                    b_confirem.setVisibility(View.GONE);
                    tv_number.setVisibility(View.VISIBLE);

                    //remove text from input
                    et_number.setText("");

                    //increase the level
                    currentLevel++;

                    // Display the current leve
                    tv_level.setText("Level : " + currentLevel);

                    // Display random number according to the level
                    generatedNumber = generateNumber(currentLevel);
                    tv_number.setText(generatedNumber);

                    //Display the elements after two seconds and hide the number
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            et_number.setVisibility(View.VISIBLE);
                            b_confirem.setVisibility(View.VISIBLE);
                            tv_number.setVisibility(View.GONE);

                            et_number.requestFocus();
                        }
                    }, 2000);
                } else {
                    tv_level.setText("Game Over! The number was " + generatedNumber);
                    b_confirem.setEnabled(false);
                }
            }
        });
    }

    private String generateNumber(int digits) {
        String output = "";
        for (int i = 0; i<=digits;i++){
            int randomDigit = r.nextInt(10);
            output = output + "" + randomDigit;
        }
        return output;

    }
}