package com.evandroalbuquerque.formularioandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.evandroalbuquerque.formularioandroid.R;

public class DisplayDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        Bundle bundle = getIntent().getExtras();

        TextView nome_receber = findViewById(R.id.txtNome);
        String nome = bundle.getString("Nome");
        nome_receber.setText("Nome: " + nome);

        TextView fone_receber = findViewById(R.id.txtFone);
        String fone = bundle.getString("Fone");
        fone_receber.setText("Fone: " + fone);

        TextView email_receber = findViewById(R.id.txtEmail);
        String email = bundle.getString("Email");
        email_receber.setText("Email:" + email);

        TextView senha_receber = findViewById(R.id.txtSenha);
        String senha = bundle.getString("Senha");

        TextView cartao_receber = findViewById(R.id.txtCartao);
        String cartao = bundle.getString("Cartão");
        cartao_receber.setText("Cartao: " + cartao);

        TextView data_receber = findViewById(R.id.txtData);
        String data = bundle.getString("Data");
        data_receber.setText("Data: " + data);

        TextView seguranca_receber = findViewById(R.id.txtSeguranca);
        String seguranca = bundle.getString("Seguranca");

        TextView concordo_receber = findViewById(R.id.txtAceito);
        String concordo = bundle.getString("Concordo");
        concordo_receber.setText("Concordo: " + concordo);

    }
}
