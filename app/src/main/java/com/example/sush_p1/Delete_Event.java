package com.example.sush_p1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

public class Delete_Event extends AppCompatActivity {
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_event);
    }

    public void deleteFromDb(View view) {
        String deleteId = ((TextView)findViewById(R.id.event_id2)).getText().toString();
        String deleteName = ((TextView)findViewById(R.id.event_name2)).getText().toString();
        db.collection("EventData").document(deleteName+deleteId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Delete_Event.this, "SUCCESSFULLY DELETE", Toast.LENGTH_SHORT).show();
            }
        });
    }
}