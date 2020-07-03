package com.example.rpag;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class barChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> items = new ArrayList<>();
        items.add(new BarEntry(3,10));
        items.add(new BarEntry(5, 15));
        items.add(new BarEntry(7, 25));
        items.add(new BarEntry(9, 20));
        items.add(new BarEntry(3,2));
        items.add(new BarEntry(8, 20));
        items.add(new BarEntry(1, 25));
        items.add(new BarEntry(6, 20));

        BarDataSet barDataSet = new BarDataSet(items, "Items");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("PRAG Bar Report");
        barChart.animateY(2000);
    }
}