package com.durar.findbeauty.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.durar.findbeauty.R;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private final List<String> values;
    private final LayoutInflater inflater;

    public CustomSpinnerAdapter(Context context, int resource, List<String> values) {
        super(context, resource, values);
        this.values = values;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.custom_spinner_item, parent, false);
        }

        TextView spinnerText = view.findViewById(R.id.spinnerText);
        String value = values.get(position);
        spinnerText.setText(value);


        spinnerText.setTextColor(getContext().getResources().getColor(android.R.color.white));
        spinnerText.setBackgroundColor(getContext().getResources().getColor(android.R.color.black));
        spinnerText.setCompoundDrawables(null,null,getContext().getResources().getDrawable(R.drawable.down_arrow_white),null);

        return view;
    }
}

