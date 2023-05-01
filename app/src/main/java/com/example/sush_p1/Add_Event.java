package com.example.sush_p1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Add_Event extends AppCompatActivity {
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
    }

    public void addEventToDb(View view) {
        String eventId=((TextView)findViewById(R.id.event_id2)).getText().toString();
        String eventName=((TextView)findViewById(R.id.event_name2)).getText().toString();
        String eventDate=((TextView)findViewById(R.id.event_date2)).getText().toString();
        String eventDescription=((TextView)findViewById(R.id.regno2)).getText().toString();
        Map<String,Object> eventAddData= new HashMap<>();
        eventAddData.put("EventName",eventName);
        eventAddData.put("EventId",eventId);
        eventAddData.put("EventDate",eventDate);
        eventAddData.put("EventDescription",eventDescription);
        db.collection("EventData").document(eventName+eventId).set(eventAddData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Add_Event.this, "SUCCESSFULLY ADDED DATA", Toast.LENGTH_SHORT).show();
            }
        });

    }


}