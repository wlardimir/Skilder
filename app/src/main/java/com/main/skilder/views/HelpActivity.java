package com.main.skilder.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.main.skilder.R;

public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbarHelp);
        setSupportActionBar(toolbar);

        // Initialize buttons and add click listeners
        Button callButton = findViewById(R.id.callButton);
        Button emailButton = findViewById(R.id.emailButton);

        callButton.setOnClickListener(v -> makePhoneCall());

        emailButton.setOnClickListener(v -> sendEmail());
    }
    // Options for returning to the main screen
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_help, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.backToMain) {
            // Back to the MainActivity
            finish();
        }

        return true;
    }
    // Method for starting a call
    private void makePhoneCall() {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:+123456789"));
        startActivity(dialIntent);
    }
    // Method for starting the e-mail client
    @SuppressLint("QueryPermissionsNeeded")
    private void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        // TODO Marc outsourcing in translation
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@skilder.de"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Betreff");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Nachrichtentext");

        // Set the package to ensure that Gmail is used
        emailIntent.setPackage("com.google.android.gm");

        // Check if the device has an e-mail client before you start the activity
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        } else {
            // If Gmail was not found, use the general intent
            emailIntent.setPackage(null);
            startActivity(Intent.createChooser(emailIntent, "E-Mail senden"));
        }
    }
}
