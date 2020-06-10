package com.example.waqas.dashboardapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.waqas.dashboardapp.Adapters.ItemListAdapter;
import com.example.waqas.dashboardapp.Models.Item;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryActivity extends AppCompatActivity {

    List<Item> allItems;
    List<Item> items;
    TextView title;
    Toolbar toolbar;
    ImageButton backButton;
    ListView itemList;
    ItemListAdapter itemListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        backButton = (ImageButton) findViewById(R.id.back_btn);
        itemList = (ListView) findViewById(R.id.item_list);
        setSupportActionBar(toolbar);

        allItems = new ArrayList<Item>();
        items = new ArrayList<Item>();
        String titleValue = getIntent().getStringExtra("title");
        String childName = getIntent().getStringExtra("subcategory");
        allItems = getIntent().getParcelableArrayListExtra("allitems");

        Log.d("Data" , "Text" + allItems.toString());

        title = (TextView) findViewById(R.id.toolbar_heading);
        title.setText(titleValue + " " + childName);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        for (int i = 0 ; i < allItems.size() ; i++ ){
            if(allItems.get(i).getMainCategory().equals(titleValue)){
                if (allItems.get(i).getSubCategory().equals(childName)){
                    items.add(allItems.get(i));
                }
            }
        }

        itemListAdapter = new ItemListAdapter(getApplicationContext(),R.layout.list_of_item,items);
        itemList.setAdapter(itemListAdapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Item item = items.get(position);

                Intent intent = new Intent(SubCategoryActivity.this,ItemDescriptionActivity.class);
                intent.putExtra("title", item.getMainCategory());
                intent.putExtra("logo_image", item.getLogoImage());
                intent.putExtra("subcategory",item.getSubCategory());
                intent.putExtra("item_name",item.getName());
                intent.putExtra("item_image",item.getImage());
                intent.putExtra("item_description",item.getDescription());
                intent.putExtra("graph_description", item.getGraphDescription());
                intent.putExtra("factor1_description", item.getFactor1Description());
                intent.putExtra("factor1_value", item.getFactor1Value());
                intent.putExtra("factor2_description", item.getFactor2Description());
                intent.putExtra("factor2_value", item.getFactor2Value());
                intent.putExtra("factor3_description", item.getFactor3Description());
                intent.putExtra("factor3_value", item.getFactor3Value());
                intent.putExtra("factor4_description", item.getFactor4Description());
                intent.putExtra("factor4_value", item.getFactor4Value());
                SubCategoryActivity.this.startActivity(intent);
            }
        });

    }

}
