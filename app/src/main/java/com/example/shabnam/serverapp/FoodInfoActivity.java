package com.example.shabnam.serverapp;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.BadParcelableException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shabnam.serverapp.Common.Common;
import com.example.shabnam.serverapp.Model.AddFood;
import com.example.shabnam.serverapp.Model.EditFood;
import com.example.shabnam.serverapp.Service.ApiService;
import com.github.angads25.toggle.LabeledSwitch;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.rey.material.widget.Switch;

import org.angmarch.views.NiceSpinner;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodInfoActivity extends AppCompatActivity {

    Button btnOption,btnUpLoad,btnsave;
    LinearLayout mainLinearLayout;
    View view;
    String name = "";
    String price = "";
    String createMaterial = "";
    String bakingTime = "";
    String recepe = "";
    String menu = "";
    int id;
    int resID;
    int menuID;
    EditText edtOptionName;
    EditText edtOptionPrice;
    LabeledSwitch optionTypeSwitch;
    boolean flag = false;
    boolean emptyFlag = true;

    ArrayList<String> lstStatusSpinner = new ArrayList<>();
    ArrayList<Integer> lstMyIDs = new ArrayList<>();

    Retrofit retrofit;
    ApiService service;
    Call<Integer> callAddFood;
    Call<Integer> callEditFood;

    EditText edtfoodname,edtfoodcost,edtTimeFood,edtCreatMatrialFood,edtMakeFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://android-application-api.ir/")
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);


        edtfoodname=(EditText)findViewById(R.id.edtfoodname);
        edtfoodcost=(EditText)findViewById(R.id.edtfoodcost);
        edtTimeFood=(EditText)findViewById(R.id.edtTimeFood);
        edtCreatMatrialFood=(EditText)findViewById(R.id.edtCreatMatrialFood);
        edtMakeFood = (EditText)findViewById(R.id.edtMakeFood);
        btnUpLoad = (Button)findViewById(R.id.btnUpLoad);
        btnsave = (Button)findViewById(R.id.btnsave);
        btnOption=(Button)findViewById(R.id.btnOption);
        final NiceSpinner niceSpinner = (NiceSpinner) findViewById(R.id.spTypeFood);
        if(Common.currentResturants.getResType().equals("رستوران")){
            lstStatusSpinner.add("پیش غذا");
            lstStatusSpinner.add("غذای اصلی");
            lstStatusSpinner.add("دسر");
        }else if (Common.currentResturants.getResType().equals("فست فود")){
            lstStatusSpinner.add("پیتزا");
            lstStatusSpinner.add("برگر");
            lstStatusSpinner.add("ساندویچ");
            lstStatusSpinner.add("پیش غذا");
            lstStatusSpinner.add("نوشیدنی");
            lstStatusSpinner.add("سوخاری");
        }else if(Common.currentResturants.getResType().equals("کافی شاپ")){
            lstStatusSpinner.add("نوشیدنی های گرم");
            lstStatusSpinner.add("نوشیدنی های سرد");
            lstStatusSpinner.add("بستنی");
            lstStatusSpinner.add("کیک");
            lstStatusSpinner.add("پاستا");
            lstStatusSpinner.add("غذا");
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, lstStatusSpinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        niceSpinner.setAdapter(adapter);

        niceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(Common.currentResturants.getResType().equals("رستوران")){
                    menuID = position + 7;
                }else if (Common.currentResturants.getResType().equals("فست فود")){
                    menuID = position + 1;
                }else if(Common.currentResturants.getResType().equals("کافی شاپ")){
                    menuID = position + 10;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(Common.selectedFoodForEdit != null) {
            edtfoodname.setText(Common.selectedFoodForEdit.getName());
            edtfoodcost.setText(Common.selectedFoodForEdit.getCost());
            edtCreatMatrialFood.setText(Common.selectedFoodForEdit.getCreateMaterial());
            edtTimeFood.setText(Common.selectedFoodForEdit.getBakingTime());
            edtMakeFood.setText(Common.selectedFoodForEdit.getRecipe());
            id = Common.selectedFoodForEdit.getId();
        }

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edtfoodname.getText().toString();
                price = edtfoodcost.getText().toString();
                createMaterial = edtCreatMatrialFood.getText().toString();
                bakingTime = edtTimeFood.getText().toString();
                recepe = edtMakeFood.getText().toString();
                if(Common.selectedFoodForEdit != null){
                    EditFood editFood = new EditFood(
                            id,
                            name ,
                            menuID ,
                            price ,
                            "123" ,
                            recepe ,
                            createMaterial ,
                            bakingTime,
                            Common.currentUser.getEmail(),
                            Common.currentResturants.getID()
                    );
                    callEditFood = service.AdminEditFoodAPI(editFood);
                    callEditFood.enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            int updateResult = response.body();
                            if(updateResult == 1)
                                Toast.makeText(FoodInfoActivity.this, "ویرایش با موفقبت انجام شد", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(FoodInfoActivity.this, "عملیات با شکست مواجه کردید", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            Toast.makeText(FoodInfoActivity.this, "خطایی رخ دادئه . لطفا مجددا اقدام نمایید", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    AddFood addFood = new AddFood(
                            name ,
                            menuID ,
                            price ,
                            "123" ,
                            recepe ,
                            createMaterial ,
                            bakingTime,
                            Common.currentUser.getEmail(),
                            Common.currentResturants.getID(),
                            lstMyIDs
                    );
                    callAddFood = service.AdminAddNewFoodAPI(addFood);
                    callAddFood.enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            int updateResult = response.body();
                            if(updateResult == 2)
                                Toast.makeText(FoodInfoActivity.this, "غذا با موفقیت اضافه شد", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(FoodInfoActivity.this, "عملیات با شکست مواجه کردید", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            Toast.makeText(FoodInfoActivity.this, "خطایی رخ دادئه . لطفا مجددا اقدام نمایید", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        flag = false;
        emptyFlag = true;
        lstMyIDs.clear();
    }
}
