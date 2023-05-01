package com.example.sush_p1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Our_Story extends AppCompatActivity {
    ListView listView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_story);
        listView=(ListView) findViewById(R.id.result);
        db.collection("UploadPDF").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<String> NameList=new ArrayList<>();
                HashMap<String,String> hashMap= new HashMap<>();
                for(DocumentSnapshot doc:task.getResult().getDocuments()){
                    NameList.add(doc.get("PdfName").toString());
                    hashMap.put(doc.get("PdfName").toString(),doc.get("PdfUri").toString());
                }
                ArrayAdapter<String> arrayAdapter= new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_list_item_1,NameList);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String name=((TextView)view).getText().toString();
                        Log.d("data",name);
                        Intent intent= new Intent(Intent.ACTION_VIEW);
                        intent.setType("application/pdf");
                        intent.setData(Uri.parse(hashMap.get(name)));
                        startActivity(intent);
                    }
                });
            }
        });
    }

}