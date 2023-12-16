package com.main.skilder.views;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.enums.Language;
import com.main.skilder.R;
import com.main.skilder.controllers.MainController;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText widthEditText;
    private EditText heightEditText;
    private EditText ceilingHeightEditText;
    private Button calculateButton;
    private MenuItem languageButton;
    private MenuItem helpButton;
    private final MainController mainController = new MainController(this);
    private TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setWidthEditText(findViewById(R.id.width));
        this.setHeightEditText(findViewById(R.id.height));
        this.setCeilingHeightEditText(findViewById(R.id.ceilingHeight));
        this.setCalculateButton(findViewById(R.id.calculate));
        this.setResultTextView(findViewById(R.id.result));

        this.getCalculateButton().setOnClickListener(view -> this.getMainController().executeCalculation());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        this.setLanguageButton(menu.findItem(R.id.language));
        this.setHelpButton(menu.findItem(R.id.help));

        this.getLanguageButton().setOnMenuItemClickListener(view -> this.toggleLanguage());
        this.getHelpButton().setOnMenuItemClickListener(view -> this.startHelpActivity());

        updateLanguageMenuItemIcon(menu.findItem(R.id.language));

        return true;
    }
    private Language getCurrentLanguage() {
        Configuration config = getResources().getConfiguration();
        int defaultLanguageIndex = 0;
        Locale defaultLocale = config.getLocales().get(defaultLanguageIndex);
        String localeCurrentLanguage = defaultLocale.getLanguage();
        Language currentLanguage = Language.ENGLISH;

        for (Language language : Language.values()) {
            if (language.getLanguageCode().equalsIgnoreCase(localeCurrentLanguage)) {
                currentLanguage = language;
                break;
            }
        }

        return currentLanguage;
    }
    /**
     * Method for changing the language
     */
    private boolean toggleLanguage() {
        Language currentLanguage = this.getCurrentLanguage();
        int nextLanguageIndex = (currentLanguage.ordinal() + 1) % Language.values().length;
        Language newLanguage = Language.values()[nextLanguageIndex];

        // Change language
        setLocale(newLanguage);

        // Recreate activity
        recreate();

        // Save the selected language
        saveLanguage(newLanguage);

        // Update the icon of the button without recreating the activity
        invalidateOptionsMenu();

        return true;
    }
    /**
     * Method for setting the language and updating the configuration
     */
    private void setLocale(Language language) {
        Locale locale = new Locale(language.getLanguageCode());
        Configuration conf = new Configuration();

        conf.setLocale(locale);
        this.getBaseContext().getResources().getConfiguration().setTo(conf);
    }
    /**
     * Method for updating the icon of the language change menu item
     * @param item TODO MARC Kommentar was ist item
     */
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
    private boolean startHelpActivity() {
        Intent helpActivityIntent = new Intent(this, HelpActivity.class);
        startActivity(helpActivityIntent);

        return true;
    }
    public boolean isWidthEditTextEmpty() {
        return this.getWidthEditText().getText().toString().length() == 0;
    }
    public boolean isHeightEditTextEmpty() {
        return this.getHeightEditText().getText().toString().length() == 0;
    }
    public boolean isCeilingHeightEditTextEmpty() {
        return this.getCeilingHeightEditText().getText().toString().length() == 0;
    }
    public void showResultColorLiter(float squareMeters, float colorLiter) {
        String textTemplate = getString(R.string.result);
        String outputText = String.format(textTemplate, colorLiter, squareMeters);

        this.getResultTextView().setText(outputText);
    }
    public void showResultEmptyFields() {
        this.getResultTextView().setText(getString(R.string.resultErrorFields));
    }
    public void showResultCeilingHeightIsNull() {
        this.getResultTextView().setText(getString(R.string.resultEmptyCeilingHeight));
    }
    public float getWidth() {
        String widthEditText = this.getWidthEditText().getText().toString();

        return Float.parseFloat(widthEditText);
    }
    public float getHeight() {
        String heightEditText = this.getHeightEditText().getText().toString();

        return Float.parseFloat(heightEditText);
    }
    /**
     * @return If textfield is empty then return 0.0f otherwise what was entered
     * in the text field
     */
    public float getCeilingHeight() {
        String ceilingHeightEditText = this.getCeilingHeightEditText().getText().toString();
        float returnValue = 0.0f;

        if(!ceilingHeightEditText.equals("")) {
            returnValue = Float.parseFloat(ceilingHeightEditText);
        }

        return returnValue;
    }
    public EditText getWidthEditText() { return widthEditText; }
    public void setWidthEditText(EditText widthEditText) { this.widthEditText = widthEditText; }
    public EditText getHeightEditText() { return heightEditText; }
    public void setHeightEditText(EditText heightEditText) { this.heightEditText = heightEditText; }
    public EditText getCeilingHeightEditText() { return ceilingHeightEditText; }
    public void setCeilingHeightEditText(EditText ceilingHeightEditText) { this.ceilingHeightEditText = ceilingHeightEditText; }
    public Button getCalculateButton() { return calculateButton; }
    public void setCalculateButton(Button calculateButton) { this.calculateButton = calculateButton; }
    public MainController getMainController() { return mainController; }
    public TextView getResultTextView() { return resultTextView; }
    public void setResultTextView(TextView resultTextView) { this.resultTextView = resultTextView; }
    public void setLanguageButton(MenuItem languageButton) { this.languageButton = languageButton; }
    public MenuItem getLanguageButton() { return languageButton; }
    public void setHelpButton(MenuItem helpButton) { this.helpButton = helpButton; }
    public MenuItem getHelpButton() { return helpButton; }
}
