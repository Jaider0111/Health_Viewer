package UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class crearUsuario extends AppCompatActivity {

    EditText cEmail;
    EditText cContraseña;
    EditText compContraseña;
    Button crear;
    CheckBox cMantener;
    private FirebaseAuth mAuth;
    ProgressDialog avance;

    FirebaseAuthUserCollisionException ex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        cEmail = findViewById(R.id.regemail);
        cContraseña = findViewById(R.id.regcontraseña);
        compContraseña = findViewById(R.id.compcontraseña);
        crear = findViewById(R.id.crearuser);
        cMantener = findViewById(R.id.mantener);

        mAuth = FirebaseAuth.getInstance();

        avance = new ProgressDialog(this);
    }

    public void crearlol(View view) {
        String correo = cEmail.getText().toString();
        String pass = cContraseña.getText().toString().trim();
        String confpass = compContraseña.getText().toString().trim();

        if (TextUtils.isEmpty(correo)) {
            Toast.makeText(crearUsuario.this, "Ingresa un E-Mail", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(crearUsuario.this, "Ingresa una contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(confpass)) {
            Toast.makeText(crearUsuario.this, "Confirma tu contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        if (!TextUtils.equals(pass, confpass)) {
            Toast.makeText(crearUsuario.this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            return;
        }

        avance.setMessage("Creando usuario...");
        avance.show();

        mAuth.createUserWithEmailAndPassword(correo, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if(cMantener.isChecked()){
                                try {
                                    OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("credenciales.txt", Activity.MODE_PRIVATE));
                                    archivo.write(cEmail.getText().toString()+"\n");
                                    archivo.write(cContraseña.getText().toString());
                                    archivo.flush();
                                    archivo.close();
                                } catch (IOException e) {
                                    Toast.makeText(crearUsuario.this, "Error al escribir el Archivo", Toast.LENGTH_LONG).show();
                                }
                            }
                            Intent i = new Intent(crearUsuario.this, Inicio.class);
                            startActivity(i);
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(crearUsuario.this, "El usuario ya existe", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(crearUsuario.this, "Autenticacion fallida", Toast.LENGTH_LONG).show();
                            }
                        }
                        avance.dismiss();
                    }
                });
    }
}
