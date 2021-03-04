package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyecto.R;

public class Inicio extends AppCompatActivity {

    Button testPsico;
    Button testFisico;
    Button acercaDe;
    Button prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        testPsico = findViewById(R.id.testpsico);
        testFisico = findViewById(R.id.testfisico);
        acercaDe = findViewById(R.id.acercade);
        prog = findViewById(R.id.progreso);
    }

    public void accionTestPsico(View view){
        Intent i = new Intent(Inicio.this, Test_Psicologico.class);
        startActivity(i);
    }

    public void accionTestFisico(View view){
        Intent i = new Intent(Inicio.this, Test_Fisico.class);
        startActivity(i);
    }

    public void accionTestAcercaDe(View view){
        Intent i = new Intent(Inicio.this, Acerca_De.class);
        startActivity(i);
    }

    public void accionProgreso(View view){
        Intent i = new Intent(Inicio.this, Progreso.class);
        startActivity(i);
    }
}
