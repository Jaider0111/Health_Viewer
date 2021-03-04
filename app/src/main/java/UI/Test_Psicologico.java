package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Test_Psicologico extends AppCompatActivity {
    RadioButton pR1, pR2, pR3, pR4;
    RadioGroup p1;
    TextView pregunta;
    int puntaje = 0, numero = 0, agrega;
    String pregunta1, pregunta2, pregunta3, pregunta4, pregunta5, pregunta6, pregunta7, pregunta8, pregunta9, pregunta10;
    String respuesta11, respuesta12, respuesta13, respuesta14;
    String respuesta21, respuesta22, respuesta23, respuesta24;
    String respuesta31, respuesta32, respuesta33, respuesta34;
    String respuesta41, respuesta42, respuesta43, respuesta44;
    String respuesta51, respuesta52, respuesta53, respuesta54;
    String respuesta61, respuesta62, respuesta63, respuesta64;
    String respuesta71, respuesta72, respuesta73, respuesta74;
    String respuesta81, respuesta82, respuesta83, respuesta84;
    String respuesta91, respuesta92, respuesta93, respuesta94;
    String respuesta101, respuesta102, respuesta103, respuesta104;
    ArrayList<String> preguntas, respuestas1, respuestas2, respuestas3, respuestas4, niveles;;
    Button regresa, sig;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__psicologico);

        pR1 = findViewById(R.id.P1R1);
        pR2 = findViewById(R.id.P1R2);
        pR3 = findViewById(R.id.P1R3);
        pR4 = findViewById(R.id.P1R4);
        p1 = findViewById(R.id.P1);
        regresa = findViewById(R.id.reg);
        pregunta = findViewById(R.id.pregunta);
        pregunta1 = getString(R.string.preg1); pregunta2 = getString(R.string.preg2);
        pregunta3 = getString(R.string.preg3); pregunta4 = getString(R.string.preg4);
        pregunta5 = getString(R.string.preg5); pregunta6 = getString(R.string.preg6);
        pregunta7 = getString(R.string.preg7); pregunta8 = getString(R.string.preg8);
        pregunta9 = getString(R.string.preg9); pregunta10 = getString(R.string.preg10);
        preguntas = new ArrayList<>();
        preguntas.add(pregunta1); preguntas.add(pregunta2); preguntas.add(pregunta3);
        preguntas.add(pregunta4); preguntas.add(pregunta5); preguntas.add(pregunta6);
        preguntas.add(pregunta7); preguntas.add(pregunta8); preguntas.add(pregunta9); preguntas.add(pregunta10);
        respuesta11 = getString(R.string.p1r1); respuesta12 = getString(R.string.p1r2);
        respuesta13 = getString(R.string.p1r3); respuesta14 = getString(R.string.p1r4);
        respuesta21 = getString(R.string.p2r1); respuesta22 = getString(R.string.p2r2);
        respuesta23 = getString(R.string.p2r3); respuesta24 = getString(R.string.p2r4);
        respuesta31 = getString(R.string.p3r1); respuesta32 = getString(R.string.p3r2);
        respuesta33 = getString(R.string.p3r3); respuesta34 = getString(R.string.p3r4);
        respuesta41 = getString(R.string.p4r1); respuesta42 = getString(R.string.p4r2);
        respuesta43 = getString(R.string.p4r3); respuesta44 = getString(R.string.p4r4);
        respuesta51 = getString(R.string.p5r1); respuesta52 = getString(R.string.p5r2);
        respuesta53 = getString(R.string.p5r3); respuesta54 = getString(R.string.p5r4);
        respuesta61 = getString(R.string.p6r1); respuesta62 = getString(R.string.p6r2);
        respuesta63 = getString(R.string.p6r3); respuesta64 = getString(R.string.p6r4);
        respuesta71 = getString(R.string.p7r1); respuesta72 = getString(R.string.p7r2);
        respuesta73 = getString(R.string.p7r3); respuesta74 = getString(R.string.p7r4);
        respuesta81 = getString(R.string.p8r1); respuesta82 = getString(R.string.p8r2);
        respuesta83 = getString(R.string.p8r3); respuesta84 = getString(R.string.p8r4);
        respuesta91 = getString(R.string.p9r1); respuesta92 = getString(R.string.p9r2);
        respuesta93 = getString(R.string.p9r3); respuesta94 = getString(R.string.p9r4);
        respuesta101 = getString(R.string.p10r1); respuesta102 = getString(R.string.p10r2);
        respuesta103 = getString(R.string.p10r3); respuesta104 = getString(R.string.p10r4);
        respuestas1 = new ArrayList<>();
        respuestas1.add(respuesta11); respuestas1.add(respuesta21); respuestas1.add(respuesta31);
        respuestas1.add(respuesta41); respuestas1.add(respuesta51); respuestas1.add(respuesta61);
        respuestas1.add(respuesta71); respuestas1.add(respuesta81); respuestas1.add(respuesta91); respuestas1.add(respuesta101);
        respuestas2 = new ArrayList<>();
        respuestas2.add(respuesta12); respuestas2.add(respuesta22); respuestas2.add(respuesta32);
        respuestas2.add(respuesta42); respuestas2.add(respuesta52); respuestas2.add(respuesta62);
        respuestas2.add(respuesta72); respuestas2.add(respuesta82); respuestas2.add(respuesta92); respuestas2.add(respuesta102);
        respuestas3 = new ArrayList<>();
        respuestas3.add(respuesta13); respuestas3.add(respuesta23); respuestas3.add(respuesta33);
        respuestas3.add(respuesta43); respuestas3.add(respuesta53); respuestas3.add(respuesta63);
        respuestas3.add(respuesta73); respuestas3.add(respuesta83); respuestas3.add(respuesta93); respuestas3.add(respuesta103);
        respuestas4 = new ArrayList<>();
        respuestas4.add(respuesta14); respuestas4.add(respuesta24); respuestas4.add(respuesta34);
        respuestas4.add(respuesta44); respuestas4.add(respuesta54); respuestas4.add(respuesta64);
        respuestas4.add(respuesta74); respuestas4.add(respuesta84); respuestas4.add(respuesta94); respuestas4.add(respuesta104);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        niveles = new ArrayList<>();
    }

    public void guardar(View view){
        agrega = 0;
        if (pR1.isChecked()){
            agrega = 1;
            niveles.add(numero, "bajos");
        }
        if (pR2.isChecked()){
            agrega = 2;
            niveles.add(numero, "medio-bajos");
        }
        if (pR3.isChecked()){
            agrega = 3;
            niveles.add(numero, "medio-altos");
        }
        if (pR4.isChecked()){
            agrega = 4;
            niveles.add(numero, "altos");
        }

        if(agrega == 0)Toast.makeText(Test_Psicologico.this,"Selecione una respuesta",Toast.LENGTH_LONG).show();
        else {
            puntaje += agrega;
            p1.clearCheck();
            numero += 1;
            regresa.setEnabled(true);
            if (numero < preguntas.size()) {
                pregunta.setText(preguntas.get(numero));
                pR1.setText(respuestas1.get(numero));
                pR2.setText(respuestas2.get(numero));
                pR3.setText(respuestas3.get(numero));
                pR4.setText(respuestas4.get(numero));
            } else almacenar(puntaje);
        }
    }

    public void deshacer(View view){
        puntaje -= agrega;
        numero -= 1;
        niveles.set(numero, "");
        p1.clearCheck();
        pregunta.setText(preguntas.get(numero));
        pR1.setText(respuestas1.get(numero));
        pR2.setText(respuestas2.get(numero));
        pR3.setText(respuestas3.get(numero));
        pR4.setText(respuestas4.get(numero));
        regresa.setEnabled(false);
    }

    public void almacenar( int dato){
        try {
            OutputStreamWriter fout= new OutputStreamWriter(openFileOutput("datosPsico.txt", Context.MODE_APPEND));
            fout.write(String.valueOf(dato)+'\n');
            fout.close();
        } catch (IOException e) {
            Toast.makeText(Test_Psicologico.this, "Error al escribir el Archivo", Toast.LENGTH_LONG).show();
        }
        Intent i = new Intent(Test_Psicologico.this, Resultados.class);
        i.putExtra("tipoTest", "Psicologico");
        i.putExtra("puntaje", String.valueOf(dato));
        i.putExtra("niveles", niveles);
        startActivity(i);
    }
}