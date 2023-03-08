package com.example.shabnam.serverapp;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alirezaafkar.sundatepicker.DatePicker;
import com.alirezaafkar.sundatepicker.components.DateItem;
import com.alirezaafkar.sundatepicker.interfaces.DateSetListener;
import com.example.shabnam.serverapp.Common.Common;
import com.example.shabnam.serverapp.Model.CommentChild;
import com.example.shabnam.serverapp.Model.CommentParent;
import com.example.shabnam.serverapp.Model.OrderChild;
import com.example.shabnam.serverapp.Model.OrderParent;
import com.example.shabnam.serverapp.Service.ApiService;
import com.squareup.picasso.Picasso;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderActivity extends AppCompatActivity implements
        View.OnClickListener, DateSetListener{
    String imageBaseUrl = "http://android-application-api.ir/Content/images/";

    String imageUrl = "";
    Button btnTimeOrder;
    private OrderActivity.Date mStartDate;
    Retrofit retrofit;
    ApiService service;

    ArrayList<String> lstStatusSpinner = new ArrayList<>();
    ExpandableLayout layout , filterLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        btnTimeOrder = (Button) findViewById(R.id.btnTimeOrder);

        mStartDate = new Date();
        btnTimeOrder.setOnClickListener(this);
        setLocale("fa");

        retrofit = new Retrofit.Builder()
                .baseUrl("http://android-application-api.ir/")
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        lstStatusSpinner.add("سفارش دریافت شد");
        lstStatusSpinner.add("سفارش تایید شد");
        lstStatusSpinner.add("سفارش درحال آماده سازی");
        lstStatusSpinner.add("سفارش تحویل پیک داده شد");
        lstStatusSpinner.add("سفارش درحال حمل به مقصد");
        lstStatusSpinner.add("سفارش تحویل داده شد");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_spinner_item, lstStatusSpinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        service = retrofit.create(ApiService.class);

        layout = (ExpandableLayout) findViewById(R.id.Order_ExpandableLayout);
        filterLayout = (ExpandableLayout) findViewById(R.id.Order_ExpandableLayoutFilter);

        layout.setRenderer(new ExpandableLayout.Renderer<OrderParent, OrderChild>() {
            @Override
            public void renderParent(View view, OrderParent phoneCategory, boolean isExpanded, int parentPosition) {
                ((TextView) view.findViewById(R.id.txt_factorId)).setText(String.valueOf(phoneCategory.getID()));
                ((TextView) view.findViewById(R.id.txtOrderDate)).setText(phoneCategory.getOrderDate());
                ((TextView) view.findViewById(R.id.txtOrderTime)).setText(phoneCategory.getOrderTime());
                ((TextView) view.findViewById(R.id.txtPrice)).setText(phoneCategory.getOrderPrice());

                NiceSpinner statusSpinner = (NiceSpinner) view.findViewById(R.id.txtOrderStatus);
                statusSpinner.setAdapter(adapter);



                view
                        .findViewById(R.id.arrow)
                        .setBackgroundResource(isExpanded ? R.drawable.ic_keyboard_arrow_up_white_24dp : R.drawable.ic_keyboard_arrow_down_white_24dp);

            }

            @Override
            public void renderChild(final View view, OrderChild phone, int parentPosition, int childPosition) {
                ((TextView) view.findViewById(R.id.txtName)).setText(phone.getOrderName());

                ((TextView) view.findViewById(R.id.txtCount)).setText(phone.getOrderCount());
                imageUrl = imageBaseUrl + phone.getOrderImage();
                Picasso
                        .with(OrderActivity.this)
                        .load(imageUrl)
                        .into(((ImageView) view.findViewById(R.id.img_child)));
            }

        });
        filterLayout.setRenderer(new ExpandableLayout.Renderer<OrderParent, OrderChild>() {
            @Override
            public void renderParent(View view, OrderParent phoneCategory, boolean isExpanded, int parentPosition) {
                ((TextView) view.findViewById(R.id.txt_factorId)).setText(String.valueOf(phoneCategory.getID()));
                ((TextView) view.findViewById(R.id.txtOrderDate)).setText(phoneCategory.getOrderDate());
                ((TextView) view.findViewById(R.id.txtOrderTime)).setText(phoneCategory.getOrderTime());
                ((TextView) view.findViewById(R.id.txtPrice)).setText(phoneCategory.getOrderPrice());

                NiceSpinner statusSpinner = (NiceSpinner) view.findViewById(R.id.txtOrderStatus);
                statusSpinner.setAdapter(adapter);



                view
                        .findViewById(R.id.arrow)
                        .setBackgroundResource(isExpanded ? R.drawable.ic_keyboard_arrow_up_white_24dp : R.drawable.ic_keyboard_arrow_down_white_24dp);

            }

            @Override
            public void renderChild(final View view, OrderChild phone, int parentPosition, int childPosition) {
                ((TextView) view.findViewById(R.id.txtName)).setText(phone.getOrderName());

                ((TextView) view.findViewById(R.id.txtCount)).setText(phone.getOrderCount());
                imageUrl = imageBaseUrl + phone.getOrderImage();
                Picasso
                        .with(OrderActivity.this)
                        .load(imageUrl)
                        .into(((ImageView) view.findViewById(R.id.img_child)));
            }

        });


        Call<List<OrderParent>> call = service.AdminOrderListAPI(Common.currentUser.getEmail() );
        call.enqueue(new Callback<List<OrderParent>>() {
            @Override
            public void onResponse(Call<List<OrderParent>> call, Response<List<OrderParent>> response) {
                filterLayout.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
                for (OrderParent comment : response.body()) {
                    layout.addSection(getSection(
                            comment.getID() ,
                            comment.getOrderDate() ,
                            comment.getOrderPrice() ,
                            comment.getOrderTime() ,
                            comment.getStatus()
                    ));
                }
            }

            @Override
            public void onFailure(Call<List<OrderParent>> call, Throwable t) {

                Log.i("Hello",""+t);
                Toast.makeText(OrderActivity.this, "Throwable"+t, Toast.LENGTH_LONG).show();

            }
        });

    }

    private Section<OrderParent,OrderChild> getSection(int id, String OrderDate,String OrderPrice,String OrderTime,String Status) {

        final Section<OrderParent,OrderChild> section = new Section<>();

        OrderParent phoneCategory = new OrderParent(
                id,
                OrderDate,
                OrderPrice,
                OrderTime,
                Status
        );

        //end parent



        //start child

        final List<OrderChild> listPhone = new ArrayList<>();

        Call<List<OrderChild>> callItems = service.AdminOrderItemListAPI(id);
        callItems.enqueue(new Callback<List<OrderChild>>() {
            @Override
            public void onResponse(Call<List<OrderChild>> call, Response<List<OrderChild>> response) {

                for (OrderChild comment : response.body()) {
                    listPhone.add(new OrderChild(
                            comment.getOrderImage(),
                            comment.getOrderName(),
                            comment.getOrderCount()
                    ));
                }
                section.children.addAll(listPhone);
            }

            @Override
            public void onFailure(Call<List<OrderChild>> call, Throwable t) {

                Log.i("Hello",""+t);
                Toast.makeText(OrderActivity.this, "Throwable"+t, Toast.LENGTH_LONG).show();

            }
        });

        section.parent = phoneCategory;


        return section;
    }

    private Section<OrderParent,OrderChild> getFilterSection(int id, String OrderDate,String OrderPrice,String OrderTime,String Status) {

        final Section<OrderParent,OrderChild> filterSection = new Section<>();

        OrderParent orderParent = new OrderParent(
                id,
                OrderDate,
                OrderPrice,
                OrderTime,
                Status
        );

        //end parent



        //start child

        final List<OrderChild> filterChild = new ArrayList<>();

        Call<List<OrderChild>> callItems = service.AdminFilterOrderItemListAPI(id);
        callItems.enqueue(new Callback<List<OrderChild>>() {
            @Override
            public void onResponse(Call<List<OrderChild>> call, Response<List<OrderChild>> response) {

                for (OrderChild comment : response.body()) {
                    filterChild.add(new OrderChild(
                            comment.getOrderImage(),
                            comment.getOrderName(),
                            comment.getOrderCount()
                    ));
                }
                filterSection.children.addAll(filterChild);
            }

            @Override
            public void onFailure(Call<List<OrderChild>> call, Throwable t) {

                Log.i("Hello",""+t);
                Toast.makeText(OrderActivity.this, "Throwable"+t, Toast.LENGTH_LONG).show();

            }
        });

        filterSection.parent = orderParent;


        return filterSection;
    }
    @Override
    public void onClick(View v) {
        int id = v.getId() == R.id.btnTimeOrder ? 1 : 2;

        DatePicker.Builder builder = new DatePicker
                .Builder()
                .id(id)
                .maxDate(1400, 12, 31);


        if (v.getId() == R.id.btnTimeOrder)
            builder.date(mStartDate.getDay(), mStartDate.getMonth(), mStartDate.getYear());


        builder.build(OrderActivity.this)
                .show(getSupportFragmentManager(), "");
    }
    @Override
    public void onDateSet(int id, @Nullable Calendar calendar, int day, int month, int year) {
        if (id == 1) {
            mStartDate.setDate(day, month, year);
            btnTimeOrder.setText(mStartDate.getDate());

            String date = mStartDate.getDate();

            Call<List<OrderParent>> callFilter = service.AdminFilterOrderListAPI(Common.currentUser.getEmail() , date);
            callFilter.enqueue(new Callback<List<OrderParent>>() {
                @Override
                public void onResponse(Call<List<OrderParent>> call, Response<List<OrderParent>> response) {

                    layout.setVisibility(View.GONE);
                    filterLayout.setVisibility(View.VISIBLE);
                    for (OrderParent comment : response.body()) {
                        filterLayout.addSection(getFilterSection(
                                comment.getID() ,
                                comment.getOrderDate() ,
                                comment.getOrderPrice() ,
                                comment.getOrderTime() ,
                                comment.getStatus()
                        ));
                    }
                }

                @Override
                public void onFailure(Call<List<OrderParent>> call, Throwable t) {

                    Log.i("Hello",""+t);
                    Toast.makeText(OrderActivity.this, "Throwable"+t, Toast.LENGTH_LONG).show();

                }
            });
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLocale("fa");
    }

    public void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    static class Date extends DateItem {
        String getDate() {
            Calendar calendar = getCalendar();
            return String.format(Locale.US,
                    "%d/%d/%d",
                    getYear() , getMonth(), getDay());
        }
    }
}
