package com.example.ejercicio_controles_grupo7;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar el título en la barra de acción
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("EjercicioControles - Grupo 7");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_contact) {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_list_contacts) {
            Intent intent = new Intent(this, ListContactsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
