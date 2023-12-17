package com.main.skilder.controllers;

import android.widget.EditText;
import com.main.skilder.models.Surface;
import com.main.skilder.views.MainActivity;

public class MainController {
    private MainActivity mainActivity;
    /**
     * Perform the calculation and output it on the surface of android app
     */
    private void calculate() {
        MainActivity currentMainActivity = this.getMainActivity();
        Surface currentSurface = new Surface();
        float width = currentMainActivity.getWidth();
        float height = currentMainActivity.getHeight();
        float ceilingHeight = currentMainActivity.getCeilingHeight();

        currentSurface.setWidth(width);
        currentSurface.setHeight(height);
        currentSurface.setCeilingHeight(ceilingHeight);

        currentSurface.initSurface();

        currentMainActivity.showResultColorLiter(
                currentSurface.getSquareMeters(),
                currentSurface.getColorLiter()
        );
    }
    /**
     * Calculation handler that performs a calculation and
     * outputs an error message if the input is incorrect.
     * Fields Width and Height are required fields.
     * Field ceiling height can't be zero.
     */
    public void executeCalculation() {
        MainActivity currentMainActivity = this.getMainActivity();
        boolean hasWidthAndHeight = !currentMainActivity.isWidthEditTextEmpty()
                && !currentMainActivity.isHeightEditTextEmpty();
        boolean isCeilingHeightEmpty = currentMainActivity.isCeilingHeightEditTextEmpty();
        boolean isCeilingHeightNull = currentMainActivity.getCeilingHeight() == 0.0f;
        boolean allowCalculation = true;

        if(!hasWidthAndHeight) {
            currentMainActivity.showResultEmptyFields();
            allowCalculation = false;
        }

        if(!isCeilingHeightEmpty && isCeilingHeightNull) {
            currentMainActivity.showResultCeilingHeightIsNull();
            allowCalculation = false;
        }

        if(allowCalculation) {
            calculate();
        }
    }
    public void clearCalculation() {
        MainActivity currentMainActivity = this.getMainActivity();

        EditText editTextWidth = currentMainActivity.getWidthEditText();
        EditText editTextHeight = currentMainActivity.getHeightEditText();
        EditText editTextCeilingHeight = currentMainActivity.getCeilingHeightEditText();

        editTextWidth.setText("");
        editTextHeight.setText("");
        editTextCeilingHeight.setText("");

        Surface currentSurface = new Surface();
        currentSurface.setWidth(0.0f);
        currentSurface.setHeight(0.0f);
        currentSurface.setCeilingHeight(0.0f);
    }

    public MainController(MainActivity mainActivity) { this.setMainActivity(mainActivity); }
    public MainActivity getMainActivity() { return mainActivity; }
    public void setMainActivity(MainActivity mainActivity) { this.mainActivity = mainActivity; }
}
