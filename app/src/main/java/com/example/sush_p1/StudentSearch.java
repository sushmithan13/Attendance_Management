package com.example.sush_p1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentSearch extends AppCompatActivity {
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search);
    }

    public void search(View view) {
        String searchTxt=((TextView)findViewById(R.id.search_txt)).getText().toString();
        Task<DocumentSnapshot> query= db.collection("Attendance").document(searchTxt).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(!documentSnapshot.exists())
                    Toast.makeText(StudentSearch.this, "NO DATA FOUND", Toast.LENGTH_SHORT).show();
                else{
                    String d=documentSnapshot.get("EventData").toString();
                    String[] l=d.substring(1, d.length() - 2).split(",");
                    for(int i=0;i<l.length;i++){
                        l[i]="event:"+l[i].replace("|","\n Id:");
//                        l[i]="Event:"+l[i].split("|")[0]+"\n"+"Id: "+l[i].split("|")[1]
                    };
                    ArrayList<String> events= new ArrayList<>(Arrays.asList(l));
//                    Log.d("data",events.get(0));
                    ListView listView= findViewById(R.id.search_result);
                    ArrayAdapter<String> arrayAdapter= new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,events);
                    listView.setAdapter(arrayAdapter);
                }
            }
        });
    }
}