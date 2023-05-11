package com.example.sush_p1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class GiveAttendance extends AppCompatActivity {
    EditText eventname,eventId,regno1;

    FirebaseFirestore db= FirebaseFirestore.getInstance();
    HashMap<String,String>hashMap=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_attendance);
        eventname=(EditText) findViewById(R.id.textView2);
        eventId=(EditText)findViewById(R.id.event_id2);
        regno1=(EditText)findViewById(R.id.regno);
//        Spinner spinner=(Spinner)findViewById(R.id.spinner_event_name);
//
////        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
//        List<String> eventNames=new ArrayList<>();
//        ArrayAdapter<String> str=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,eventNames);
//        spinner.setAdapter(str);
//        db.collection("EventData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful()){
//                    for(QueryDocumentSnapshot document:task.getResult()){
//                        eventNames.add(document.get("EventName").toString());
//                        hashMap.put(document.get("EventName").toString(),document.get("EventId").toString());
//                    }
//                }
//            }
//        });
//        str.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        str.notifyDataSetChanged();


//        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                Log.d("helo",hashMap.get(eventNames.get(i)));
//            }
//        });
    }
    public void addEventStudentToDb(View view) {
        if(!validateName() | !validateId() | !validateReg()){
            return;
        }
        String eventName=((TextView)findViewById(R.id.textView2)).getText().toString();
        String eventId=((TextView)findViewById(R.id.event_id2)).getText().toString();
        String regno=((TextView)findViewById(R.id.regno)).getText().toString();
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
    public void backtoAtt(View view) {
        Intent intent= new Intent(this,Attendance.class);
        startActivity(intent);
    }
    private boolean validateName(){
        String name= eventname.getText().toString();
        if(name.isEmpty())
        {
            eventname.setError("Event name cannot be empty");
            return false;
        }
        return true;

    }
    private boolean validateReg(){
        String name= regno1.getText().toString();
        if(name.isEmpty()){
            regno1.setError("Register Number cannot be empty");
            return false;
        }
        return true;

    }
    private boolean validateId(){
        String eId=eventId.getText().toString();
        String regex="[A-Za-z]{3}[0-9]{2}";
        if(eId.isEmpty())
        {
            eventId.setError("Event ID cannot be empty");
            return false;
        }

        else if(!eId.matches(regex))
        {
            eventId.setError("Event ID to be 3 letters and 2 numbers");
            return false;
        }
        return true;
    }
}