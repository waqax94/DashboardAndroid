package com.example.waqas.dashboardapp.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class ItemListAdapter extends ArrayAdapter {

    List<Item> itemList = new ArrayList<>();
    Context context;

    public ItemListAdapter(Context context, int resource, List<Item> itemList) {
        super(context, resource);
        this.context = context;
        this.itemList = itemList;
    }

    static class Handler{
        TextView itemName;
        ImageView itemImage;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View list;
        list = convertView;
        final ItemListAdapter.Handler handler;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            list = inflater.inflate(R.layout.list_of_item,parent,false);
            handler = new ItemListAdapter.Handler();
            handler.itemName = (TextView) list.findViewById(R.id.item_name);
            handler.itemImage = (ImageView) list.findViewById(R.id.item_image);

            list.setTag(handler);
        }
        else {
            handler = (ItemListAdapter.Handler)list.getTag();
        }

        final Item item;
        item = (Item) this.getItem(position);

        Picasso.with(this.getContext()).load(item.getImage()).into(handler.itemImage);
        handler.itemName.setText(item.getName());

        return list;
    }
}
