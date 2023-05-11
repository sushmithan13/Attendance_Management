package com.example.sush_p1;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
public class Delete_Event extends AppCompatActivity {
    EditText eventname,eventId;

    FirebaseFirestore db= FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_event);
        eventname=(EditText) findViewById(R.id.event_name22);
        eventId=(EditText)findViewById(R.id.event_id2);
    }
    public void deleteFromDb(View view) {
        if(!validateName() | !validateId()){
            return;
        }
        String deleteId = ((TextView)findViewById(R.id.event_id2)).getText().toString();
        String deleteName = ((TextView)findViewById(R.id.event_name22)).getText().toString();
        db.collection("EventData").document(deleteName+deleteId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Delete_Event.this, "SUCCESSFULLY DELETED DATA", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void backToEdit(View view) {
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