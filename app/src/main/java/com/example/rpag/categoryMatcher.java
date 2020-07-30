package com.example.rpag;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


public class categoryMatcher {
    public HashMap<String, String> I2C = new HashMap<>();         // item to category

    categoryMatcher(Context context) throws Exception {
        //System.out.println("Hash map initialized");
        // get file from raw folder
        InputStream inputStream = context.getResources().openRawResource(R.raw.itemlist);

        BufferedReader cats = new BufferedReader(new InputStreamReader(inputStream));

        // int i = 1; // testing purpose to check any format problem
        while (cats.ready()) {
            //String[] token = cats.readLine().trim().split(",");
            String[] token = cats.readLine().split(",");
            String item = token[0].trim();
            String cat = token[1].trim();
            I2C.put(item, cat);
            // System.out.println(i);
            // i++;
        }
        I2C.put("Vaccination", "Healthcare");
        //System.out.println(findCat("Eggs"));
    }

    public String findCat(String item){

        //item = item.toLowerCase();
       // item = item.substring(0, 1).toUpperCase() + item.substring(1);

        String cat = I2C.get(item);

        if(cat != null) {
            //if (cat.equals("sil")) cat = "Saving, Invest, Loan"; // sil = Saving, Invest, Loan
            return cat;
        }else {
            return "Miscellaneous";
        }
    }

}