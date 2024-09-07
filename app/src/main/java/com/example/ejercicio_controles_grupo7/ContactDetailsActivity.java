package com.example.ejercicio_controles_grupo7;

import static com.example.ejercicio_controles_grupo7.R.*;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class ContactDetailsActivity extends AppCompatActivity {
    private TextView tvNombreDato, tvAppellidoDato, tvTelefonoDato, tvTipoTelefonoDato, tvEmailDato,
            tvTipoEmailDato, tvDireccionDato, tvFechaNacimientoDato, tvInteresesDato, tvInfoDato, tvNivelEstudiosDato;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_contact_details);
        conexVariables();

        //leer json
        String jsonString = leerJSON();
        if (jsonString != null) {
            mostrarDatos(jsonString);
        }
    }

    private void conexVariables(){
        tvNombreDato = findViewById(id.tvDatoNombre);
        tvAppellidoDato = findViewById(R.id.tvDatoApellido);
        tvTelefonoDato = findViewById(R.id.tvDatoTelefono);
        tvTipoTelefonoDato = findViewById(R.id.tvTipoTelContacto);
        tvTipoEmailDato = findViewById(R.id.tvTipoEmailContacto);
        tvEmailDato = findViewById(R.id.tvDatoEmail);
        tvDireccionDato = findViewById(R.id.tvDatoDireccion);
        tvFechaNacimientoDato = findViewById(R.id.tvDatoFechaNac);
        tvInteresesDato = findViewById(id.tvDatoIntereses);
        tvInfoDato = findViewById(R.id.tvDatoInfo);
        tvNivelEstudiosDato = findViewById(R.id.tvDatoEstAlcanzado);
    }

    private String leerJSON() {
        try {
            FileInputStream inputStream = openFileInput("contacts.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            int read = inputStream.read(buffer);
            inputStream.close();

            if (read == size) {
                // Convertir el byte a string
                return new String(buffer, StandardCharsets.UTF_8);
            } else {
                Toast.makeText(this, "Error leyendo archivo", Toast.LENGTH_SHORT).show();
                return null;
            }

        } catch (IOException e) {
            Toast.makeText(this, "Error leyendo archivo JSON", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private void mostrarDatos(String jsonString) {
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            int max = jsonArray.length();
            boolean nomEncontrado = false;
            Intent intent = getIntent();
            String nombreSeleccionado = intent.getStringExtra("nombre");

            int i = 0;
            while (i < max && !nomEncontrado) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String nombre = jsonObject.getString("nombre");

                // Verificar si es el mismo
                if (nombre.equalsIgnoreCase(nombreSeleccionado)) {
                    // Extraer los datos
                    String apellido = jsonObject.getString("apellido");
                    String telefono = jsonObject.getString("telefono");
                    String email = jsonObject.getString("email");
                    String direccion = jsonObject.getString("direccion");
                    String fechaNac = jsonObject.getString("fechaNacimiento");
                    String tipoTelefono = jsonObject.getString("tipoTelefono");
                    String tipoEmail = jsonObject.getString("tipoEmail");
                    String nivelEstudios = jsonObject.getString("nivelEstudios");
                    String recibeInfo = jsonObject.getBoolean("recibeInformacion") ? "Si" : "No";

                    JSONArray interesesArray = jsonObject.getJSONArray("intereses");
                    StringBuilder intereses = new StringBuilder();
                    for (int j = 0; j < interesesArray.length(); j++) {
                        String interes = interesesArray.getString(j);
                        intereses.append(interes);
                        if (j < interesesArray.length() - 1) {
                            intereses.append(", ");
                        }
                    }

                    // Setear los views
                    tvNombreDato.setText(nombre);
                    tvAppellidoDato.setText(apellido);
                    tvTelefonoDato.setText(telefono);
                    tvTipoTelefonoDato.setText(tipoTelefono);
                    tvEmailDato.setText(email);
                    tvTipoEmailDato.setText(tipoEmail);
                    tvDireccionDato.setText(direccion);
                    tvFechaNacimientoDato.setText(fechaNac);
                    tvNivelEstudiosDato.setText(nivelEstudios);
                    tvInfoDato.setText(recibeInfo);
                    tvInteresesDato.setText(intereses.toString());

                    nomEncontrado = true;
                }
                i++;
            }

        } catch (JSONException e) {
            Toast.makeText(this, "Error procesando el JSON", Toast.LENGTH_SHORT).show();
        }
    }
}
