package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText kilograms, pounds, ounces;
    private Button button;
    private Switch switchk, switchp, switcho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kilograms = findViewById(R.id.idKilograms);
        pounds = findViewById(R.id.idPounds);
        ounces = findViewById(R.id.idOunces);
        button = findViewById(R.id.button);
        switchk = findViewById(R.id.switchK);
        switchp = findViewById(R.id.switchP);
        switcho = findViewById(R.id.switchO);

        refresh();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                refresh();
                restart();
            }
        });
        switchk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    kilograms.setEnabled(true);
                    pounds.setEnabled(false);
                    ounces.setEnabled(false);
                    switchp.setChecked(false);
                    switcho.setChecked(false);
                    kilograms.addTextChangedListener(new TextWatcher() {
                                        @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Toast.makeText(MainActivity.this, "Listner Worked", Toast.LENGTH_SHORT).show();
                    if ((Double.parseDouble(kilograms.getText().toString()) > 0) && switchk.isChecked()) {
                        convertKg();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
                }
                else disableSwitches();
            }
        });
        switchp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    kilograms.setEnabled(false);
                    pounds.setEnabled(true);
                    ounces.setEnabled(false);
                    switchk.setChecked(false);
                    switcho.setChecked(false);
                    pounds.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            if ((Double.parseDouble(pounds.getText().toString()) > 0) && switchp.isChecked()) {
                                convertPound();
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                        }
                    });
                }
                else disableSwitches();
            }
        });
        switcho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    kilograms.setEnabled(false);
                    pounds.setEnabled(false);
                    ounces.setEnabled(true);
                    switchk.setChecked(false);
                    switchp.setChecked(false);
                    ounces.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            if ((Double.parseDouble(ounces.getText().toString()) > 0) && switcho.isChecked()) {
                                convertOunce();
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                        }
                    });
                }
                else disableSwitches();
            }
        });

    }
    public void convertKg()
    {
            String s = kilograms.getText().toString();
            double kg = Double.parseDouble(s);
            double pound = 2.205 * kg;
            double ounce = 35.274 * kg;
            pounds.setText("" + pound);
            ounces.setText("" + ounce);
    }
    public void convertPound()
    {
            String s = pounds.getText().toString();
            double pound = Double.parseDouble(s);
            double kg = 0.454 * pound;
            double ounce = 16 * pound;
            kilograms.setText("" + kg);
            ounces.setText("" + ounce);
    }
    public void convertOunce()
    {
            String s = ounces.getText().toString();
            double ounce = Double.parseDouble(s);
            double pound = 0.0625 * ounce;
            double kg = 0.0283 * ounce;
            pounds.setText("" + pound);
            kilograms.setText("" + kg);
    }
    public void refresh()
    {
        disableText();
        disableSwitches();
        kilograms.setText("");
        pounds.setText("");
        ounces.setText("");
    }
    public void disableSwitches()
    {
        if(!(switchk.isChecked() == switchp.isChecked() == switcho.isChecked()))
        {
            kilograms.setEnabled(false);
            pounds.setEnabled(false);
            ounces.setEnabled(false);
        }
    }
    public void disableText()
    {
        switchk.setChecked(false);
        switchp.setChecked(false);
        switcho.setChecked(false);
    }
    public void restart(){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finishAffinity();
    }
}