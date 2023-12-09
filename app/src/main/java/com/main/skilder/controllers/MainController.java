package com.main.skilder.controllers;

import com.main.skilder.models.Surface;
import com.main.skilder.views.MainActivity;

public class MainController {
    private MainActivity mainActivity;

    private void calculate() {
        MainActivity currentMainActivity = this.getMainActivity();
        Surface currentSurface = new Surface();
        float width = currentMainActivity.getWidth();
        float height = currentMainActivity.getHeight();
        float ceilingHeight = !currentMainActivity.isCeilingHeightEditTextEmpty() ? currentMainActivity.getCeilingHeight() : 1.0f;

        currentSurface.setWidth(width);
        currentSurface.setHeight(height);
        currentSurface.setCeilingHeight(ceilingHeight);

        currentSurface.initSurface();

        currentMainActivity.showResultColorLiter(
                currentSurface.getSquareMeters(),
                currentSurface.getColorLiter()
        );
    }

    public void executeCalculation() {
        // TODO refector if statements
        MainActivity currentMainActivity = this.getMainActivity();
        boolean hasError = true;

        if(!currentMainActivity.isWidthEditTextEmpty() && !currentMainActivity.isHeightEditTextEmpty()) {
            if(!currentMainActivity.isCeilingHeightEditTextEmpty()) {
                if(currentMainActivity.getCeilingHeight() > 0.0f) {
                    hasError = false;
                }
            } else {
                hasError = false;
            }
        }

        if(!hasError) {
            calculate();
        } else {
            currentMainActivity.showResultEmptyFields();
        }
    }

    public MainController(MainActivity mainActivity) {
        this.setMainActivity(mainActivity);
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}
