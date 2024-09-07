package com.example.ejercicio_controles_grupo7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;



public class ListContactsActivity extends AppCompatActivity {
    private ListView lvContactos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);
        lvContactos = findViewById(R.id.lv_contactos);

        String jsonContactos = leerJSON();

        if (jsonContactos != null) {
            mostrarContactos(jsonContactos);
        }

        lvContactos.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(ListContactsActivity.this, ContactDetailsActivity.class);
            String seleccion = (String) lvContactos.getItemAtPosition(i);

            String [] desconcatenar = seleccion.split(" ");
            String nomSeleccionado = desconcatenar[0];

            intent.putExtra("nombre", nomSeleccionado);
            startActivity(intent);
        });
    }

    private void mostrarContactos(String jsonString) {
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            int max = jsonArray.length();
            ArrayAdapter<String> arrayContactos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());

            for (int i = 0; i < max; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String nombre = jsonObject.getString("nombre");
                String apellido = jsonObject.getString("apellido");
                String email = jsonObject.getString("email");

                // concatenar
                String contactoResumen = nombre + " " + apellido + " (" + email + ")";
                arrayContactos.add(contactoResumen);
            }

            // Setear el adaptador en el ListView
            lvContactos.setAdapter(arrayContactos);

        } catch (JSONException e) {
            Toast.makeText(this, "Error procesando el JSON", Toast.LENGTH_SHORT).show();
        }
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

    public void Volver(android.view.View view) {
        finish(); // Cierra el activity actual y regresa al anterior
    }
}
