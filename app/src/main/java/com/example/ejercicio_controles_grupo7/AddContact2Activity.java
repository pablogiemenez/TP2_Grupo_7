package com.example.ejercicio_controles_grupo7;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AddContact2Activity extends AppCompatActivity {

    private RadioGroup rgEstudios;
    private CheckBox cbDeporte, cbMusica, cbArte, cbTecnologia;
    private Switch swSi;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact2);

        // Referencias a los elementos de la UI
        rgEstudios = findViewById(R.id.rgEstudios);
        cbDeporte = findViewById(R.id.cbDeporte);
        cbMusica = findViewById(R.id.cbMusica);
        cbArte = findViewById(R.id.cbArte);
        cbTecnologia = findViewById(R.id.cbTecnologia);
        swSi = findViewById(R.id.swSi);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Acción al presionar el botón guardar
        btnGuardar.setOnClickListener(v -> {
            // Obtener la selección del RadioGroup
            int selectedEstudioId = rgEstudios.getCheckedRadioButtonId();
            RadioButton selectedEstudio = findViewById(selectedEstudioId);

            if (selectedEstudio == null) {
                Toast.makeText(AddContact2Activity.this, "Por favor, selecciona un nivel de estudios", Toast.LENGTH_SHORT).show();
                return;
            }

            // Obtener los datos del primer formulario desde el intent
            Intent intent = getIntent();
            String nombre = intent.getStringExtra("nombre");
            String apellido = intent.getStringExtra("apellido");
            String telefono = intent.getStringExtra("telefono");
            String email = intent.getStringExtra("email");
            String direccion = intent.getStringExtra("direccion");
            String fechaNacimiento = intent.getStringExtra("fechaNacimiento");
            String tipoTelefono = intent.getStringExtra("tipoTelefono");
            String tipoEmail = intent.getStringExtra("tipoEmail");

            // Crear un objeto JSON con todos los datos
            JSONObject contactObject = new JSONObject();
            try {
                contactObject.put("nombre", nombre);
                contactObject.put("apellido", apellido);
                contactObject.put("telefono", telefono);
                contactObject.put("email", email);
                contactObject.put("direccion", direccion);
                contactObject.put("fechaNacimiento", fechaNacimiento);
                contactObject.put("tipoTelefono", tipoTelefono);
                contactObject.put("tipoEmail", tipoEmail);
                contactObject.put("nivelEstudios", selectedEstudio.getText().toString());

                JSONArray intereses = new JSONArray();
                if (cbDeporte.isChecked()) intereses.put(cbDeporte.getText().toString());
                if (cbMusica.isChecked()) intereses.put(cbMusica.getText().toString());
                if (cbArte.isChecked()) intereses.put(cbArte.getText().toString());
                if (cbTecnologia.isChecked()) intereses.put(cbTecnologia.getText().toString());
                contactObject.put("intereses", intereses);

                contactObject.put("recibeInformacion", swSi.isChecked());

                // Guardar en un archivo interno
                saveToFile(contactObject);

                //Volver al inicio si se guardo
                startActivity(new Intent(AddContact2Activity.this, MainActivity.class));
            } catch (JSONException e) {
                Log.e("AddContact2Activity", "Error al crear el objeto JSON", e);
                Toast.makeText(AddContact2Activity.this, "Error al guardar los datos: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

    private void saveToFile(JSONObject contactObject) {
        String fileName = "contacts.json";
        try {
            // Leer el archivo existente o inicializar si no existe
            FileInputStream fis = null;
            String existingData;

            try {
                fis = openFileInput(fileName);
                byte[] buffer = new byte[fis.available()];
                int bytesRead = fis.read(buffer);
                if (bytesRead > 0) {
                    existingData = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
                } else {
                    existingData = "";
                }
            } catch (IOException e) {
                Log.e("SaveToFileError", "Archivo no encontrado, se creará uno nuevo", e);
                existingData = "";
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }

            // Agregar los nuevos datos al archivo existente
            JSONArray contactsArray;
            if (existingData.isEmpty()) {
                contactsArray = new JSONArray();
            } else {
                contactsArray = new JSONArray(existingData);
            }
            contactsArray.put(contactObject);

            // Escribir de nuevo el archivo con los datos actualizados
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(contactsArray.toString().getBytes());
            fos.close();

            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_LONG).show();
        } catch (IOException | JSONException e) {
            Log.e("SaveToFileError", "Error al guardar los datos", e);
            Toast.makeText(this, "Error al guardar los datos: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
