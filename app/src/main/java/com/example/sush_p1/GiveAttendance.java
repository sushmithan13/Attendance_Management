package com.example.sush_p1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GiveAttendance extends AppCompatActivity {
    FirebaseFirestore db= FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_attendance);
    }

    public void addEventStudentToDb(View view) {
        String eventName=((TextView)findViewById(R.id.event_name2)).getText().toString();
        String eventId=((TextView)findViewById(R.id.event_id2)).getText().toString();
        String regno=((TextView)findViewById(R.id.regno2)).getText().toString();
//        Toast.makeText(this, eventName+"\t"+eventId+"\t"+regno, Toast.LENGTH_SHORT).show();
        Map<String, Object> eventData= new HashMap<>();
        eventData.put("EventData",Arrays.asList(eventName+"|"+eventId));
            db.collection("Attendance").document(regno).update("EventData",FieldValue.arrayUnion(eventName+"|"+eventId)).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    db.collection("Attendance").document(regno).set(eventData,SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(GiveAttendance.this, "SUCCCESSFULLY ADDED", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(GiveAttendance.this, "SUCCCESSFULLY ADDED", Toast.LENGTH_SHORT).show();
                }
            });

    }
}