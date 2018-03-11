package com.example.himel.fulbari;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    String msg;
    FirebaseDatabase database;
    DatabaseReference myReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn);
        button.setOnClickListener(this);
        database =  FirebaseDatabase.getInstance();
        myReference = database.getReference();

        //read valuse from firebase database
        myReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == button){
            EditText nameEt = findViewById(R.id.edittvname);
            EditText contactET = findViewById(R.id.edittvcontact);
            String child = nameEt.getText().toString();
            myReference = database.getReference("Users").child(child);

            myReference.child("Name").setValue(nameEt.getText().toString());
            myReference.child("Contact").setValue(contactET.getText().toString());

            Toast.makeText(getApplicationContext(),"Sended",Toast.LENGTH_SHORT).show();


        }

    }
}
