package com.jorgeloayza.tareaintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    Button btnSigueinte;
    EditText etNombre,etTelefono, etEmail,etDescripcion;
    DatePicker dpDefechaNacimiento;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSigueinte = findViewById(R.id.btnSiguiente);
        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etEmail = findViewById(R.id.etEmail);
        etDescripcion = findViewById(R.id.etDescripcionContacto);
        dpDefechaNacimiento = findViewById(R.id.dpFechaNacimiento);

        btnSigueinte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //verificamos que todos lo dato estan llenos con el metodo veirifarDatos
                if (verificarDatos()){
                    Intent intent = new Intent(MainActivity.this, DatosActivity.class);
                    intent.putExtra(getString(R.string.intentNombre),etNombre.getText().toString());
                    //Mandamos los datos de la fecha separados
                    intent.putExtra(getString(R.string.intentDia),dpDefechaNacimiento.getDayOfMonth());
                    intent.putExtra(getString(R.string.intentMes),dpDefechaNacimiento.getMonth());
                    intent.putExtra(getString(R.string.intentYear),dpDefechaNacimiento.getYear());

                    intent.putExtra(getString(R.string.intentTelefono),etTelefono.getText().toString());
                    intent.putExtra(getString(R.string.intentEmail),etEmail.getText().toString());
                    intent.putExtra(getString(R.string.intentDescripcion),etDescripcion.getText().toString());
                    startActivity(intent);
                    //cerramos la actual activity
                    finish();
                }else {
                    Snackbar.make(view, R.string.mensajeLlenadoDatos,Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        //verificamos si nos llega datos de la activity datos
        if (getIntent().getExtras() != null){
            bundle = getIntent().getExtras();
            setearDatos();
        }
    }
    //seteamos los dato recibidos a nuestros edittext y el date picker
    private void setearDatos() {
        etNombre.setText(bundle.getString(getString(R.string.intentNombre)));
        dpDefechaNacimiento.updateDate(bundle.getInt(getString(R.string.intentYear)),
                bundle.getInt(getString(R.string.intentMes)),
                bundle.getInt(getString(R.string.intentDia)));

        etTelefono.setText(bundle.getString(getString(R.string.intentTelefono)));
        etEmail.setText(bundle.getString(getString(R.string.intentEmail)));
        etDescripcion.setText(bundle.getString(getString(R.string.intentDescripcion)));

    }
    //metodo para veirificar que todos los datos estan llenos
    public boolean verificarDatos(){
        if (etNombre.getText().toString().isEmpty() || etTelefono.getText().toString().isEmpty() ||
                etEmail.getText().toString().isEmpty() || etDescripcion.getText().toString().isEmpty()){
            return false;
        } else {
            return true;
        }

    }
}