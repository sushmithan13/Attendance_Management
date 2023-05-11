package com.example.sush_p1;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.firestore.FirebaseFirestore;
public class Faculty_Page extends AppCompatActivity {
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_page);
    }
    public void goToEventsEdit(View view) {
        Intent intent= new Intent(this, Events_Edit.class);
        startActivity(intent);
    }
    public void goToAttendance(View view) {
        Intent intent= new Intent(this, Attendance.class);
        startActivity(intent);
    }
    public void uploadPDF(View view) {
        Intent intent= new Intent(this, Upload_Story.class);
        startActivity(intent);
    }
    public void backToFacultyLogin(View view) {
        Intent intent = new Intent(this, Faculty_Login.class);
        startActivity(intent);
    }
}