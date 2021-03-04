package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import UI.Inicio;
import UI.crearUsuario;

public class MainActivity extends AppCompatActivity {

    EditText cEmail;
    EditText cContrasena;
    Button cIniciar;
    Button cNuevoUser;
    CheckBox cGuardar;
    FirebaseAuth mAuth;
    ProgressDialog avance;

    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cEmail = findViewById(R.id.email);
        cContrasena = findViewById(R.id.contraseña);
        cIniciar = findViewById(R.id.iniciarses);
        cNuevoUser = findViewById(R.id.nuevouser);
        cGuardar = findViewById(R.id.guardar);

        mAuth = FirebaseAuth.getInstance();

        avance = new ProgressDialog(this);

        String corr = "";
        String cont = "";
        try{
            InputStreamReader archivo = new InputStreamReader(openFileInput("credenciales.txt"));
            BufferedReader bf = new BufferedReader(archivo);
            String lectura = bf.readLine();
            if(lectura != null){
                corr = lectura;
                cont = bf.readLine();
            }
        }catch (IOException e){
            Toast.makeText(MainActivity.this, "Error al leer el Archivo", Toast.LENGTH_LONG).show();
        }
        cEmail.setText(corr);
        cContrasena.setText(cont);
        if(!corr.equals("") && !cont.equals("")) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) cIniciar.callOnClick();
            else {
                Toast.makeText(MainActivity.this, "Iniciado en modo Desconeccion", Toast.LENGTH_LONG);
                Intent i = new Intent(MainActivity.this, Inicio.class);
                startActivity(i);
            }
        }
    }

    public void iniciar(View view) {
        String correo = cEmail.getText().toString().trim();
        String pass = cContrasena.getText().toString().trim();

        if (TextUtils.isEmpty(correo)) {
            Toast.makeText(MainActivity.this, "Ingresa un E-Mail", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(MainActivity.this, "Ingresa una contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        avance.setMessage("Iniciando sesion...");
        avance.show();

        mAuth.signInWithEmailAndPassword(correo, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if(cGuardar.isChecked()){
                                try {
                                    OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("credenciales.txt", Activity.MODE_PRIVATE));
                                    archivo.write(cEmail.getText().toString()+"\n");
                                    archivo.write(cContrasena.getText().toString());
                                    archivo.flush();
                                    archivo.close();
                                } catch (IOException e) {
                                    Toast.makeText(MainActivity.this, "Error al escribir el Archivo", Toast.LENGTH_LONG).show();
                                }
                            }
                            Intent i = new Intent(MainActivity.this, Inicio.class);
                            startActivity(i);
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                Toast.makeText(MainActivity.this, "Contraseña incorrecta", Toast.LENGTH_LONG).show();
                            } else{
                                Toast.makeText(MainActivity.this, "El usuario no existe", Toast.LENGTH_LONG).show();
                            }
                        }
                        avance.dismiss();
                    }
                });
    }

    public void nuevoUsuario(View view){
        Intent i = new Intent(MainActivity.this, crearUsuario.class);
        startActivity(i);
    }
}

