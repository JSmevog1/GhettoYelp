package com.example.ghettoyelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ViewAllUsersActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_users);
    }

    static Intent ViewAllUsersIntentFactory(Context context){
        return new Intent(context, ViewAllUsersActivity.class);
    }
}

