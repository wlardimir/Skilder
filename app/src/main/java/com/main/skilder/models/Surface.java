package com.main.skilder.models;

public class Surface {
    private float width = 0.0f;
    private float height = 0.0f;
    private float ceilingHeight = 0.0f;
    private float squareMeters = 0.0f;
    private float colorLiter = 0.0f;
    public float getWidth() {
        return this.width;
    }
    public void setWidth(Float width) {
        this.width = width;
    }
    public float getHeight() {
        return this.height;
    }
    public void setHeight(Float height) {
        this.height = height;
    }
    public float getCeilingHeight() {
        return this.ceilingHeight;
    }
    public void setCeilingHeight(Float ceilingHeight) {
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
    /**
     * Perform the calculation of surface according the square meters.
     * Empty ceiling height field will be handle like 1.0f.
     */
    private void calculateSurfaceSquareMeter() {
        float ceilingHeight = this.getCeilingHeight() != 0.0f ? this.getCeilingHeight() : 1.0f;

        this.setSquareMeters((this.getWidth() + this.getHeight()) * 2 * ceilingHeight);
    }
    /**
     * Perform the calculation how many liter of color is needed for the following surface
     */
    private void calculateColorLiter() {
        float surfaceSquareMeter = this.getSquareMeters();
        int literColorSteps = (int) Math.ceil(surfaceSquareMeter / 15.0f);
        float literFactor = 2.5f;

        this.setColorLiter(literFactor * literColorSteps);
    }
    /**
     * The initialization of the object then calculates the surface square meters
     * and the number of liters of the colors
     */
    public void initSurface() {
        this.calculateSurfaceSquareMeter();
        this.calculateColorLiter();
    }
}
