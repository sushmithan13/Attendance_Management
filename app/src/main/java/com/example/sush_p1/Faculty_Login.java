package com.example.sush_p1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Faculty_Login extends AppCompatActivity {

    FirebaseFirestore db= FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);

    }

    public void goToFaculty(View view) {
        TextView username= (TextView)findViewById(R.id.search_txt);
        TextView fpassword=(TextView) findViewById(R.id.f_password_txt);
        String fusername=username.getText().toString();
        String fpass=fpassword.getText().toString();
//        Toast.makeText(this, fusername+"\t"+fpass, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,Faculty_Page.class);
        CollectionReference fuser= db.collection("fuser");
        Task<QuerySnapshot> query=fuser.whereEqualTo("UserName", fusername).whereEqualTo("FacultyPassword", fpass).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                for(QueryDocumentSnapshot document: task.getResult())
//                    Toast.makeText(Faculty_Login.this, document.getData().toString(), Toast.LENGTH_SHORT).show();
                if(!task.getResult().isEmpty())
                   startActivity(intent);
                else
                    Toast.makeText(Faculty_Login.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}