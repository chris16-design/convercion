package com.example.conversiondemonedas;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAmount;
    private EditText editTextRate;
    private Spinner spinnerConversion;
    private Button buttonConvert;
    private Button buttonClear;
    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencias a vistas
        editTextAmount = findViewById(R.id.editTextAmount);
        editTextRate = findViewById(R.id.editTextRate);
        spinnerConversion = findViewById(R.id.spinnerConversion);
        buttonConvert = findViewById(R.id.buttonConvert);
        buttonClear = findViewById(R.id.buttonClear);

        // Opciones del Spinner (solo visual)
        String[] opciones = {
                "Dólar a Euro",
                "Dólar a Peso",
                "Peso a Dólar",
                "Euro a Yen"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConversion.setAdapter(adapter);

        // Listeners
        buttonConvert.setOnClickListener(v -> convertir());
        buttonClear.setOnClickListener(v -> limpiar());
    }

    private void convertir() {
        String inputAmount = editTextAmount.getText().toString().trim();
        String inputRate = editTextRate.getText().toString().trim();

        if (inputAmount.isEmpty() || inputRate.isEmpty()) {
            Toast.makeText(this, "Ingrese cantidad y tasa de conversión", Toast.LENGTH_SHORT).show();
            return;
        }

        double cantidad, tasa;
        try {
            cantidad = Double.parseDouble(inputAmount);
            tasa = Double.parseDouble(inputRate);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        double resultado = cantidad * tasa;
        String mensaje = "Resultado: " + df.format(resultado);
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    private void limpiar() {
        editTextAmount.setText("");
        editTextRate.setText("");
        spinnerConversion.setSelection(0);
        Toast.makeText(this, "Campos limpiados", Toast.LENGTH_SHORT).show();
    }}