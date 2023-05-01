package com.example.sush_p1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Events_Edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_edit);
    }

    public void goToAddEvent(View view) {
        Intent intent= new Intent(this, Add_Event.class);
        startActivity(intent);
    }

    public void goToDeleteEvent(View view) {
        Intent intent= new Intent(this, Delete_Event.class);
        startActivity(intent);
    }
}