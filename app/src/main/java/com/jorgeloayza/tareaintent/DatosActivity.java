package com.jorgeloayza.tareaintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DatosActivity extends AppCompatActivity {
    TextView tvNombre,tvFechaNacimiento,tvTelefono,tvEmail,tvDescripcion;
    Button btnEditar;
    //Datos recibidos
    String nombre,telefono,email,descripcion;
    int dia = 0, mes = 0, year = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        tvNombre = findViewById(R.id.tvNombre);
        tvFechaNacimiento = findViewById(R.id.tvFechaNacimiento);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvEmail = findViewById(R.id.tvEmail);
        tvDescripcion = findViewById(R.id.tvDescripcion);

        //Obtenemos los datos de la activity anterior y los asignamos
        //a nuestras varibles
        Bundle bundle = getIntent().getExtras();
        nombre = bundle.getString(getString(R.string.intentNombre));
        dia = bundle.getInt(getString(R.string.intentDia));
        mes = bundle.getInt(getString(R.string.intentMes));
        year = bundle.getInt(getString(R.string.intentYear));
        telefono = bundle.getString(getString(R.string.intentTelefono));
        email = bundle.getString(getString(R.string.intentEmail));
        descripcion = bundle.getString(getString(R.string.intentDescripcion));

        tvNombre.setText(nombre);
        //Armamos la fecha
        String fecha =  dia + "/" + (mes +1) + "/" + year;
        tvFechaNacimiento.setText("Fecha Nacimiento: "+ fecha);
        tvTelefono.setText("Tel.: "+ telefono);
        tvEmail.setText("Email: " + email);
        tvDescripcion.setText(descripcion);

        btnEditar = findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDatosDeVuelta();
            }
        });

    }

    //Metodo que se activa cuando presionamos el boton back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        enviarDatosDeVuelta();
    }
    //metodo para enviar los datos a la anterior activity para ser editados
    private void enviarDatosDeVuelta() {
        Intent intent = new Intent(DatosActivity.this, MainActivity.class);
        intent.putExtra(getString(R.string.intentNombre),nombre);
        //Mandamos los datos de la fecha separados
        intent.putExtra(getString(R.string.intentDia),dia);
        intent.putExtra(getString(R.string.intentMes),mes);
        intent.putExtra(getString(R.string.intentYear),year);

        intent.putExtra(getString(R.string.intentTelefono),telefono);
        intent.putExtra(getString(R.string.intentEmail),email);
        intent.putExtra(getString(R.string.intentDescripcion),descripcion);
        startActivity(intent);
        //cerramos la actual activity
        finish();
    }
}
