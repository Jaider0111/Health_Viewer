package Data;

import android.provider.Settings;

import java.util.ArrayList;
import com.example.proyecto.R;

import static androidx.core.content.res.TypedArrayUtils.getString;

public class Resultado_Fisico extends Resultado {
    private float indice_masa_corporal;
    private float peso;
    private float altura;
    private String estado_fisico;

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getIndice_masa_corporal() {
        return indice_masa_corporal;
    }

    public void setIndice_masa_corporal(float indice_masa_corporal) {
        this.indice_masa_corporal = indice_masa_corporal;
    }

    public String getEstado_fisico() {
        return estado_fisico;
    }

    public void setEstado_fisico(String estado_fisico) {
        this.estado_fisico = estado_fisico;
    }

    public Resultado_Fisico(int puntaje, ArrayList<String> consecuencias, ArrayList<String> recomendacion, float peso, float talla) {
        super(puntaje, consecuencias, recomendacion);
        this.peso = peso;
        this.altura = talla;
        calcular(peso,altura);
        estado(indice_masa_corporal);
    }

    public void calcular(float peso, float altura){
        float altura_1 = altura;
        float M_corporal= peso/(altura_1*altura_1);
        indice_masa_corporal = M_corporal;
    }

    public void estado(float indice){
        if(indice<18.5)estado_fisico = "Bajo Peso";
        else if(indice>=18.5 && indice<25)estado_fisico = "Normal";
        else if(indice>=25 && indice<30)estado_fisico = "Sobrepeso";
        else estado_fisico = "Obesidad";
    }

    public String consecuencia(){
        ArrayList<String>arreglo = super.getConsecuencias();
        if(indice_masa_corporal<18.5)return arreglo.get(0);
        else if(indice_masa_corporal>=18.5 && indice_masa_corporal<25)return arreglo.get(1);
        else if(indice_masa_corporal>=25 && indice_masa_corporal<30)return arreglo.get(2);
        else return arreglo.get(3);
    }
}
