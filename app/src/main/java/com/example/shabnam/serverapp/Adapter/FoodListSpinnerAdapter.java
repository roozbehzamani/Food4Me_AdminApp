package com.example.shabnam.serverapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.shabnam.serverapp.Common.Common;
import com.example.shabnam.serverapp.Model.SpinnerFood;
import com.example.shabnam.serverapp.R;

import java.util.ArrayList;

/**
 * Created by Shabnam on 03/12/2018.
 */

public class FoodListSpinnerAdapter extends ArrayAdapter<SpinnerFood> {
    public FoodListSpinnerAdapter(Context context , ArrayList<SpinnerFood> itemModels){
        super(context , 0 , itemModels);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position , convertView , parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position , convertView , parent);
    }

    private View initView(int position , View convertView , ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_food_list_layout , parent , false
            );
        }


        final SpinnerFood itemModel = getItem(position);

        CheckBox txt = (CheckBox) convertView.findViewById(R.id.ckb);

        txt.setText(itemModel.getFoodName());

        txt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Common.foodIDs.add(itemModel.getID());
                else
                    Common.foodIDs.remove(FindListItemIndex(itemModel.getID()));
            }
        });

        return convertView;
    }
    public int FindListItemIndex(int itemID){
        int index = -1;
        if(Common.foodIDs.size() !=0){
            for (int i = 0; i < Common.foodIDs.size(); i++){
                int select = Common.foodIDs.get(i);
                if(select == itemID){
                    index = i;
                }
            }
        }
        return index;
    }
}
