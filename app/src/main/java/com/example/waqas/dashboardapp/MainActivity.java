package com.example.waqas.dashboardapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.waqas.dashboardapp.Adapters.CustomExpandableListAdapter;
import com.example.waqas.dashboardapp.Adapters.HomeGridAdapter;
import com.example.waqas.dashboardapp.Helper.FragmentNavigationManager;
import com.example.waqas.dashboardapp.Interface.NavigationManager;
import com.example.waqas.dashboardapp.Models.Item;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private List<Item> allItems;
    private GridView homeGridView;

    private ExpandableListView expandableListView;
    private CustomExpandableListAdapter adapter;
    private HomeGridAdapter homeGridAdapter;
    private List<String> lstTitle;
    private List<String> logoImages;
    private NavigationManager navigationManager;

    ImageView coverImage;
    ImageView avatarImage;
    private String appTitle;
    private String coverLink;
    private String avatarLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coverImage = (ImageView) findViewById(R.id.cover_image);
        avatarImage = (ImageView) findViewById(R.id.avatar);


        homeGridView = (GridView) findViewById(R.id.home_grid);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = (ExpandableListView) findViewById(R.id.navList);
        navigationManager = FragmentNavigationManager.getmInstance(this);


        allItems = new ArrayList<Item>();
        initItems();

        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header,null,false);
        expandableListView.addHeaderView(listHeaderView);

        genData();

        addDrawerItem();
        setupDrawer();


        if(savedInstanceState == null){
            selectFirstItemAsDefault();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(appTitle);



        homeGridAdapter = new HomeGridAdapter(this,R.layout.home_grid_layout,allItems);
        homeGridView.setAdapter(homeGridAdapter);

        homeGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Item item = allItems.get(position);

                Intent intent = new Intent(MainActivity.this,ItemDescriptionActivity.class);
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
                MainActivity.this.startActivity(intent);
            }
        });

//        Picasso.with(getApplicationContext()).load(coverLink).into(coverImage);
//        Picasso.with(getApplicationContext()).load(avatarLink).into(avatarImage);

    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectFirstItemAsDefault() {

        if(navigationManager != null){
            String firstItem = lstTitle.get(0);
            getSupportActionBar().setTitle(firstItem);
        }

    }


    private void genData() {

        List<String> title = new ArrayList<String>();
        List<String> logos = new ArrayList<String>();

        Log.d("Data" , "Text" + allItems.toString());
        for(int i = 0 ; i < allItems.size() ; i++){
            String category = allItems.get(i).getMainCategory();
            if(!title.contains(category)){
                title.add(category);
                logos.add(allItems.get(i).getLogoImage());
            }
        }

        lstTitle = title;
        logoImages = logos;
    }

    private void setupDrawer() {


        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(appTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(appTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

    }

    private void addDrawerItem() {
        adapter = new CustomExpandableListAdapter(this,lstTitle,logoImages);

        List<String> childItem;

        for (int i = 0 ; i < lstTitle.size() ; i++ ){
            childItem = new ArrayList<String>();
            for(int j = 0 ; j < allItems.size() ; j++ ){
                if(lstTitle.get(i).equals(allItems.get(j).getMainCategory())){
                    if(!childItem.contains(allItems.get(j).getSubCategory())){
                        childItem.add(allItems.get(j).getSubCategory());
                    }
                }
            }
            adapter.addChildren(i,childItem);
        }
        expandableListView.setAdapter(adapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                expandableListView.expandGroup(groupPosition);
                parent.smoothScrollToPosition(groupPosition);

                // Need default behaviour here otherwise group does not get expanded/collapsed
                // on click
                if (parent.isGroupExpanded(groupPosition)) {
                    parent.collapseGroup(groupPosition);
                } else {
                    parent.expandGroup(groupPosition);
                }

                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastPosition = -1;
            @Override
            public void onGroupExpand(int groupPosition) {

                if (groupPosition != lastPosition){
                    expandableListView.collapseGroup(lastPosition);
                }
                lastPosition = groupPosition;
                getSupportActionBar().setTitle(lstTitle.get(groupPosition).toString());
            }
        });



        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                getSupportActionBar().setTitle(appTitle);
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String groupTitle = lstTitle.get(groupPosition);
                String childName = (String) adapter.getChild(groupPosition,childPosition);


                expandableListView.collapseGroup(groupPosition);
                Intent intent = new Intent(MainActivity.this,SubCategoryActivity.class);
                intent.putExtra("title", groupTitle);
                intent.putExtra("subcategory",childName);
                intent.putParcelableArrayListExtra("allitems", (ArrayList<? extends Parcelable>) allItems);
                MainActivity.this.startActivity(intent);

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void initItems() {

        get_json();
        get_json_Title();

    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("fyp.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void get_json(){

        try {
            JSONArray jArray = new JSONArray(readJSONFromAsset());


            for (int i = 0 ; i < jArray.length() ; ++i ){
                Item newItem = new Item(jArray.getJSONObject(i).getString("mainCategory"),
                        jArray.getJSONObject(i).getString("logoImage"),
                        jArray.getJSONObject(i).getString("subCategory"),
                        jArray.getJSONObject(i).getString("name"),
                        jArray.getJSONObject(i).getString("description"),
                        jArray.getJSONObject(i).getString("image"),
                        jArray.getJSONObject(i).getString("graphDescription"),
                        jArray.getJSONObject(i).getString("factor1Description"),
                        jArray.getJSONObject(i).getString("factor1Value"),
                        jArray.getJSONObject(i).getString("factor2Description"),
                        jArray.getJSONObject(i).getString("factor2Value"),
                        jArray.getJSONObject(i).getString("factor3Description"),
                        jArray.getJSONObject(i).getString("factor3Value"),
                        jArray.getJSONObject(i).getString("factor4Description"),
                        jArray.getJSONObject(i).getString("factor4Value"));
                Log.d("Here", "Text" + newItem.toString());
                allItems.add(newItem);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }


    public String readJSONFromAssetTitle() {
        String json = null;
        try {
            InputStream is = getAssets().open("fyp_title.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void get_json_Title(){

        try {
            JSONArray jArray = new JSONArray(readJSONFromAssetTitle());


            for (int i = 0 ; i < jArray.length() ; ++i ){
                appTitle = jArray.getJSONObject(i).getString("title");
                avatarLink = jArray.getJSONObject(i).getString("startingImage");
                coverLink = jArray.getJSONObject(i).getString("coverImage");
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(mDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
