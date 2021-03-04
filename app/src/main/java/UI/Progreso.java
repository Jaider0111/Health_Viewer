package UI;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.example.proyecto.R;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Progreso extends AppCompatActivity {
    LineChart progPsico;
    LineChart progFisico;
    ArrayList<String> valoresPsico;
    ArrayList<String> valoresFisico;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progreso);
        progPsico = findViewById(R.id.graficaPsico);
        progFisico = findViewById(R.id.graficaFisico);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        valoresPsico = new ArrayList<>();
        valoresFisico = new ArrayList<>();
        try {
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("datosPsico.txt")));
            String lectura = fin.readLine();
            while (lectura != null) {
                valoresPsico.add(lectura);
                lectura = fin.readLine();
            }
            fin.close();
        } catch (Exception e) {
            Toast.makeText(Progreso.this, "Error al leer el Archivo", Toast.LENGTH_LONG).show();
        }
        try {
            BufferedReader hj = new BufferedReader(new InputStreamReader(openFileInput("datosFisico.txt")));
            String lectura = hj.readLine();
            while (lectura != null) {
                valoresFisico.add(lectura);
                lectura = hj.readLine();
            }
            hj.close();
        } catch (Exception e) {
            Toast.makeText(Progreso.this, "Error al leer el Archivo", Toast.LENGTH_LONG).show();
        }
        if(valoresPsico == null){
            valoresPsico = new ArrayList<>();
            valoresPsico.add("0");
        }
        if(valoresFisico == null){
            valoresFisico = new ArrayList<>();
            valoresFisico.add("0");
        }
        createCharts(valoresPsico, "Progreso Psicomental", progPsico);
        createCharts(valoresFisico, "Progreso Fisico", progFisico);
    }
    private Chart getSameChart(Chart chart, String desc, int colortext, int background, int animacion, ArrayList<String> valores){
        chart.getDescription().setText(desc);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        chart.getDescription().setPosition(width/1.3f,height/20 );
        chart.getDescription().setTextColor(Color.rgb(83,69,21));
        chart.getDescription().setTextSize(50);
        chart.setNoDataTextColor(colortext);
        chart.setBackgroundColor(background);
        chart.animateY(animacion);
        legend(chart, valores);
        return chart;
    }

    private void legend(Chart chart, ArrayList<String> valores) {
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setTextSize(18);

        ArrayList<LegendEntry> entries = new ArrayList<>();
        LegendEntry entry1 = new LegendEntry();
        entry1.formColor = Color.GREEN;
        entry1.label = "Eje x: Numero del test";
        entries.add(entry1);
        LegendEntry entry2 = new LegendEntry();
        entry2.formColor = Color.BLUE;
        entry2.label = "Eje y: Puntaje";
        entries.add(entry2);
        legend.setCustom(entries);
    }

    private ArrayList<Entry> getLineEntries(ArrayList<String> valores) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < valores.size(); i++)
            entries.add(new Entry(i, Integer.parseInt(valores.get(i))));
        return entries;
    }

    private void axisX(XAxis xAxis, ArrayList<String> valores){
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineColor(Color.BLACK);
        xAxis.setAxisLineWidth(2);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(15);
        xAxis.setAxisMinimum(0);
        ArrayList<String> ejex = new ArrayList<>();
        for (int i = 0; i < valores.size(); i++)
            ejex.add(String.valueOf(i+1));
        xAxis.setValueFormatter(new IndexAxisValueFormatter(ejex));
    }

    private void axisXLeft(YAxis yAxis){
        yAxis.setSpaceTop(40);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setAxisLineWidth(2);
        yAxis.setTextColor(Color.BLACK);
        yAxis.setTextSize(15);
    }

    private void axisRight(YAxis yAxis){
        yAxis.setEnabled(false);
    }

    public void createCharts(ArrayList<String> valores, String name, LineChart prog){
        prog = (LineChart) getSameChart(prog,name, Color.BLACK, Color.TRANSPARENT, 3000, valores);
        prog.setDrawGridBackground(true);
        axisX(prog.getXAxis(), valores);
        axisRight(prog.getAxisRight());
        axisXLeft(prog.getAxisLeft());
        prog.setData(getLineData(valores));
        prog.invalidate();
    }

    private DataSet getData(DataSet dataSet){
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(15);
        return dataSet;
    }

    private LineData getLineData(ArrayList<String> valores){
        LineDataSet lineDataSet = (LineDataSet) getData(new LineDataSet(getLineEntries(valores),""));
        LineData lineData = new LineData(lineDataSet);
        lineDataSet.setLineWidth(2.5f);
        lineDataSet.setCircleColors(Color.GREEN);
        lineDataSet.setCircleRadius(5);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        return lineData;
    }
}
