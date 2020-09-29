package abhiroopkodandapursanjeeva.vest;

import android.os.Bundle;
import android.support.p000v4.view.InputDeviceCompat;
import android.support.p003v7.app.AppCompatActivity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class activity_graph extends AppCompatActivity {
    GraphView graph;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0010R.layout.activity_graph);
        Bundle extras = getIntent().getExtras();
        double[] tempArray = extras.getDoubleArray("data");
        boolean F = extras.getBoolean("unit");
        int size = extras.getInt("size");
        double time = 0.0d;
        this.graph = (GraphView) findViewById(C0010R.C0012id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        for (int i = 0; i < size; i++) {
            series.appendData(new DataPoint(time, tempArray[i]), true, size);
            time += 0.033d;
        }
        this.graph.addSeries(series);
        series.setColor(InputDeviceCompat.SOURCE_ANY);
        series.setDrawDataPoints(true);
        this.graph.getGridLabelRenderer().setHorizontalAxisTitle("Elapsed Minutes");
        if (F) {
            this.graph.getGridLabelRenderer().setVerticalAxisTitle("Temperature(F)");
        } else {
            this.graph.getGridLabelRenderer().setVerticalAxisTitle("Temperature(C)");
        }
    }
}
