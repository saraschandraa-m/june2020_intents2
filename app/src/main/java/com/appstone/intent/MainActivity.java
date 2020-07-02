package com.appstone.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //1. Implicit Intent
    //2. Explicit Intent
    //startActivityForResult
    private EditText mEtUsername;
    private EditText mEtPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMove = findViewById(R.id.btn_intent);
        Button btnMoveWithResult = findViewById(R.id.btn_move_with_result);
        Button btnUpdatePhone = findViewById(R.id.btn_update_phone_result);
        mEtUsername = findViewById(R.id.et_input);
        mEtPhoneNumber = findViewById(R.id.et_phone_number);


        btnMove.setOnClickListener(this);
        btnMoveWithResult.setOnClickListener(this);
        btnUpdatePhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_intent) {
            String username = mEtUsername.getText().toString();
            Intent implicitIntent = new Intent(MainActivity.this, ImplicitIntentActivity.class);
            implicitIntent.putExtra("USERNAME", username);
            startActivity(implicitIntent);
        } else if (view.getId() == R.id.btn_move_with_result) {
            String username = mEtUsername.getText().toString();
            Intent resultIntent = new Intent(MainActivity.this, ImplicitIntentActivity.class);
            resultIntent.putExtra("USERNAME", username);
            startActivityForResult(resultIntent, 109);
        } else if (view.getId() == R.id.btn_update_phone_result) {
            String phoneNumber = mEtPhoneNumber.getText().toString();
            Intent phoneIntent = new Intent(MainActivity.this, ExplicitIntentActivity.class);
            phoneIntent.putExtra("PHONE", phoneNumber);
            startActivityForResult(phoneIntent, 110);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 109) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle returnData = data.getExtras();
                String updatedUsername = returnData.getString("USERNAME");
                mEtUsername.setText(updatedUsername);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "User pressed cancelled", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == 110) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle returnPhoneData = data.getExtras();
                String updatedPhone = returnPhoneData.getString("PHONE");
                mEtPhoneNumber.setText(updatedPhone);
            }
        } else {
            Toast.makeText(MainActivity.this, "Request Code did not match", Toast.LENGTH_LONG).show();
        }
    }
}