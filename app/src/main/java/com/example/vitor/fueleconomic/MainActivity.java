package com.example.vitor.fueleconomic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private SeekBar _gasSeekBar ;
    private SeekBar _ethanolSeekBar;
    private TextView  _priceGasSelectedTextView;
    private TextView _priceEthanolSelectedTextView;
    private TextView _theBestChoiceSelectedTextView;
    private ImageView _resultImageView;

    private NumberFormat _currencyFormat = NumberFormat.getCurrencyInstance();

    private double _costGasoline;
    private double _costEthanol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _gasSeekBar = findViewById(R.id.gasSeekBar);
        _ethanolSeekBar = findViewById(R.id.ethanolSeekBar);
        _priceGasSelectedTextView = findViewById(R.id.priceGasSelectedTextView);
        _priceEthanolSelectedTextView = findViewById(R.id.priceEthanolSelectedTextView);
        _theBestChoiceSelectedTextView = findViewById(R.id.TheBestChoiceSelectTextView);
        _resultImageView = findViewById(R.id.resutImageView);

        String zeroFormatado = _currencyFormat.format(0);
        _priceEthanolSelectedTextView.setText(zeroFormatado);
        _priceGasSelectedTextView.setText(zeroFormatado);
        _gasSeekBar.setOnSeekBarChangeListener(new SeekBarListener(_priceGasSelectedTextView,_costGasoline));
        _ethanolSeekBar.setOnSeekBarChangeListener(new SeekBarListener(_priceEthanolSelectedTextView,_costEthanol));

    }

    private enum Fuel {
        gasoline,
        ethanol
    }

    private  Fuel calculateBestChoice(){
        int gasoline = _gasSeekBar.getProgress();
        int ethanol = _ethanolSeekBar.getProgress();
        int result = gasoline - ethanol;
        if (result >= 0.7)
            return  Fuel.gasoline;
        return  Fuel.ethanol;
    }

    private void selectBestChoice(Fuel fuel){
        if (fuel == Fuel.ethanol){
            _theBestChoiceSelectedTextView.setText(R.string.ethanol);
            _resultImageView.setImageDrawable((getDrawable(R.drawable.etanol)));
        }
        else {
            _theBestChoiceSelectedTextView.setText(R.string.gasoline);
            _resultImageView.setImageDrawable((getDrawable(R.drawable.gasolina)));
        }
    }

    private  class SeekBarListener implements SeekBar.OnSeekBarChangeListener {
        TextView _textView;
        double _cost;
        public SeekBarListener(TextView textView,double cost){
            _textView = textView;
            _cost = cost;
        }
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            _cost = progress * 0.05;
            _textView.setText(_currencyFormat.format(_cost));
            Fuel bestFuel = calculateBestChoice();
            selectBestChoice(bestFuel);


        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}

