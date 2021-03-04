package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.R;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Test_Fisico extends AppCompatActivity {
    RadioButton pR1, pR2, pR3, pR4;
    RadioGroup p1;
    TextView pregunta, pesop, tallap;
    EditText entradap, entradat;
    int puntaje = 0, numero = 0, agrega;
    float  peso, talla;
    String pregunta1, pregunta2, pregunta3, pregunta4, pregunta5, pregunta6, pregunta7, pregunta8, pregunta9, pregunta10;
    ArrayList<String> preguntas;
    Button regresa, sig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__fisico);

        pR1 = findViewById(R.id.P2R1);
        pR2 = findViewById(R.id.P2R2);
        pR3 = findViewById(R.id.P2R3);
        pR4 = findViewById(R.id.P2R4);
        p1 = findViewById(R.id.P2);
        regresa = findViewById(R.id.reg1);
        sig = findViewById(R.id.siguiente1);
        pregunta = findViewById(R.id.pregunta1);
        entradap = findViewById(R.id.entpeso);
        entradat = findViewById(R.id.enttalla);
        pesop = findViewById(R.id.peso);
        tallap = findViewById(R.id.talla);
        pregunta1 = getString(R.string.pregF1); pregunta2 = getString(R.string.pregF2);
        pregunta3 = getString(R.string.pregF3); pregunta4 = getString(R.string.pregF4);
        pregunta5 = getString(R.string.pregF5); pregunta6 = getString(R.string.pregF6);
        pregunta7 = getString(R.string.pregF7); pregunta8 = getString(R.string.pregF8);
        pregunta9 = getString(R.string.pregF9); pregunta10 = getString(R.string.pregF0);
        preguntas = new ArrayList<>();
        preguntas.add(pregunta1); preguntas.add(pregunta2); preguntas.add(pregunta3);
        preguntas.add(pregunta4); preguntas.add(pregunta5); preguntas.add(pregunta6);
        preguntas.add(pregunta7); preguntas.add(pregunta8); preguntas.add(pregunta9);
        preguntas.add(pregunta10);
    }

    public void guardar(View view){
        agrega = 0;
        if (pR1.isChecked()) agrega = 1;
        if (pR2.isChecked()) agrega = 2;
        if (pR3.isChecked()) agrega = 3;
        if (pR4.isChecked()) agrega = 4;
        if(numero == 0){
            if(TextUtils.isEmpty(entradat.getText().toString().trim())){
                Toast.makeText(Test_Fisico.this,"Ingresa tu talla",Toast.LENGTH_LONG).show();
                return;
            }
            else if(TextUtils.isEmpty(entradap.getText().toString().trim())){
                Toast.makeText(Test_Fisico.this,"Ingresa tu peso",Toast.LENGTH_LONG).show();
                return;
            }
            else{
                peso = Float.parseFloat(entradap.getText().toString());
                talla = Float.parseFloat(entradat.getText().toString());
                pesop.setEnabled(false);
                pesop.setVisibility(View.INVISIBLE);
                tallap.setEnabled(false);
                tallap.setVisibility(View.INVISIBLE);
                entradap.setEnabled(false);
                entradap.setVisibility(View.INVISIBLE);
                entradat.setEnabled(false);
                entradat.setVisibility(View.INVISIBLE);
            }
        }
        if(agrega == 0)Toast.makeText(Test_Fisico.this,"Selecione una respuesta",Toast.LENGTH_LONG).show();
        else {
            puntaje += agrega;
            p1.clearCheck();
            numero += 1;
            regresa.setEnabled(true);
            if (numero < preguntas.size()) {
                pregunta.setText(preguntas.get(numero));
            } else almacenar(puntaje);
        }
    }

    public void deshacer(View view){
        puntaje -= agrega;
        numero -= 1;
        p1.clearCheck();
        pregunta.setText(preguntas.get(numero));
        regresa.setEnabled(false);
    }

    public void almacenar( int dato){
        try {
            OutputStreamWriter fout= new OutputStreamWriter(openFileOutput("datosFisico.txt", Context.MODE_APPEND));
            fout.write(String.valueOf(dato)+'\n');
            fout.close();
        } catch (IOException e) {
            Toast.makeText(Test_Fisico.this, "Error al escribir el Archivo", Toast.LENGTH_LONG).show();
        }
        Intent i = new Intent(Test_Fisico.this, Resultados.class);
        i.putExtra("tipoTest", "Fisico");
        i.putExtra("puntaje", String.valueOf(dato));
        i.putExtra("peso", String.valueOf(peso));
        i.putExtra("talla", String.valueOf(talla));
        startActivity(i);
    }
}