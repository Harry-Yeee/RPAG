package com.example.rpag;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class deleteData extends AppCompatActivity {
    ListView deleteViewList;
    DataBaseHelper dataBaseHelper;
    String month = "month";
    String category = "category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);

        deleteViewList = (ListView)findViewById(R.id.deleteViewList);
        dataBaseHelper = new DataBaseHelper(deleteData.this);

        if(getIntent().hasExtra("month") && getIntent().hasExtra("category")){
            category = getIntent().getStringExtra("category");
            month = getIntent().getStringExtra("month");
            showSpecificList();
        }else{
            showList(dataBaseHelper);
        }



        deleteViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Item clickedItem = (Item)adapterView.getItemAtPosition(position);
                dataBaseHelper.deleteData(clickedItem);
                if(getIntent().hasExtra("month") && getIntent().hasExtra("category")){
                    showSpecificList();
                }else{
                    showList(dataBaseHelper);
                }
                Toast.makeText(deleteData.this, "Deleted" + clickedItem.toString(), Toast.LENGTH_SHORT);

            }
        });
    }

    private void showSpecificList() {
        List<Item> itemList = dataBaseHelper.viewData();
        List<Item> list = new ArrayList<>();
        for(Item items: itemList){
            if(items.getItemCategory().equals(category) && items.getItemDate().equals(month)) {
                list.add(items);
            }
        }

        ArrayAdapter itemArrayAdapter = new ArrayAdapter<Item>(deleteData.this, android.R.layout.simple_expandable_list_item_1, list);

        deleteViewList.setAdapter(itemArrayAdapter);
    }

    public void showList(DataBaseHelper dataBaseHelper){
        List<Item> itemList = dataBaseHelper.viewData();

        ArrayAdapter itemArrayAdapter = new ArrayAdapter<Item>(deleteData.this, android.R.layout.simple_expandable_list_item_1, itemList);

        deleteViewList.setAdapter(itemArrayAdapter);

    }

}