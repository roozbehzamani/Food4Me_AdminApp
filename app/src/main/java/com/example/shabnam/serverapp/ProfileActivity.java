package com.example.shabnam.serverapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shabnam.serverapp.Common.Common;
import com.example.shabnam.serverapp.Service.ApiService;
import com.makeramen.roundedimageview.RoundedImageView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    RoundedImageView imgProfileImage;
    Retrofit retrofit;
    ApiService service;
    Call<Integer> callEditUser;
    Call<Integer> callChangePassword;
    String imageBaseUrl = "http://android-application-api.ir/Content/images/";

    String imageUrl =  "";
    Button changerPassword;
    Button btntype_res;
    TextView  txtPhone;
    EditText Edt_name;
    EditText Edt_address;
    TextView  edtEmail;
    TextView  txtResName;
    Button  btsSaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://android-application-api.ir/")
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);


        changerPassword = (Button) findViewById(R.id.nav_change_password);
        btntype_res = (Button) findViewById(R.id.btntype_res);
        imgProfileImage = (RoundedImageView) findViewById(R.id.imgProfileImage);
        txtPhone = (TextView) findViewById(R.id.txtPhone);
        Edt_name = (EditText) findViewById(R.id.Edt_name);
        Edt_address = (EditText) findViewById(R.id.Edt_address);
        edtEmail = (TextView) findViewById(R.id.edtEmail);
        txtResName = (TextView) findViewById(R.id.txtResName);
        btsSaveChanges = (Button) findViewById(R.id.btsSaveChanges);

        init();




        btsSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Edt_name.getText().toString();
                String address = Edt_address.getText().toString();
                callEditUser = service.AdminEditUser(name , address , Common.currentUser.getEmail());
                callEditUser.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        int result = response.body();
                        if(result == 1){
                            Common.currentUser.setHome_address(address);
                            Common.currentUser.setName(name);
                            Toast.makeText(ProfileActivity.this, "ویرایش با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                            init();
                        }else {
                            Toast.makeText(ProfileActivity.this, "ویرایش با مشکل مواجه شد", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(ProfileActivity.this, "خطایی رخ دادئه . لطفا مجددا اقدام نمایید", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        btntype_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        changerPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangePasswordDialog();
            }
        });

    }

    private void init() {
        txtPhone.setText(Common.currentUser.getMob_phone());
        edtEmail.setText(Common.currentUser.getEmail());
        if(Common.currentUser.getName() != null && Common.currentUser.getHome_address() != null){
            Edt_name.setText(Common.currentUser.getName());
            Edt_address.setText(Common.currentUser.getHome_address());
        }


        String type = Common.currentUser.getAccess();
        if(type.equals("Restuarant")){
            btntype_res.setText(Common.currentResturants.getResType());
            txtResName.setText(Common.currentResturants.getResName());
            imageUrl = imageBaseUrl + Common.currentResturants.getResImage();
            Picasso
                    .with(getApplicationContext())
                    .load(imageUrl)
                    .into(imgProfileImage);
        }
    }

    private void showChangePasswordDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileActivity.this);
        alertDialog.setTitle("تغییر رمز");
        alertDialog.setMessage("لطفا تمام اطلاعات را پر کنید");

        LayoutInflater inflater = this.getLayoutInflater();
        View layout_pwd = inflater.inflate(R.layout.change_password_layout , null);

        final MaterialEditText edtPassword = (MaterialEditText) layout_pwd.findViewById(R.id.edtPassword);
        final MaterialEditText edtNewPassword = (MaterialEditText) layout_pwd.findViewById(R.id.edtNewPassword);
        final MaterialEditText edtRepeatPassword = (MaterialEditText) layout_pwd.findViewById(R.id.edtRepeatPassword);

        alertDialog.setView(layout_pwd);

        //Button
        alertDialog.setPositiveButton("ذخیره", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String oldPass = edtPassword.getText().toString();
                String newPass = edtNewPassword.getText().toString();
                String repNewPass = edtRepeatPassword.getText().toString();
                if(oldPass.equals(Common.currentUser.getPassword())){
                    if(newPass.equals(repNewPass)){
                        callChangePassword = service.AdminNewPasswordUser(newPass , Common.currentUser.getEmail());
                        callChangePassword.enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                int result = response.body();
                                if(result == 1){
                                    Common.currentUser.setPassword(newPass);
                                    Toast.makeText(ProfileActivity.this, "رمز با موفقیت تغییر یافت", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(ProfileActivity.this, "مشکلی پیش آمده . لطفا مجددا تلاش نمایید", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {
                                Toast.makeText(ProfileActivity.this, "خطایی رخ دادئه . لطفا مجددا اقدام نمایید", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }else {
                        Toast.makeText(ProfileActivity.this, "پسورد ها همخوانی ندارند", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ProfileActivity.this, "پسورد فعلی اشتباه وارد شده است", Toast.LENGTH_SHORT).show();
                }

            }
        });
        alertDialog.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
