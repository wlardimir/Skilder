package com.main.skilder.models;

public class Surface {
    private float width = 0.0f;
    private float height = 0.0f;
    private float ceilingHeight = 1.0f;
    private float squareMeters = 0.0f;
    private float colorLiter = 0.0f;

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getCeilingHeight() {
        return this.ceilingHeight;
    }

    public void setCeilingHeight(float ceilingHeight) {
        this.ceilingHeight = ceilingHeight;
    }

    public float getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(float squareMeters) {
        this.squareMeters = squareMeters;
    }

    public float getColorLiter() {
        return colorLiter;
    }

    public void setColorLiter(float colorLiter) {
        this.colorLiter = colorLiter;
    }

    private void calculateSurfaceSquareMeter() {
        this.setSquareMeters((this.getWidth() + this.getHeight()) * 2 * this.getCeilingHeight());
    }

    private void calculateColorLiter() {
        float surfaceSquareMeter = this.getSquareMeters();
        int literColorSteps = (int) Math.ceil(surfaceSquareMeter / 15.0f);
        float literFactor = 2.5f;

        this.setColorLiter(literFactor * literColorSteps);
    }

    public void initSurface() {
        this.calculateSurfaceSquareMeter();
        this.calculateColorLiter();
    }
}
