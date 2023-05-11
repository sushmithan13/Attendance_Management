package com.example.sush_p1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.HashMap;
public class Event_Page extends AppCompatActivity {
    ListView listView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        listView=(ListView) findViewById(R.id.ListEvents);
        db.collection("EventData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<String> EventList= new ArrayList<>();
                for(DocumentSnapshot doc:task.getResult().getDocuments())
                {
                    EventList.add(doc.get("EventName").toString());
                    EventList.add(doc.get("EventId").toString());
                    EventList.add(doc.get("EventDescription").toString());
                    EventList.add(doc.get("EventDate").toString());
                    EventList.add("\n-------------------------------------------------------------------------\n");
                }
                ArrayAdapter<String> arrayAdapter= new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_list_item_1,EventList);
                listView.setAdapter(arrayAdapter);
            }
        });
    }
    public void backtoStudent(View view) {
        Intent intent= new Intent(this,Student_Page.class);
        startActivity(intent);
    }
}