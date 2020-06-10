package com.example.waqas.dashboardapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemDescriptionActivity extends AppCompatActivity {

    TextView title;
    Toolbar toolbar;
    ImageButton backButton;
    TextView itemName;
    TextView itemCategory;
    ImageView itemImage;
    TextView itemDescription;
    PieChart pieChart;
    List<PieEntry> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        title = (TextView) findViewById(R.id.toolbar_heading);
        backButton = (ImageButton) findViewById(R.id.back_btn);
        itemName = (TextView) findViewById(R.id.item_desc_name);
        itemCategory = (TextView) findViewById(R.id.item_decs_cat);
        itemImage = (ImageView) findViewById(R.id.item_desc_img);
        itemDescription = (TextView) findViewById(R.id.item_description);
        pieChart = (PieChart) findViewById(R.id.pie_chart);
        values = new ArrayList<>();
        setSupportActionBar(toolbar);

        String titleValue = getIntent().getStringExtra("title");
        String logoLink = getIntent().getStringExtra("logo_image");
        String childName = getIntent().getStringExtra("subcategory");
        String nameText = getIntent().getStringExtra("item_name");
        String imageLink = getIntent().getStringExtra("item_image");
        String descriptionText = getIntent().getStringExtra("item_description");
        String graphName = getIntent().getStringExtra("graph_description");
        String factor1desc = getIntent().getStringExtra("factor1_description");
        String factor1val = getIntent().getStringExtra("factor1_value");
        String factor2desc = getIntent().getStringExtra("factor2_description");
        String factor2val = getIntent().getStringExtra("factor2_value");
        String factor3desc = getIntent().getStringExtra("factor3_description");
        String factor3val = getIntent().getStringExtra("factor3_value");
        String factor4desc = getIntent().getStringExtra("factor4_description");
        String factor4val = getIntent().getStringExtra("factor4_value");

        title.setText(nameText);
        Picasso.with(getApplicationContext()).load(imageLink).into(itemImage);
        itemName.setText(nameText);
        itemCategory.setText(titleValue + " " + childName);
        itemDescription.setText(descriptionText);

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(20f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText(graphName);
        Description desc = new Description();
        desc.setText(graphName);
        desc.setTextSize(20f);
        pieChart.setDescription(desc);

        values.add(new PieEntry(Float.parseFloat(factor1val),factor1desc));
        values.add(new PieEntry(Float.parseFloat(factor2val),factor2desc));
        values.add(new PieEntry(Float.parseFloat(factor3val),factor3desc));
        values.add(new PieEntry(Float.parseFloat(factor4val),factor4desc));

        PieDataSet pieDataSet = new PieDataSet(values,"Stats");
        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);

        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
