package Data;

import java.util.ArrayList;

public class Resultado_Psicologico extends Resultado {
    private String nivel_estres;
    private String patron_comportamiento;
    private ArrayList<String> niveles;

    public String getNivel_estres() {
        return nivel_estres;
    }

    public void setNivel_estres(String nivel_estres) {
        this.nivel_estres = nivel_estres;
    }

    public String getPatron_comportamiento() {
        return patron_comportamiento;
    }

    public void setPatron_comportamiento(String patron_comportamiento) {
        this.patron_comportamiento = patron_comportamiento;
    }

    public ArrayList<String> getniveles() {
        return niveles;
    }

    public void setniveles(ArrayList<String> niveles) {
        this.niveles = niveles;
    }

    public Resultado_Psicologico(int puntaje, ArrayList<String> consecuencias, ArrayList<String> recomendacion, ArrayList<String> niveles) {
        super(puntaje, consecuencias, recomendacion);
        this.niveles = niveles;
        nivel();
        patron(niveles);
    }

    private void patron(ArrayList<String> arrayList) {
        patron_comportamiento = "Puede presentar niveles " + arrayList.get(0) + " de ansiedad por separación, niveles "
                + arrayList.get(1) + " de fobia social, niveles " + arrayList.get(2) + " de ataques de pánico, niveles " + arrayList.get(3) + " de ansiedad generalizada, niveles "
                + arrayList.get(4) + " de estrés agudo, niveles " + arrayList.get(5) + " de ira, niveles " + arrayList.get(6) + " de pérdida del control, niveles " + arrayList.get(7)
                + " efectos físicos del estrés, niveles " + arrayList.get(8) + " de incertidumbre constante, niveles " + arrayList.get(9) + " de mala adaptación.";
    }

    private void nivel() {
        float puntaje = super.getPuntaje();
        if (puntaje<=20) nivel_estres = "Leve";
        else if (puntaje<=30) nivel_estres = "Moderado";
        else nivel_estres = "Grave";
    }

    public String consToString(){
        String cons = "Las enfermedades derivadas del estres que puedes estar propenso a padecer son:" + '\n';
        ArrayList<String> arrayList = getConsecuencias();
        for(int i = 0; i < arrayList.size();i++){
            cons += arrayList.get(i) + '\n';
        }
        return cons;
    }
}
