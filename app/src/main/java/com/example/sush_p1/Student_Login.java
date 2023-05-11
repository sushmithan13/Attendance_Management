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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
public class Student_Login extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
    }
    public void checkLogin(View view) {
        TextView s_user=(TextView)findViewById(R.id.s_username_txt);
        TextView s_password=(TextView)findViewById(R.id.s_password_txt);
        String regno=s_user.getText().toString();
        String pass=s_password.getText().toString();
//        Toast.makeText(this, regno+"\t"+pass, Toast.LENGTH_SHORT).show();
        CollectionReference user = db.collection("users");
        Intent intent= new Intent(this, Student_Page.class);
        Task<QuerySnapshot> query = user.whereEqualTo("RegNo", regno).whereEqualTo("Password",pass).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(!task.getResult().isEmpty())
                    startActivity(intent);
                else
                    Toast.makeText(Student_Login.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void backToHomeStudent(View view) {
        Intent intent = new Intent(this, Home_Page.class);
        startActivity(intent);
    }
    public void backToHom1(View view) {
        Intent intent= new Intent(this,Home_Page.class);
        startActivity(intent);
    }
}