package com.example.rpag;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class pieChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        PieChart pieChart = findViewById(R.id.pieChart);

        ArrayList<PieEntry> categories = new ArrayList<>();
        categories.add(new PieEntry(2,10));
        categories.add(new PieEntry(5, 15));
        categories.add(new PieEntry(7, 25));
        categories.add(new PieEntry(9, 20));
        categories.add(new PieEntry(3,2));
        categories.add(new PieEntry(8, 20));
        categories.add(new PieEntry(1, 25));
        categories.add(new PieEntry(6, 20));

        PieDataSet pieDataSet = new PieDataSet(categories, "Categories");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.setCenterText("Categories");
        pieChart.getDescription().setEnabled(false);
        pieChart.animate();

    }
}