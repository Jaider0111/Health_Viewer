package UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Data.Resultado;
import Data.Resultado_Fisico;
import Data.Resultado_Psicologico;

public class Resultados extends AppCompatActivity {
    TextView res,punt, rec, cons, otrom;
    String tipoTest;
    int puntaje;
    FirebaseAuth mAuth;
    DatabaseReference myRef;
    Resultado resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        res = findViewById(R.id.mensaje);
        punt = findViewById(R.id.puntaje);
        rec = findViewById(R.id.recomendaciones);
        cons = findViewById(R.id.consecuencias);
        otrom = findViewById(R.id.otro);
        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();
        recuperardatos();
        if(TextUtils.equals(tipoTest, "Fisico")){
            Resultado_Fisico resultado_fisico = (Resultado_Fisico) resultado;
            cons.setText(resultado_fisico.consecuencia());
            rec.setText(resultado_fisico.getRecomendacion());
            String otro = "Su IMC es:" + resultado_fisico.getIndice_masa_corporal() + " . Por lo tanto su Estado Fisico es: "+ resultado_fisico.getEstado_fisico();
            otrom.setText(otro);
        }else {
            Resultado_Psicologico resultadoPsicologico = (Resultado_Psicologico) resultado;
            String cosa = "Te encuentras en el nivel de estres: " + resultadoPsicologico.getNivel_estres();
            rec.setText(cosa);
            otrom.setText(resultadoPsicologico.getPatron_comportamiento());
            cons.setText(resultadoPsicologico.consToString());
        }
    }

    private void recuperardatos() {
        Bundle datos = getIntent().getExtras();
        tipoTest = datos.getString("tipoTest");
        String parte = String.valueOf(res.getText());
        parte += ' ' + tipoTest+" fue:";
        res.setText(parte);
        puntaje = Integer.parseInt(datos.getString("puntaje"));
        punt.setText(String.valueOf(puntaje));
        if(TextUtils.equals(tipoTest, "Psicologico")){
            ArrayList<String> niveles = datos.getStringArrayList("niveles");
            ArrayList<String> cons = new ArrayList<>();
            cons.add("Trastorno de ansiedad por separación");
            cons.add("Fobia específica");
            cons.add("Trastorno de ansiedad social");
            cons.add("Trastorno de pánico");
            cons.add("Trastorno de ansiedad generalizada");
            cons.add("Trastorno de estrés postraumático");
            cons.add("Trastorno de estrés agudo");
            cons.add("Trastorno de adaptación");
            resultado = new Resultado_Psicologico(puntaje, cons, new ArrayList<String>(), niveles );
        }
        else {
            float peso = Float.parseFloat(datos.getString("peso"));
            float talla = Float.parseFloat(datos.getString("talla"));
            ArrayList consecuencias = new ArrayList();
            consecuencias.add(getString(R.string.Rt_fisico1));
            consecuencias.add(getString(R.string.Rt_fisico2));
            consecuencias.add(getString(R.string.Rt_fisico3));
            consecuencias.add(getString(R.string.Rt_fisico4));
            consecuencias.add(getString(R.string.Rt_fisico5));
            ArrayList recomendaciones = new ArrayList();
            recomendaciones.add(getString(R.string.Cf1));
            recomendaciones.add(getString(R.string.Cf2));
            recomendaciones.add(getString(R.string.Cf3));
            recomendaciones.add(getString(R.string.Cf4));

            resultado = new Resultado_Fisico(puntaje, recomendaciones, consecuencias , peso, talla);
        }
    }


    public void inicio(View view){
        Intent i = new Intent(Resultados.this, Inicio.class);
        startActivity(i);
    }

    public void misestad(View view){
        Intent i = new Intent(Resultados.this, Progreso.class);
        startActivity(i);
    }
}
