package com.example.sush_p1;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class Home_Page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }
    public void goToStudentLogin(View view) {
        Intent intent= new Intent(this,Student_Login.class);
        startActivity(intent);
    }
    public void goToFacultyLogin(View view) {
        Intent intent = new Intent(this, Faculty_Login.class);
        startActivity(intent);
    }
    public void goToOurStory(View view) {
        Intent intent=new Intent(this,Our_Story.class);
        startActivity(intent);
    }
    public void backToMain(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}