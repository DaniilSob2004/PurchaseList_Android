package com.example.purchaselist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.purchaselist.R;
import com.example.purchaselist.models.Product;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private LayoutInflater inflater;
    private int layout;
    private List<Product> productsModels;

    public ProductAdapter(Context context, int resources, List<Product> productsModels) {
        super(context, resources, productsModels);

        this.inflater = LayoutInflater.from(context);
        this.layout = resources;
        this.productsModels = productsModels;
    }

    public List<Product> getList() {
        return productsModels;
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

        Product product = productsModels.get(position);
        viewHolder.nameView.setText(product.getName());
        viewHolder.countView.setText(String.valueOf(product.getCount()));
        viewHolder.checkedCheckbox.setChecked(product.getChecked() == 1);

        return convertView;
    }

    private class ViewHolder {

        final TextView nameView;
        final TextView countView;
        final CheckBox checkedCheckbox;

        ViewHolder(View view) {
            nameView = view.findViewById(R.id.name_text_view);
            countView = view.findViewById(R.id.count_text_view);
            checkedCheckbox = view.findViewById(R.id.checked_checkbox);
        }
    }
}
