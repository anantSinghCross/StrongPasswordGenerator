package com.crossbox.strongpasswordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    MaterialButton mb ;
    MaterialButton copyButton;
    TextInputEditText lengthEditText;
    TextInputEditText splEditText;
    MaterialTextView t ;
    String capitalChars;
    String smallChars;
    String numbers ;
    String symbols ;
    MaterialCheckBox capsCheck;
    MaterialCheckBox smallsCheck;
    MaterialCheckBox numsCheck;
    MaterialCheckBox symsCheck;
    boolean flag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        capitalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        smallChars = "abcdefghijklmnopqrstuvwxyz";
        numbers = "0123456789";
        mb = findViewById(R.id.mb1);
        copyButton = findViewById(R.id.copy);
        t = findViewById(R.id.num);
        lengthEditText = findViewById(R.id.minVal);
        capsCheck = findViewById(R.id.caps);
        smallsCheck = findViewById(R.id.smalls);
        numsCheck = findViewById(R.id.numbers);
        symsCheck = findViewById(R.id.syms);
        splEditText = findViewById(R.id.splEditText);
        flag = true;

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Data", t.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
            }
        });

        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                if(lengthEditText.getText().toString().equals("") || Integer.parseInt(lengthEditText.getText().toString())<8){
                    Toast.makeText(MainActivity.this, "We recommend using atleast 8 characters", Toast.LENGTH_SHORT).show();
                }
                else if(!capsCheck.isChecked() && !smallsCheck.isChecked() && !numsCheck.isChecked() && !symsCheck.isChecked()){
                    Toast.makeText(MainActivity.this, "Check atleast one checkbox", Toast.LENGTH_SHORT).show();
                }
                else{
                    String finalValues = "";
                    if(capsCheck.isChecked()){
                        finalValues += capitalChars;
                    }
                    if(smallsCheck.isChecked()){
                        finalValues += smallChars;
                    }
                    if(numsCheck.isChecked()){
                        finalValues += numbers;
                    }
                    if(symsCheck.isChecked()){
                        if(splEditText.getText().toString().equals("")){
                            flag = false;
                            Toast.makeText(MainActivity.this, "Please enter some characters", Toast.LENGTH_SHORT).show();
                        }else{
                            String spl = splEditText.getText().toString();
                            for(int i=0;i<spl.length();i++){
                                if(spl.charAt(i)==' '){
                                    flag = false;
                                    Toast.makeText(MainActivity.this, "Character sequence must not contain spaces", Toast.LENGTH_SHORT).show();
                                }
                            }
                            symbols = splEditText.getText().toString();
                            finalValues += symbols;
                        }
                    }
                    if(flag == true){
                        int length = Integer.parseInt(lengthEditText.getText().toString());
                        Random rand = new Random();
                        char[] password = new char[length];
                        for(int i=0;i<length;i++){
                            password[i] = finalValues.charAt(rand.nextInt(finalValues.length()));
                        }
                        t.setText(String.valueOf(password));
                    }

                }
            }
        });
    }


}
