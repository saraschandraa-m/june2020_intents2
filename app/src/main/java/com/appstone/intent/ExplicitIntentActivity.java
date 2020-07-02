package com.appstone.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ExplicitIntentActivity extends AppCompatActivity {

    private EditText mEtPhone;
    private EditText mEtEmailAddress;
    private EditText mEtSubject;
    private EditText mEtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_intent);

        mEtPhone = findViewById(R.id.et_phone_explicit);
        mEtEmailAddress = findViewById(R.id.et_email_address);
        mEtSubject = findViewById(R.id.et_subject);
        mEtMessage = findViewById(R.id.et_message);

        Button btnUpdatePhone = findViewById(R.id.btn_update_phone);

        Button btnCall = findViewById(R.id.btn_call);
        Button btnEmail = findViewById(R.id.btn_email);
        Button btbBrowser = findViewById(R.id.btn_browser);

        Bundle data = getIntent().getExtras();

        String phoneNo = data.getString("PHONE");

        mEtPhone.setText(phoneNo);

        btnUpdatePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedPhone = mEtPhone.getText().toString();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("PHONE", updatedPhone);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = mEtPhone.getText().toString();

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phoneNo));
                startActivity(callIntent);
            }
        });

        btbBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://www.google.com"));
                startActivity(browserIntent);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = mEtSubject.getText().toString();
                String emailAddress = mEtEmailAddress.getText().toString();
                String message = mEtMessage.getText().toString();

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, message);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
                emailIntent.setType("message/rfc822");
                startActivity(emailIntent);
            }
        });
    }
}