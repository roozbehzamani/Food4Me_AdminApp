package com.example.shabnam.serverapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shabnam.serverapp.Common.Common;
import com.example.shabnam.serverapp.Model.CommentChild;
import com.example.shabnam.serverapp.Model.CommentParent;
import com.example.shabnam.serverapp.Service.ApiService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentActivity extends AppCompatActivity {

    Retrofit retrofit;
    ApiService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://android-application-api.ir/")
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiService.class);


        final ExpandableLayout layout = (ExpandableLayout) findViewById(R.id.comment_expandablelayout);

        layout.setRenderer(new ExpandableLayout.Renderer<CommentParent,CommentChild>() {
            @Override
            public void renderParent(View view, CommentParent phoneCategory, boolean isExpanded, int parentPosition) {
                ((TextView)view.findViewById(R.id.txtCommentName)).setText(phoneCategory.getName());

                view
                        .findViewById(R.id.arrow)
                        .setBackgroundResource(isExpanded?R.drawable.ic_keyboard_arrow_up_white_24dp:R.drawable.ic_keyboard_arrow_down_white_24dp);

            }

            @Override
            public void renderChild(final View view, CommentChild phone, int parentPosition, int childPosition) {
                ((TextView)view.findViewById(R.id.txtUserPhone)).setText(phone.getName());
                ((TextView)view.findViewById(R.id.txtComment)).setText(phone.getComment());
                ((RatingBar)view.findViewById(R.id.ratingBarComment)).setRating((phone.getStar()));

            }
        });


        Call<List<CommentParent>> call = service.AdminResCommentParentAPI(Common.currentResturants.getID());
        call.enqueue(new Callback<List<CommentParent>>() {
            @Override
            public void onResponse(Call<List<CommentParent>> call, Response<List<CommentParent>> response) {

                for (CommentParent comment : response.body()) {
                    layout.addSection(getSection(
                            comment.getID() ,
                            comment.getName()
                    ));
                }
            }

            @Override
            public void onFailure(Call<List<CommentParent>> call, Throwable t) {

                Log.i("Hello",""+t);
                Toast.makeText(CommentActivity.this, "Throwable"+t, Toast.LENGTH_LONG).show();

            }
        });

    }
    private Section<CommentParent,CommentChild> getSection(int id, String Name) {

        final Section<CommentParent,CommentChild> section = new Section<>();

        CommentParent phoneCategory = new CommentParent(
                id,
                Name
        );

        //end parent



        //start child

        final List<CommentChild> listPhone = new ArrayList<>();

        Call<List<CommentChild>> callItems = service.AdminResCommentChildAPI(id);
        callItems.enqueue(new Callback<List<CommentChild>>() {
            @Override
            public void onResponse(Call<List<CommentChild>> call, Response<List<CommentChild>> response) {

                for (CommentChild comment : response.body()) {
                    listPhone.add(new CommentChild(
                            comment.getID(),
                            comment.getStar(),
                            comment.getComment(),
                            comment.getName()
                    ));
                }
                section.children.addAll(listPhone);
            }

            @Override
            public void onFailure(Call<List<CommentChild>> call, Throwable t) {

                Log.i("Hello",""+t);
                Toast.makeText(CommentActivity.this, "Throwable"+t, Toast.LENGTH_LONG).show();

            }
        });

        section.parent = phoneCategory;


        return section;
    }
}
