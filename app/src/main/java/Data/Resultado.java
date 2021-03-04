package Data;

import java.util.ArrayList;
import java.util.List;

public abstract class Resultado {
    private int puntaje;
    private ArrayList <String> consecuencias;
    private ArrayList<String> recomendacion;

    public ArrayList<String> getConsecuencias() {
        return consecuencias;
    }

    public void setConsecuencias(ArrayList<String> consecuencias) {
        this.consecuencias = consecuencias;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getRecomendacion() {
        String recomend;
        if (puntaje<=8) recomend = recomendacion.get(0);
        else if (puntaje <= 16) recomend = recomendacion.get(1);
        else if (puntaje <= 24) recomend = recomendacion.get(2);
        else if (puntaje <= 32) recomend = recomendacion.get(3);
        else recomend = recomendacion.get(4);
        return recomend;
    }

    public void setRecomendacion(ArrayList<String> recomendacion) {
        this.recomendacion = recomendacion;
    }

    public Resultado(int puntaje, ArrayList<String> consecuencias, ArrayList<String> recomendacion) {
        this.puntaje = puntaje;
        this.consecuencias = consecuencias;
        this.recomendacion = recomendacion;
    }
}
