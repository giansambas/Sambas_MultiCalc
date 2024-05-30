package com.example.sambas_multicalc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private StringBuilder currentInput = new StringBuilder();
    private double firstOperand = 0;
    private String operator = "";
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Number buttons
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2,
                R.id.button3, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };

        View.OnClickListener numberButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (isOperatorPressed) {
                    currentInput.setLength(0); // Clear the current input
                    isOperatorPressed = false;
                }
                currentInput.append(button.getText().toString());
                display.setText(currentInput.toString());
            }
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(numberButtonListener);
        }

        // Operator buttons
        int[] operatorButtonIds = {
                R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide
        };

        View.OnClickListener operatorButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                operator = button.getText().toString();
                firstOperand = Double.parseDouble(currentInput.toString());
                isOperatorPressed = true;
            }
        };

        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(operatorButtonListener);
        }

        // Equals button
        findViewById(R.id.buttonEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double secondOperand = Double.parseDouble(currentInput.toString());
                double result = 0;

                switch (operator) {
                    case "+":
                        result = firstOperand + secondOperand;
                        break;
                    case "-":
                        result = firstOperand - secondOperand;
                        break;
                    case "*":
                        result = firstOperand * secondOperand;
                        break;
                    case "/":
                        if (secondOperand != 0) {
                            result = firstOperand / secondOperand;
                        } else {
                            display.setText("Error");
                            return;
                        }
                        break;
                }
                display.setText(String.valueOf(result));
                currentInput.setLength(0);
                currentInput.append(result);
                isOperatorPressed = false;
            }
        });

        // Clear button
        findViewById(R.id.buttonClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput.setLength(0);
                display.setText("0");
                firstOperand = 0;
                operator = "";
                isOperatorPressed = false;
            }
        });
    }
}

