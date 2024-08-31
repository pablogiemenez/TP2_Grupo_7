package com.example.ejercicio_controles_grupo7;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {
    //Inicializacion contorles
    private EditText et_nombre, et_apellido,et_telefono, et_mail,et_direccion,et_fecha_nacimiento;
    private Spinner sp_tel;
    private Spinner sp_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        //conexion con interfaz grafica
        sp_tel=(Spinner) findViewById(R.id.spinner_telefono);
        sp_mail=(Spinner) findViewById(R.id.spinner_email);
        et_nombre=(EditText) findViewById(R.id.txt_nombre);
        et_apellido=(EditText) findViewById(R.id.txt_apellido);
        et_direccion=(EditText) findViewById(R.id.txt_direccion);
        et_mail=(EditText) findViewById(R.id.txt_email);
        et_telefono=(EditText) findViewById(R.id.txt_telefono);
        et_fecha_nacimiento=(EditText) findViewById(R.id.txt_fecha_nacimiento);
        

        // enlace de datos con spinner
        String [] opciones={"casa","Trabajo","movil"};
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,opciones);
        sp_tel.setAdapter(adapter);
        sp_mail.setAdapter(adapter);
    }
}
