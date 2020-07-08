package com.example.rpag;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class barChart extends AppCompatActivity {
    String month = "Selected Month";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().hasExtra("month")){
            month = getIntent().getStringExtra("month");
        }

        setContentView(R.layout.activity_bar_chart);

        BarChart barChart = findViewById(R.id.barChart);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(barChart.this);

        String [] category = new String[] {"Housing", "Transportation", "Food", "Utilities", "Healthcare", "Insurance",
                "Save_Invest_Loan", "Entertainment", "Personal_Spending", "Miscellaneous"};

        //double [] categorySpent = new double[10];
        ArrayList<BarEntry> categories = new ArrayList<>();
        float j = (float) 0.5;
        for(int i = 0; i < 10; i++){
            try {
                Category categoryData = dataBaseHelper.getCategoryData(category[i], month);
                if (categoryData != null && categoryData.getCategoryMonth().equals(month)) {

                    categories.add(new BarEntry(j, (float)categoryData.getCategorySpent()));
                }else{
                    categories.add(new BarEntry(j, (float)0.0));
                }
                j++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*
        categories.add(new BarEntry((float) .5, (float)categorySpent[0]));
        categories.add(new BarEntry((float) 1.5, (float)categorySpent[1]));
        categories.add(new BarEntry((float) 2.5, (float)categorySpent[2]));
        categories.add(new BarEntry((float) 3.5, (float)categorySpent[3]));
        categories.add(new BarEntry((float) 4.5,(float)categorySpent[4]));
        categories.add(new BarEntry((float) 5.5, (float)categorySpent[5]));
        categories.add(new BarEntry((float) 6.5, (float)categorySpent[6]));
        categories.add(new BarEntry((float) 7.5, (float)categorySpent[7]));
        categories.add(new BarEntry((float) 8.5, (float)categorySpent[8]));
        categories.add(new BarEntry((float) 9.5, (float)categorySpent[9]));
         */




        BarDataSet barDataSet = new BarDataSet(categories, "Categories");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("BAR CHART");
        barChart.animateY(2000);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(category));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

        //barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(3);
        float barSpace = 0.1f;
        float groupSpace = 1f;

        barData.setBarWidth(0.16f);

        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(0+barChart.getBarData().getGroupWidth(groupSpace, barSpace)*10);
        barChart.getAxisLeft().setAxisMinimum(0);

        barChart.invalidate();

    }
}