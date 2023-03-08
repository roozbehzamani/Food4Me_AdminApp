package com.example.shabnam.serverapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shabnam.serverapp.Common.Common;
import com.example.shabnam.serverapp.Model.EditFoodAlbum;
import com.example.shabnam.serverapp.Model.User;
import com.example.shabnam.serverapp.Service.ApiService;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    EditText edtPhone , edtPassword;
    Button btnSignIn;
    CheckBox ckbRemember;
    TextView txt_forgetPassword;

    Retrofit retrofit;
    ApiService service;
    Call<User> call;
    Call<Integer> callForgetPassword;

    String userPhone ;
    String userPassword ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://android-application-api.ir/")
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);

        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        txt_forgetPassword = (TextView) findViewById(R.id.forget_password);
        ckbRemember = (CheckBox) findViewById(R.id.ckbRemember);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        Paper.init(this);

        String Phone = Paper.book().read(Common.USER_KEY);
        String pwd = Paper.book().read(Common.PWD_KEY);




        if (Phone != null && pwd != null){
            call = service.AdminSignInAPI(
                    Phone,
                    pwd
            );
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    Common.currentUser = response.body();
                    if(response.body() != null){

                        Intent signInIntent = new Intent(SignInActivity.this , HomeActivity.class);
                        startActivity(signInIntent);
                    }else {
                        Toast.makeText(SignInActivity.this, "اطلاعات ورود صحیح نمیباشد", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    //Toast.makeText(SignInActivity.this, "خطایی رخ دادئه . لطفا مجددا اقدام نمایید", Toast.LENGTH_SHORT).show();

                }
            });
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userPhone = edtPhone.getText().toString();
                userPassword = edtPassword.getText().toString();

                call = service.AdminSignInAPI(
                        userPhone,
                        userPassword
                );
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        
                        Common.currentUser = response.body();
                        if(response.body() != null){
                            if (ckbRemember.isChecked())
                            {
                                Paper.book().write(Common.USER_KEY,edtPhone.getText().toString());
                                Paper.book().write(Common.PWD_KEY,edtPassword.getText().toString());
                            }
                           Intent signInIntent = new Intent(SignInActivity.this , HomeActivity.class);
                            startActivity(signInIntent);
                        }else {
                            Toast.makeText(SignInActivity.this, "اطلاعات ورود صحیح نمیباشد", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        //Toast.makeText(SignInActivity.this, "خطایی رخ دادئه . لطفا مجددا اقدام نمایید", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        
        txt_forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetPassword();
            }
        });
    }

    private void forgetPassword() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SignInActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();
        View layout_pwd = inflater.inflate(R.layout.forget_password_popup_layout , null);

        EditText edtPhone = (EditText) layout_pwd.findViewById(R.id.edtPhone);
        EditText edtEmail = (EditText) layout_pwd.findViewById(R.id.edtEmail);
        Button btnSendEmail = (Button) layout_pwd.findViewById(R.id.btnSendEmail);

        alertDialog.setView(layout_pwd);


        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                callForgetPassword = service.AdminForgetPassword(email , phone);
                callForgetPassword.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        int result = response.body();
                        if(result == 1){
                            Toast.makeText(SignInActivity.this, "رمز با موفقیت به ایمیل شما ارسال گردید", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(SignInActivity.this, "شماره همراه یا ایمیل وارد شده صحیح نمیباشد", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(SignInActivity.this, "خطایی رخ دادئه . لطفا مجددا اقدام نمایید", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });



        alertDialog.show();
    }
}
