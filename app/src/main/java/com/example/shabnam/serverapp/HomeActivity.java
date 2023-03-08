package com.example.shabnam.serverapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shabnam.serverapp.Adapter.FoodListAdapter;
import com.example.shabnam.serverapp.Adapter.FoodListSpinnerAdapter;
import com.example.shabnam.serverapp.Common.Common;
import com.example.shabnam.serverapp.Model.Food;
import com.example.shabnam.serverapp.Model.Resturants;
import com.example.shabnam.serverapp.Service.ApiService;
import com.makeramen.roundedimageview.RoundedImageView;
import com.rey.material.widget.Switch;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import snow.skittles.BaseSkittle;
import snow.skittles.Skittle;
import snow.skittles.SkittleBuilder;
import snow.skittles.SkittleLayout;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FoodListAdapter.customAdapterInterfaceForCancle ,
        FoodListAdapter.customAdapterInterface,
        SkittleBuilder.OnSkittleClickListener{

    FoodListAdapter foodListAdapter;
    ArrayList<Food> foodList;

    RecyclerView recyclerView;
    String type = "";
    String userEmail = "";
    String imageBaseUrlDrawer = "http://android-application-api.ir/Content/UserContent/images/";
    String imageUrlDrawer =  "";

    Retrofit retrofit;
    ApiService service;
    Call<List<Food>> callFood;

    Call<Resturants> callResturant;

    TextView txtFullName , txtResStatus;
    RoundedImageView imageDrawer;
    Switch schResStatus;

    Call<Integer> callDeletePacking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        type = Common.currentUser.getAccess();
        userEmail = Common.currentUser.getEmail();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            // edited here
            this.getWindow().setStatusBarColor(Color.WHITE);
        }

        retrofit = new Retrofit.Builder()
                .baseUrl("http://android-application-api.ir/")
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);

        SetData();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("  ");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //set name for user
        View headerView = navigationView.getHeaderView(0);
        txtFullName = (TextView)headerView.findViewById(R.id.txtFullName);
        imageDrawer=(RoundedImageView)headerView.findViewById(R.id.imageDrawer);
        txtResStatus=(TextView) headerView.findViewById(R.id.txtResStatus);
        schResStatus=(Switch) headerView.findViewById(R.id.schResStatus);
        String s = Common.currentUser.getName() + " " + Common.currentUser.getFamily();
        txtFullName.setText(s);
        imageUrlDrawer = imageBaseUrlDrawer + Common.currentUser.getImage();
        Picasso.with(getApplicationContext()).load(imageUrlDrawer)
                .into(imageDrawer);

        callResturant = service.AdminResInformationAPI(userEmail);
        callResturant.enqueue(new Callback<Resturants>() {
            @Override
            public void onResponse(Call<Resturants> call, Response<Resturants> response) {
                Common.currentResturants = response.body();
                if(Common.currentResturants.isGetOrder()){
                    txtResStatus.setText("فعال");
                    schResStatus.setChecked(true);
                }else {
                    txtResStatus.setText("غیرفعال");
                    schResStatus.setChecked(false);
                }
            }

            @Override
            public void onFailure(Call<Resturants> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "خطایی رخ دادئه . لطفا مجددا اقدام نمایید", Toast.LENGTH_SHORT).show();
            }
        });



        //fab Button========================================================================

        final SkittleBuilder skittleBuilder =
                SkittleBuilder.newInstance((SkittleLayout) findViewById(R.id.skittleLayout), false);

        skittleBuilder.addSkittle(Skittle.newInstance(ContextCompat.getColor(this, R.color.whitebtn),
                ContextCompat.getDrawable(this, R.drawable.ic_dehaze_black_24dp)));

        skittleBuilder.addSkittle(Skittle.newInstance(ContextCompat.getColor(this, R.color.whitebtn),
                ContextCompat.getDrawable(this, R.drawable.ic_restaurant_black_24dp)));





        skittleBuilder.setSkittleClickListener(this);


        //end of fab button====================================================================


    }

    private void SetData() {
        foodList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_FoodList);
        foodListAdapter = new FoodListAdapter(this , foodList , this , this , type);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(foodListAdapter);

        callFood = service.GetResAdminHomeListAPI(type , userEmail);
        callFood.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if(response.body() != null){
                    Common.foods = new ArrayList<>();
                    for (Food food : response.body()){
                        foodList.add(food);
                        Common.foods.add(food);
                        foodListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "خطایی رخ دادئه . لطفا مجددا اقدام نمایید", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent profile = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(profile);
        } else if (id == R.id.nav_orders) {
            Intent orders = new Intent(HomeActivity.this, OrderActivity.class);
            startActivity(orders);
        }   else if (id == R.id.nav_Comments) {
            Intent Reserv_food = new Intent(HomeActivity.this, CommentActivity.class);
            startActivity(Reserv_food);
        } else if (id == R.id.nav_log_out) {

            Paper.book().destroy();
            Intent singin = new Intent(HomeActivity.this,SignInActivity.class);
            singin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(singin);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCustomListItemClick(int position) {
        Food f = foodList.get(position);
        Common.selectedFoodForEdit = f;
        Intent intent = new Intent(HomeActivity.this , FoodInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCustomListItemClickForCancle(int position) {
        Food f = foodList.get(position);
        callDeletePacking = service.AdminDeleteFoodAPI(f.getId());
        callDeletePacking.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.body() == 1){
                    Toast.makeText(HomeActivity.this, "حذف با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                    SetData();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "خطایی رخ دادئه . لطفا مجددا اقدام نمایید", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSkittleClick(BaseSkittle skittle, int position) {
        if(position == 0){
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }else if(position == 1){
            Intent FoodInfoIntent = new Intent(HomeActivity.this , FoodInfoActivity.class);
            startActivity(FoodInfoIntent);
        }
    }

    @Override
    public void onMainSkittleClick() {

    }
}
