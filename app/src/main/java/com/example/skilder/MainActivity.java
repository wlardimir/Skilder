package com.example.skilder;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String SELECTED_LANGUAGE = "SelectedLanguage";
    private static final String LANGUAGE_ENGLISH = "en";
    private static final String LANGUAGE_GERMAN = "de";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.language) {
            // Change the language when pressing the language change button
            toggleLanguage(item);
            return true;
        }

        if (id == R.id.help) {
            Toast.makeText(this, "Create new Help", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Method for changing the language
    private void toggleLanguage(MenuItem item) {
        // Determine which language is currently set
        Configuration config = getResources().getConfiguration();
        String currentLanguage = config.locale.getLanguage();

        // Set new language
        String newLanguage = (currentLanguage.equals(LANGUAGE_ENGLISH)) ? LANGUAGE_GERMAN : LANGUAGE_ENGLISH;

        // Change language
        setLocale(newLanguage);

        // Update the icon of the button
        updateLanguageMenuItemIcon(item, newLanguage);

        // Update the title of the toolbar
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);

        // Save the selected language
        saveLanguage(newLanguage);

        // Refresh the view to display the changes
        updateViews();
    }

    // Method for setting the language and updating the configuration
    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.setLocale(locale);

        Resources resources = getResources();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    // Method for updating the icon of the language change menu item
    private void updateLanguageMenuItemIcon(MenuItem item, String languageCode) {
        // Set the icon based on the language
        boolean isIconUK = true;
        if (languageCode.equals(LANGUAGE_ENGLISH)) {
            item.setIcon(R.drawable.uk);
            Toast.makeText(this, "Language set to English", Toast.LENGTH_SHORT).show();
        } else {
            isIconUK = false;
            item.setIcon(R.drawable.ger);
            Toast.makeText(this, "Sprache auf Deutsch gestellt", Toast.LENGTH_SHORT).show();
        }
    }

    // Method for saving the selected language in the SharedPreferences
    private void saveLanguage(String languageCode) {
        getPreferences(MODE_PRIVATE).edit().putString(SELECTED_LANGUAGE, languageCode).apply();
    }

    // Method for updating the view after the language change
    private void updateViews() {

    }
}
