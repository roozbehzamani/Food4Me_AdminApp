package com.example.shabnam.serverapp.Service;



import com.example.shabnam.serverapp.Model.AddFood;
import com.example.shabnam.serverapp.Model.CommentChild;
import com.example.shabnam.serverapp.Model.CommentParent;
import com.example.shabnam.serverapp.Model.EditFood;
import com.example.shabnam.serverapp.Model.Food;
import com.example.shabnam.serverapp.Model.OrderChild;
import com.example.shabnam.serverapp.Model.OrderParent;
import com.example.shabnam.serverapp.Model.ReservFoodChild;
import com.example.shabnam.serverapp.Model.ReservFoodParent;
import com.example.shabnam.serverapp.Model.ReservResChild;
import com.example.shabnam.serverapp.Model.ReservResParent;
import com.example.shabnam.serverapp.Model.Resturants;
import com.example.shabnam.serverapp.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("Home/AdminSignInAPI")
    Call<User> AdminSignInAPI(@Field("userPhone") String userPhone, @Field("userPassword") String userPassword);

    @FormUrlEncoded
    @POST("Home/AdminFoodListAPI")
    Call<List<Food>> GetResAdminHomeListAPI(@Field("type") String type, @Field("userEmail") String userEmail);

    @FormUrlEncoded
    @POST("Home/AdminResCommentParentAPI")
    Call<List<CommentParent>> AdminResCommentParentAPI(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("Home/AdminResCommentChildAPI")
    Call<List<CommentChild>> AdminResCommentChildAPI(
            @Field("id") int id
    );


    //new===================================================================
    @FormUrlEncoded
    @POST("Home/AdminResInformationAPI")
    Call<Resturants> AdminResInformationAPI(
            @Field("userEmail") String userEmail
    );

    @POST("Home/AdminAddNewFoodAPI")
    Call<Integer> AdminAddNewFoodAPI(@Body AddFood addFood);



    @FormUrlEncoded
    @POST("Home/AdminDeleteFoodAPI")
    Call<Integer> AdminDeleteFoodAPI(
            @Field("ID") int ID
    );

    @FormUrlEncoded
    @POST("Home/AdminShowFoodReserveParentAPI")
    Call<List<ReservFoodParent>> AdminShowFoodReserveParentAPI(
            @Field("resID") int resID
    );

    @FormUrlEncoded
    @POST("Home/AdminShowFoodReserveChildAPI")
    Call<List<ReservFoodChild>> AdminShowFoodReserveChildAPI(
            @Field("ParentID") int ParentID
    );

    @FormUrlEncoded
    @POST("Home/AdminShowResReserveParentAPI")
    Call<List<ReservResParent>> AdminShowResReserveParentAPI(
            @Field("resID") int resID
    );

    @FormUrlEncoded
    @POST("Home/AdminShowResReserveChildAPI")
    Call<List<ReservResChild>> AdminShowResReserveChildAPI(
            @Field("ParentID") int ParentID
    );

    @POST("Home/AdminEditFoodAPI")
    Call<Integer> AdminEditFoodAPI(@Body EditFood editFood);

    @FormUrlEncoded
    @POST("Home/AdminForgetPassword")
    Call<Integer> AdminForgetPassword(
            @Field("Email") String Email,
            @Field("Phone") String Phone
    );

    @FormUrlEncoded
    @POST("Home/AdminEditUser")
    Call<Integer> AdminEditUser(
            @Field("name") String name,
            @Field("address") String address,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("Home/AdminNewPasswordUser")
    Call<Integer> AdminNewPasswordUser(
            @Field("newPassword") String newPassword,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("Home/AdminOrderListAPI")
    Call<List<OrderParent>> AdminOrderListAPI(
            @Field("userEmail") String userEmail
    );

    @FormUrlEncoded
    @POST("Home/AdminOrderItemListAPI")
    Call<List<OrderChild>> AdminOrderItemListAPI(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("Home/AdminFilterOrderListAPI")
    Call<List<OrderParent>> AdminFilterOrderListAPI(
            @Field("userEmail") String userEmail,
            @Field("date") String date
    );

    @FormUrlEncoded
    @POST("Home/AdminFilterOrderItemListAPI")
    Call<List<OrderChild>> AdminFilterOrderItemListAPI(
            @Field("id") int id
    );
    @FormUrlEncoded
    @POST("Home/AdminShowFilterFoodReserveParentAPI")
    Call<List<ReservFoodParent>> AdminShowFilterFoodReserveParentAPI(
            @Field("resID") int resID,
            @Field("date") String date
    );
    @FormUrlEncoded
    @POST("Home/AdminShowFilterResReserveParentAPI")
    Call<List<ReservResParent>> AdminShowFilterResReserveParentAPI(
            @Field("resID") int resID,
            @Field("date") String date
    );
}