package com.olemissboys.studyinpeace.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.olemissboys.studyinpeace.MapsActivity;
import com.olemissboys.studyinpeace.R;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    public void buttonReset(View view) {
        startActivity(new Intent(getApplicationContext(), OptionsActivity.class));
    }

    public void backButton(View view)
    {
        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
    }
}
