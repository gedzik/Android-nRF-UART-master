package com.nordicsemi.nrfUARTv2;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        initGraph(graph);
    }

    public void initGraph(GraphView graph) {
        mSeries.setDrawBackground(true);
        mSeries.setAnimated(true);
        mSeries.setDrawDataPoints(true);
        mSeries.setTitle("People");

        graph.addSeries(mSeries);

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
    }

    private LineGraphSeries<DataPoint> mSeries;
}
