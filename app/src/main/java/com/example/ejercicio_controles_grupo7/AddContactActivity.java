package com.example.ejercicio_controles_grupo7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
        conexVariables();

        // enlace de datos con spinner
        String [] opciones={"Casa","Trabajo","Movil"};
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,opciones);
        sp_tel.setAdapter(adapter);
        sp_mail.setAdapter(adapter);
    }

    public void conexVariables (){
        sp_tel=(Spinner) findViewById(R.id.spinner_telefono);
        sp_mail=(Spinner) findViewById(R.id.spinner_email);
        et_nombre=(EditText) findViewById(R.id.txt_nombre);
        et_apellido=(EditText) findViewById(R.id.txt_apellido);
        et_direccion=(EditText) findViewById(R.id.txt_direccion);
        et_mail=(EditText) findViewById(R.id.txt_email);
        et_telefono=(EditText) findViewById(R.id.txt_telefono);
        et_fecha_nacimiento=(EditText) findViewById(R.id.txt_fecha_nacimiento);

    }

    public void ContinuarButton(View view){
        if (validarDatos()) {
            Intent intent = new Intent(AddContactActivity.this, AddContact2Activity.class);

            intent.putExtra("nombre", et_nombre.getText().toString().trim());
            intent.putExtra("apellido", et_apellido.getText().toString().trim());
            intent.putExtra("telefono", et_telefono.getText().toString().trim());
            intent.putExtra("email", et_mail.getText().toString().trim());
            intent.putExtra("direccion", et_direccion.getText().toString().trim());
            intent.putExtra("fechaNacimiento", et_fecha_nacimiento.getText().toString().trim());

            intent.putExtra("tipoTelefono", sp_tel.getSelectedItem().toString());
            intent.putExtra("tipoEmail", sp_mail.getSelectedItem().toString());

            startActivity(intent);
        } else {

            Toast.makeText(AddContactActivity.this, "Por favor, corrige los errores en el formulario.", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean validarDatos() {
        EditText[] campos = {et_nombre, et_apellido, et_telefono, et_mail, et_fecha_nacimiento};
        String[] mensajesError = {
                "El campo nombre no puede estar vacío",
                "El campo apellido no puede estar vacío",
                "El campo teléfono no puede estar vacío",
                "El campo email no puede estar vacío",
                "El campo fecha de nacimiento no puede estar vacío"
        };

        // Validar campos vacíos
        for (int i = 0; i < campos.length; i++) {
            if (campos[i].getText().toString().trim().isEmpty()) {
                campos[i].setError(mensajesError[i]);
                return false;
            }
        }

        if (!et_nombre.getText().toString().matches("[a-zA-Z]+")) {
            et_nombre.setError("El nombre no puede contener números");
            return false;
        }
        if (!et_apellido.getText().toString().matches("[a-zA-Z]+")) {
            et_apellido.setError("El apellido no puede contener números");
            return false;
        }
        if (!et_telefono.getText().toString().matches("[0-9-]+")) {
            et_telefono.setError("El teléfono solo puede contener números y guiones");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(et_mail.getText().toString()).matches()) {
            et_mail.setError("Ingrese un email válido");
            return false;
        }
        if (!et_fecha_nacimiento.getText().toString().matches("\\d{2}/\\d{2}/\\d{4}")) {
            et_fecha_nacimiento.setError("Ingrese una fecha válida en formato dd/mm/yyyy");
            return false;
        }

        return true;
    }

    public void Volver(android.view.View view) {
        finish(); // Cierra el activity actual y regresa al anterior
    }
}
