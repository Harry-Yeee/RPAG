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

    String month = "Selected Month";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        if(getIntent().hasExtra("month")){
            month = getIntent().getStringExtra("month");
        }

        PieChart pieChart = findViewById(R.id.pieChart);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(pieChart.this);

        String [] category = new String[] {"Housing", "Transportation", "Food", "Utilities", "Healthcare", "Insurance",
                "Save_Invest_Loan", "Entertainment", "Personal_Spending", "Miscellaneous"};

        ArrayList<PieEntry> categories = new ArrayList<>();

        double [] categorySpent = new double[10];
        for(int i = 0; i < 10; i++){
            try {
                Category categoryData = dataBaseHelper.getCategoryData(category[i], month);
                if (categoryData != null && categoryData.getCategoryMonth().equals(month) && categoryData.getCategorySpent() != 0.0) {
                    categorySpent[i] = categoryData.getCategorySpent();

                    categories.add(new PieEntry((float)categoryData.getCategorySpent(), category[i]));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*
        categories.add(new PieEntry((float)categorySpent[0],"Housing"));
        categories.add(new PieEntry((float)categorySpent[1], "Transportation"));
        categories.add(new PieEntry((float)categorySpent[2], "Food"));
        categories.add(new PieEntry((float)categorySpent[3], "Utilities"));
        categories.add(new PieEntry((float)categorySpent[4],"Healthcare"));
        categories.add(new PieEntry((float)categorySpent[5], "Insurance"));
        categories.add(new PieEntry((float)categorySpent[6], "Save_Invest_Loan"));
        categories.add(new PieEntry((float)categorySpent[7], "Entertainment"));
        categories.add(new PieEntry((float)categorySpent[8], "Personal_Spending"));
        categories.add(new PieEntry((float)categorySpent[9], "Miscellaneous"));
         */


        PieDataSet pieDataSet = new PieDataSet(categories, "Categories");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.setCenterText("Categories");
        pieChart.getDescription().setEnabled(false);
        pieChart.animateX(2000);

    }
}