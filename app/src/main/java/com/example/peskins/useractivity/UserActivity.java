package com.example.peskins.useractivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;


public class UserActivity extends AppCompatActivity {

    private User user;
    private Gson gson;
    private TextView nameText;
    private TextView moteText;
    private TextView followingText;
    private TextView followersText;
    private TextView numbfollowing;
    private TextView numfollowers;
    private TextView about;
    private ImageView photo_user;
    private ImageView photo_background;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        gson = new Gson();
        queue = Volley.newRequestQueue(this);

        nameText = findViewById(R.id.nameText);
        moteText = findViewById(R.id.moteText);
        followingText = findViewById(R.id.followingText);
        followersText = findViewById(R.id.followersView);
        numbfollowing = findViewById(R.id.numb_following);
        numfollowers = findViewById(R.id.numFollowers);
        about = findViewById(R.id.about);
        photo_user = findViewById(R.id.photoView);
        photo_background = findViewById(R.id.ImageView);

        StringRequest req = new StringRequest(
                Request.Method.GET,
                "JohnDoe.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                user = gson.fromJson(response, User.class);
                updateUser();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserActivity.this, "Error de xarxa", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(req);

        Glide.with(this)
                .load("file:///android_asset/Jose.JPG")
                .into(photo_user);
        Glide.with(this)
                .load("file:///android_asset/UserProfile-background.jpg")
                .into(photo_background);

    }
    private void updateUser()
    {
        nameText.setText(user.getName());
        moteText.setText(user.getHandle());
        numbfollowing.setText(user.getFollowing());
        numfollowers.setText(user.getFollowers());
        about.setText(user.getAbout());
    }
}
