package com.example.sush_p1;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
public class Add_Event extends AppCompatActivity {
    EditText eventname,eventId;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        eventname=(EditText) findViewById(R.id.event_name25);
        eventId=(EditText)findViewById(R.id.event_id2);

        findViewById(R.id.event_date2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                EditText dateData=(EditText) view;
                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        Add_Event.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                dateData.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });
    }
    public void addEventToDb(View view) {
        if(!validateName() | !validateId()){
            return;
        }
        String eventId=((TextView)findViewById(R.id.event_id2)).getText().toString();
        String eventName=((TextView)findViewById(R.id.event_name25)).getText().toString();
        String eventDate=((EditText)findViewById(R.id.event_date2)).getText().toString();
        String eventDescription=((TextView)findViewById(R.id.event_name21)).getText().toString();
        Map<String,Object> eventAddData= new HashMap<>();
        eventAddData.put("EventName",eventName);
        eventAddData.put("EventId",eventId);
        eventAddData.put("EventDate",eventDate);
        eventAddData.put("EventDescription",eventDescription);
        db.collection("EventData").document(eventName+eventId).set(eventAddData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Add_Event.this, "SUCCESSFULLY ADDED DATA", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void addEventBack(View view) {
        Intent intent= new Intent(this,Events_Edit.class);
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