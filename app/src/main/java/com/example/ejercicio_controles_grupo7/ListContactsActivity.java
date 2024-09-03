package com.example.ejercicio_controles_grupo7;

import static com.example.ejercicio_controles_grupo7.R.*;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import Entidades.Contacto;


public class ListContactsActivity extends AppCompatActivity {
    private ListView lvContactos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);
        lvContactos=(ListView) findViewById(R.id.lv_contactos);
        try {
            InputStream inputStream= getAssets().open("contacts.json");
            int size=inputStream.available();
            byte[] buffer= new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json= new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray= new JSONArray(json);
            int max= jsonArray.length();
            ArrayAdapter<String> arrayContactos= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
            JSONObject jsonObject;

            for (int i=0;i<max;i++){
                Contacto contacto = new Contacto();
                jsonObject=jsonArray.getJSONObject(i);
                String nombre= jsonObject.getString("nombre");
                String apellido= jsonObject.getString("apellido");
                String email= jsonObject.getString("email");
                contacto.setNombre(nombre);
                contacto.setApellido(apellido);
                contacto.setEmail(email);
                arrayContactos.add(contacto.toStringResume());


            }
            lvContactos.setAdapter(arrayContactos);



        }catch (IOException e){
            Toast.makeText(this, "error leyendo archivo JSON", Toast.LENGTH_SHORT).show();

        }catch(JSONException e){
            Toast.makeText(this, "error parsing json", Toast.LENGTH_SHORT).show();
        }

    }
}
