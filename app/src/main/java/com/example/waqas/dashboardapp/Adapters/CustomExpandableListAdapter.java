package com.example.waqas.dashboardapp.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.waqas.dashboardapp.Models.Item;
import com.example.waqas.dashboardapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listTitle;
    private List<String> logoImages;
    SparseArray<List<String>> lstChild = new SparseArray<List<String>>();;

    public CustomExpandableListAdapter(Context context, List<String> listTitle, List<String> logoImages) {
        this.context = context;
        this.listTitle = listTitle;
        this.logoImages = logoImages;
    }

    public void addChildren(int groupPosition, List<String> children){
        this.lstChild.put(groupPosition,children);
    }
    @Override
    public int getGroupCount() {
        return listTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (lstChild.get(groupPosition) == null) {
            return 0;
        }
        else
            return lstChild.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return lstChild.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String title = (String)getGroup(groupPosition);
        String logoImage = logoImages.get(groupPosition);
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_group_title,null);
        }
        TextView txtTitle = (TextView)convertView.findViewById(R.id.expandableGroupListItem);
        ImageView logoImageView = (ImageView)convertView.findViewById(R.id.logo_images);
        txtTitle.setTypeface(null,Typeface.BOLD);
        txtTitle.setText(title);
        Picasso.with(context).load(logoImage).into(logoImageView);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String title = (String)getChild(groupPosition,childPosition);
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
        }
        TextView txtChild = (TextView)convertView.findViewById(R.id.expandableListItem);
        txtChild.setText(title);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
