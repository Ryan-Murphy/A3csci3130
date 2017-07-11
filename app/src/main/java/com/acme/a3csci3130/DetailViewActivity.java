package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DetailViewActivity extends Activity {

    private EditText nameField, emailField, businessNumberField, primaryBusinessField, addressField, provinceField;
    private MyApplicationData appState;
    Contact receivedPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        appState = ((MyApplicationData) getApplicationContext());
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        businessNumberField = (EditText) findViewById(R.id.businessNumber);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
            businessNumberField.setText(receivedPersonInfo.businessNumber);
            primaryBusinessField.setText(receivedPersonInfo.primaryBusiness);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.province);
        }
    }
    /**
     * Updates current contact object in firebase database to new field values
     * */
    public void updateContact(View v){
        // Grab UID for our contact
        String uid = receivedPersonInfo.uid;
        // Create a database reference for our contact
        DatabaseReference contactReference = appState.firebaseReference.child(uid);
        // Create our Result Map to store new values into
        Map<String, Object> contactUpdates = new HashMap<String, Object>();
        // Add the values into our map
        contactUpdates.put("uid", uid);
        contactUpdates.put("name", nameField.getText().toString());
        contactUpdates.put("email", emailField.getText().toString());
        contactUpdates.put("businessNumber", businessNumberField.getText().toString());
        contactUpdates.put("primaryBusiness", primaryBusinessField.getText().toString());
        contactUpdates.put("address", addressField.getText().toString());
        contactUpdates.put("province", provinceField.getText().toString());
        // Update the contact object
        contactReference.updateChildren(contactUpdates);
    }
    /**
    * Will remove a specified contact which is currently in the view
    * */
    public void eraseContact(View v)
    {
        // Removes the current contact from the database
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
    }
}
