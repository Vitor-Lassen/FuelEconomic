package com.example.vitor.fueleconomic;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.Format;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar gasSeekBar = findViewById(R.id.gasSeekBar);
        SeekBar ethanolSeekBar = findViewById(R.id.ethanolSeekBar);
        TextView  priceGasSelectedTextView = findViewById(R.id.priceGasSelectedTextView);
        TextView priceEthanolSelectedTextViwe = findViewById(R.id.priceEthanolSelectedTextView);
       // TextInputEditText resultEditText = findViewById(R.id.resultTextInput);

        String zeroFormatado = currencyFormat.format(0);
        priceEthanolSelectedTextViwe.setText(zeroFormatado);
        priceGasSelectedTextView.setText(zeroFormatado);
        gasSeekBar.setOnSeekBarChangeListener(new SeekBarListener(priceGasSelectedTextView));
        ethanolSeekBar.setOnSeekBarChangeListener(new SeekBarListener(priceEthanolSelectedTextViwe));
    }
    private  class SeekBarListener implements SeekBar.OnSeekBarChangeListener {
        TextView _textView;

        public SeekBarListener(TextView textView){
            _textView = textView;
        }
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            double valor = progress * 0.05;
            _textView.setText(currencyFormat.format(valor));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}

