package com.appstone.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ImplicitIntentActivity extends AppCompatActivity {

    private EditText etUpdateInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);

        TextView mtvMain = findViewById(R.id.tv_main);
        etUpdateInput = findViewById(R.id.et_update_input);

        Bundle data = getIntent().getExtras();

        String username = data.getString("USERNAME");
        mtvMain.setText(username);

        etUpdateInput.setText(username);
    }

    public void onUpdateClicked(View view) {
        String updatedUsername = etUpdateInput.getText().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("USERNAME", updatedUsername);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    public void onCancelClicked(View view) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }


}