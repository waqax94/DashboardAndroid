package com.example.waqas.dashboardapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.waqas.dashboardapp.Models.Item;
import com.example.waqas.dashboardapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class HomeGridAdapter extends ArrayAdapter {

    Context context;
    List<Item> itemList = new ArrayList<>();

    public HomeGridAdapter(Context context, int resource, List<Item> itemList) {
        super(context, resource);
        this.context = context;
        this.itemList = itemList;
    }

    static class Handler{
        TextView itemCat;
        TextView itemName;
        ImageView itemImage;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        grid = convertView;
        final HomeGridAdapter.Handler handler;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.home_grid_layout,parent,false);
            handler = new HomeGridAdapter.Handler();
            handler.itemName = (TextView) grid.findViewById(R.id.grid_item_name);
            handler.itemCat = (TextView) grid.findViewById(R.id.grid_item_cat);
            handler.itemImage = (ImageView) grid.findViewById(R.id.home_grid_image);

            grid.setTag(handler);
        }
        else {
            handler = (HomeGridAdapter.Handler)grid.getTag();
        }

        final Item item;
        item = (Item) this.getItem(position);

        Picasso.with(this.getContext()).load(item.getImage()).into(handler.itemImage);
        handler.itemName.setText(item.getName());
        handler.itemCat.setText(item.getMainCategory());

        return grid;
    }
}
