package com.example.purchaselist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.purchaselist.R;
import com.example.purchaselist.models.MyList;
import com.example.purchaselist.utils.TestUtils;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<MyList> {

    private LayoutInflater inflater;
    private int layout;
    private List<MyList> listModels;

    public MyListAdapter(Context context, int resources, List<MyList> listModels) {
        super(context, resources, listModels);

        this.inflater = LayoutInflater.from(context);
        this.layout = resources;
        this.listModels = listModels;
    }

    public List<MyList> getList() {
        return listModels;
    }

    public void addList(MyList myList) {
        this.listModels.add(myList);
        notifyDataSetChanged();
    }

    public void deleteList(MyList myList) {
        this.listModels.remove(myList);
        notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = this.inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MyList myList = listModels.get(position);
        viewHolder.nameView.setText(myList.getName());
        viewHolder.dateView.setText(TestUtils.getFormattedDate(myList.getDate()));

        return convertView;
    }

    private class ViewHolder {

        final TextView nameView;
        final TextView dateView;

        ViewHolder(View view) {
            nameView = view.findViewById(R.id.lists_name);
            dateView = view.findViewById(R.id.lists_date);
        }
    }
}
