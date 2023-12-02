package com.main.skilder;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.enums.Language;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
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
        updateLanguageMenuItemIcon(menu.findItem(R.id.language));
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.language) {
            // Change the language when pressing the language change button
            toggleLanguage();
        }

        if (id == R.id.help) {
            startNewActivity();
        }

        return true;
    }

    private Language getCurrentLanguage() {
        Configuration config = getResources().getConfiguration();
        int defaultLanguageIndex = 0;
        Locale defaultLocale = config.getLocales().get(defaultLanguageIndex);
        String localeCurrentLanguage = defaultLocale.getLanguage();
        Language currentLanguage = Language.ENGLISH;

        for(Language language : Language.values()) {
            if (language.getLanguageCode().equalsIgnoreCase(localeCurrentLanguage)) {
                currentLanguage = language;
                break;
            }
        }

        return currentLanguage;
    }

    // Method for changing the language
    private void toggleLanguage() {
        Language currentLanguage = this.getCurrentLanguage();
        int nextLanguageIndex = (currentLanguage.ordinal()+1) % Language.values().length;
        Language newLanguage = Language.values()[nextLanguageIndex];

        // Change language
        setLocale(newLanguage);

        // Recreate activity
        recreate();

        // Save the selected language
        saveLanguage(newLanguage);

        // Update the icon of the button without recreating the activity
        invalidateOptionsMenu();
    }

    // Method for setting the language and updating the configuration
    private void setLocale(Language language) {
        Locale locale = new Locale(language.getLanguageCode());
        Configuration conf = new Configuration();

        conf.setLocale(locale);
        this.getBaseContext().getResources().getConfiguration().setTo(conf);
    }

    // Method for updating the icon of the language change menu item
    private void updateLanguageMenuItemIcon(MenuItem item) {
         switch (this.getCurrentLanguage()) {
            case ENGLISH:
                item.setIcon(R.drawable.uk);
                break;

            case GERMAN:
                item.setIcon(R.drawable.de);
                break;

            default:
                // Other default languages will be turn to english
                setLocale(Language.ENGLISH);
                item.setIcon(R.drawable.uk);
                break;
        }
    }

    // Method for saving the selected language in the SharedPreferences
    private void saveLanguage(Language language) {
        getPreferences(MODE_PRIVATE).edit().putString(
                "SelectedLanguage",
                language.getLanguageCode()
        ).apply();
    }

    // Start New Activity
    private void startNewActivity() {
        Intent helpActivityIntent = new Intent(this, HelpActivity.class);
        startActivity(helpActivityIntent);
    }
}