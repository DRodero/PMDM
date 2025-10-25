package com.example.botonesylistas;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView1;
    private Button button1;
    private Button button2;

    private RecyclerView listaEjemplo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView1 = findViewById(R.id.textview1);
        textView1.setText(R.string.saludo);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        Button button3 = findViewById(R.id.button3);
        Spinner spinner1 = findViewById(R.id.spinner1);

        // Usamos un listener para el botón "Leer desplegable" usando una expresión lambda
        button3.setOnClickListener(view ->  {
            String tecnologíaSeleccionada = spinner1.getSelectedItem().toString();
            textView1.setText("Has seleccionado: " + tecnologíaSeleccionada);
        });

        // Cuando se pulsa el CheckBox, se cambia el color del fondo del layout principal
        CheckBox checkBox1 = findViewById(R.id.checkBox1);
        checkBox1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Cambiamos el fondo del layout principal a "fondo_oscuro"
                findViewById(R.id.main).setBackgroundColor(getResources().getColor(R.color.fondo_oscuro, getTheme()));
            }
            else {
                findViewById(R.id.main).setBackgroundColor(getResources().getColor(R.color.fondo_claro, getTheme()));

            }
        });

        // Cuando cambia el valor del RadioGroup, cambio el color del botón 3
        RadioGroup radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup1.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButton1) {
                button3.setBackgroundColor(getResources().getColor(R.color.rojo, getTheme()));
            } else if (checkedId == R.id.radioButton2) {
                button3.setBackgroundColor(getResources().getColor(R.color.amarillo, getTheme()));
            } else if (checkedId == R.id.radioButton3) {
                button3.setBackgroundColor(getResources().getColor(R.color.verde, getTheme()));
            }
        });

        // ******** Código para la lista de elementos ********

        // Referenciomos la lista en una variable
        listaEjemplo1 = findViewById(R.id.listaEjemplo1);

        // Creo una lista de elementos
        ArrayList<ElementoLista> elementos = new ArrayList<>();

        // Creo de forma aleatoria 20 elementos
        for (int i = 0; i < 20; i++) {
            elementos.add(new ElementoLista("Cabecera  " + i, "Texto  " + i));
        }

        // Establezco tamaño fijo de la lista
        listaEjemplo1.setHasFixedSize(true);

        // Creo un layout manager para la lista
        RecyclerView.LayoutManager llm = new LinearLayoutManager(this);
        listaEjemplo1.setLayoutManager(llm);

        // Creamos el adaptador
        AdaptadorEjemplo adaptador = new AdaptadorEjemplo(this, elementos);

        // Indicamos que el adaptador de nuestra lista es el que hemos creado
        listaEjemplo1.setAdapter(adaptador);

        // Refrescamos los elementos de la lista
        adaptador.refrescar();

        // ******** Fin código para la lista de elementos ********

    }

    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            textView1.setText("Has pulsado el botón 1");
        }
        else if (v.getId() == R.id.button2) {
            textView1.setText("Has pulsado el botón 2");
        }
    }
}