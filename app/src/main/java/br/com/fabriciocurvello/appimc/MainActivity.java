package br.com.fabriciocurvello.appimc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etPeso;
    EditText etAltura;
    Button btCalcular;
    TextView tvImc;
    TextView tvSituacao;

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

        etPeso = findViewById(R.id.et_peso);
        etAltura = findViewById(R.id.et_altura);
        btCalcular = findViewById(R.id.bt_calcular);
        tvImc = findViewById(R.id.tv_imc);
        tvSituacao = findViewById(R.id.tv_situacao);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double peso = Double.parseDouble(etPeso.getText().toString());
                double altura = Double.parseDouble(etAltura.getText().toString());

                double imc = calcularImc(peso, altura);
                String situacao = validarSituacao( imc );

                tvImc.setText( String.format("IMC: %.1f", imc)); // formatado para exibir 1 dígito decimal
                tvSituacao.setText("Situação: " + situacao);
            }
        });
    }

    private double calcularImc( double peso, double altura ) {
        double imc = peso / (altura * altura);
        return imc;
    }

    private String validarSituacao( double imc ) {
        String situacao;
        if ( imc < 18.5 ) {
            situacao = "Abaixo do peso";
        } else if ( imc < 24.9 ) {
            situacao = "Peso normal";
        } else if ( imc < 29.9 ) {
            situacao = "Sobrepeso";
        } else if ( imc < 34.9 ) {
            situacao = "Obesidade grau 1";
        } else if ( imc < 39.9 ) {
            situacao = "Obesidade grau 2";
        } else {
            situacao = "Obesidade grau 3";
        }
        return situacao;
    }
}