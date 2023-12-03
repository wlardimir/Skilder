package com.main.skilder;

public class Calculator {
    static double resultColorArea;
    public static double calculateColorArea(double width, double height, double ceilingHeight) {
        return resultColorArea = (width + height) * 2 * ceilingHeight;
    }
    public static double calculateColorNeeded(double resultColorArea) {
        // Number of threshold values
        int thresholds = 8;

        // Size of each threshold range
        double rangeSize = 15.0;

        // default amount of color
        double defaultAmount = 0.0;

        // Calculation of the index of the area based on the resultColorArea
        int rangeIndex = (int) Math.ceil(resultColorArea / rangeSize);

        // Amount of color for each area
        return rangeIndex <= thresholds ? rangeIndex * 2.5 : defaultAmount;
    }
}
