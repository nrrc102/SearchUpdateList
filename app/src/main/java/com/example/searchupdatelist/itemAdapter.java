package com.example.searchupdatelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class itemAdapter extends BaseAdapter {
    Context context;
    ArrayList<MyItem> list;
    LayoutInflater inflater;

    public itemAdapter(Context context, ArrayList<MyItem> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemHolder holder = null;
        if(convertView == null){
            convertView=inflater.inflate(R.layout.itemlayout, null);
            holder = new ItemHolder();
            holder.image = convertView.findViewById(R.id.imageView);
            holder.name = convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }else holder = (ItemHolder) convertView.getTag();
        //populate the item
        holder.image.setImageURI(list.get(position).getImageUri());
        holder.name.setText(list.get(position).getName());

        return convertView;
    }
    //
    static class ItemHolder{
        ImageView image;
        TextView name;
    }
}
