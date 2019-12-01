package com.evandroalbuquerque.formularioandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.evandroalbuquerque.formularioandroid.R;
import com.evandroalbuquerque.formularioandroid.activity.mask.InputMask;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.redmadrobot.inputmask.MaskedTextChangedListener;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty(messageResId = R.string.validation_error_not_empty)
    public EditText nome;

    @NotEmpty(messageResId = R.string.validation_error_not_empty)
    @Email(messageResId = R.string.validation_error_email)
    public EditText email;

    @NotEmpty(messageResId = R.string.validation_error_not_empty)
    @Pattern(regex = "(\\+[0-9]+[\\- \\.]*)?(\\([0-9]+\\)[\\-\\.]*)?([0-9][0-9\\- \\.]+[0-9])",
            messageResId = R.string.validation_error_phone)
    public EditText fone;

    @NotEmpty(messageResId = R.string.validation_error_not_empty)
    @Password(min = 8, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS,
            messageResId = R.string.validation_error_password)
    public EditText senha;

    @NotEmpty(messageResId = R.string.validation_error_not_empty)
    @Length(min = 19, message = "Informe pelo menos 19 dígitos")
    public EditText cartao;

    @NotEmpty(messageResId = R.string.validation_error_not_empty)
    public EditText data;

    @NotEmpty(messageResId = R.string.validation_error_not_empty)
    @Length(min = 3, message = "Informe pelo menos 3 dígitos")
    public EditText seguranca;

    @Checked(message = "Você precisa aceitar os termos de serviço")
    public CheckBox concordo;

    public Button   enviar;

    public Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome      = findViewById(R.id.txtNome);
        email     = findViewById(R.id.txtEmail);
        fone      = findViewById(R.id.txtFone);
        senha     = findViewById(R.id.txtSenha);
        cartao    = findViewById(R.id.txtCartao);
        data      = findViewById(R.id.txtData);
        seguranca = findViewById(R.id.txtSeguranca);
        concordo  = findViewById(R.id.chkAceito);
        enviar    = findViewById(R.id.btnEnviar);

        MaskedTextChangedListener foneMascara = InputMask.getPhoneMask(fone);
        fone.addTextChangedListener(foneMascara);
        fone.setOnFocusChangeListener(foneMascara);

        fone.setHint(foneMascara.placeholder());

        MaskedTextChangedListener dataMascara = InputMask.getDateMask(data);
        data.addTextChangedListener(dataMascara);
        data.setOnFocusChangeListener(dataMascara);
        data.setHint(dataMascara.placeholder());

        MaskedTextChangedListener cartaoMascara = InputMask.getCreditCardNumberMask(cartao);
        cartao.addTextChangedListener(cartaoMascara);
        cartao.setOnFocusChangeListener(cartaoMascara);
        cartao.setHint(cartaoMascara.placeholder());

        validator = new Validator(MainActivity.this);
        validator.setValidationListener(MainActivity.this);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnEnviar)

                     validator.validate();
            }
        });

    }

    @Override
    public void onValidationSucceeded() {
        Intent intent = new Intent(MainActivity.this, DisplayDataActivity.class);
        intent.putExtra("Nome", nome.getText().toString());
        intent.putExtra("Email", email.getText().toString());
        intent.putExtra("Fone", fone.getText().toString());
        intent.putExtra("Senha", senha.getText().toString());
        intent.putExtra("Cartão", cartao.getText().toString());
        intent.putExtra("Data", data.getText().toString());
        intent.putExtra("Segurança", seguranca.getText().toString());
        intent.putExtra("Concordo", (concordo.isChecked()) ? "Termo de aceito" : "Termo de não aceito");

        startActivity(intent);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        Iterator<ValidationError> iterator = errors.iterator();

        while (iterator.hasNext()) {
            ValidationError error = iterator.next();
            View view = error.getView();

            if (view instanceof TextView) {
                ((TextView) view).setError(error.getCollatedErrorMessage(this));
            }

            iterator.remove();
        }
    }

}
