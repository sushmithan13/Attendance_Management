package com.example.sush_p1;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.HashMap;
import java.util.Map;
public class Upload_Story extends AppCompatActivity {
    Button uploadBtn;
    EditText file;
    EditText fileName;
    FirebaseStorage storage=FirebaseStorage.getInstance();
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    StorageReference reference;
//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_story);
        uploadBtn= (Button) findViewById(R.id.button);
        file= (EditText) findViewById(R.id.file);
        fileName=(EditText)findViewById(R.id.fileName);
        uploadBtn.setEnabled(false);
        reference=storage.getReference("UploadPDF");
        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPDF();
            }
        });
    }
    public void setPDF(){
        Intent intent= new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDF FILE SELECT"),12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==12 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            uploadBtn.setEnabled(true);
//            Log.d("details",));
            file.setText(fileName.getText().toString());
            uploadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadPdfFileFirebase(data.getData());
                }
            });
        }
    }
    private void uploadPdfFileFirebase(Uri data) {
        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("File is loading...");
        progressDialog.show();
        StorageReference ref= reference.child(file.getText().toString()+".pdf");
        ref.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri uri=uriTask.getResult();
                    putPDF putPDF= new putPDF(file.getText().toString(),uri.toString());
                Map<String,Object> pdfData= new HashMap<>();
                pdfData.put("PdfName",file.getText().toString());
                pdfData.put("PdfUri",uri.toString());
                db.collection("UploadPDF").add(pdfData).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        progressDialog.cancel();
                        Toast.makeText(Upload_Story.this, "SUCCESSFULLY UPLOADED", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
            }
        });
    }
    public void bacToFac(View view) {
        Intent intent= new Intent(this,Faculty_Page.class);
        startActivity(intent);
    }
}