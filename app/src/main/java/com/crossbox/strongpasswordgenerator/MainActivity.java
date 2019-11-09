package com.crossbox.strongpasswordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    MaterialButton mb ;
    TextInputEditText lengthEditText;
    MaterialTextView t ;
    String capitalChars;
    String smallChars;
    String numbers ;
    String symbols ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        capitalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        smallChars = "abcdefghijklmnopqrstuvwxyz";
        numbers = "0123456789";
        symbols = "!@#$%^&*_=+-/.?<>)";
        mb = findViewById(R.id.mb1);
        t = findViewById(R.id.num);
        lengthEditText = findViewById(R.id.minVal);

        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = Integer.parseInt(lengthEditText.getText().toString());//todo add checks
                Random rand = new Random();
                String finalValues = capitalChars + smallChars + numbers + symbols;//todo generalize combination
                char[] password = new char[length];//todo generalize length
                for(int i=0;i<length;i++){
                    password[i] = finalValues.charAt(rand.nextInt(finalValues.length()));
//                    Log.i("gen",String.valueOf(password[i]));
                }
                t.setText(String.valueOf(password));
//                Log.i("Password",password.toString());
            }
        });
    }
}
