package com.example.shabnam.serverapp.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.shabnam.serverapp.Model.Food;
import com.example.shabnam.serverapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Comment;

import java.util.ArrayList;

/**
 * Created by Shabnam on 30/11/2018.
 */

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Food> FlList;
    String type;
    FoodListAdapter.customAdapterInterface customAdapterInterface;
    FoodListAdapter.customAdapterInterfaceForCancle customAdapterInterfaceForCancle;

    String foodImage = "";
    String imageBaseUrl = "http://android-application-api.ir/Content/UserContent/images/";

    String imageUrl =  "";

    public FoodListAdapter(
            Context context,
                           ArrayList<Food> FlList,
                           FoodListAdapter.customAdapterInterface customAdapterInterface,
                           FoodListAdapter.customAdapterInterfaceForCancle customAdapterInterfaceForCancle,
            String type
    ){
        this.customAdapterInterface=customAdapterInterface;
        this.customAdapterInterfaceForCancle=customAdapterInterfaceForCancle;
        this.FlList=FlList;
        this.context = context;
        this.type=type;
    }

    public interface customAdapterInterface {
        void onCustomListItemClick(int position);
    }

    public interface customAdapterInterfaceForCancle {
        void onCustomListItemClickForCancle(int position);
    }

    @Override
    public FoodListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_list_item,parent,false);
        return new FoodListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodListAdapter.MyViewHolder holder , final int position) {

        Food food = FlList.get(position);

        holder.foodName.setText(food.getName());
        holder.foodcost.setText(food.getCost());
        imageUrl = imageBaseUrl + food.getFoodImage();
        Picasso.with(context).load(imageUrl)
                .into(holder.food_image);

        if(food.getFoodCount() == -1){
            holder.txtFoodCount.setText("نامحدود");
        }else {
            holder.txtFoodCount.setText(String.valueOf(food.getFoodCount()));
        }


        holder.foodlistCardView.setTag(position);
        holder.btnCancel.setTag(position);

    }

    @Override
    public int getItemCount() {
        return FlList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView
                foodName , foodcost , txtFoodCount;

        private ImageView
                food_image;
        private ImageButton
                btnCancel;
        private CardView
                foodlistCardView;




        public MyViewHolder(final View itemView) {
            super(itemView);

            foodName = (TextView)itemView.findViewById(R.id.foodName);
            foodcost = (TextView)itemView.findViewById(R.id.foodcost);
            txtFoodCount = (TextView)itemView.findViewById(R.id.txtFoodCount);
            food_image = (ImageView) itemView.findViewById(R.id.food_image);
            btnCancel = (ImageButton) itemView.findViewById(R.id.btnCancel);
            foodlistCardView=(CardView)itemView.findViewById(R.id.foodlistCardView);

            foodlistCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=(int)v.getTag();
                    customAdapterInterface.onCustomListItemClick(pos);
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=(int)v.getTag();
                    customAdapterInterfaceForCancle.onCustomListItemClickForCancle(pos);
                }
            });
        }
    }
}
