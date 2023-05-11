package com.example.sush_p1;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class Student_Page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);
    }
    public void bacToLogin(View view) {
        Intent intent= new Intent(this,Student_Login.class);
        startActivity(intent);
    }
    public void toShowEvents(View view) {
        Intent intent = new Intent(this, Event_Page.class);
        startActivity(intent);
    }
}