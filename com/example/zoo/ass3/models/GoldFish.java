package com.example.zoo.ass3.models;

import com.example.zoo.ass3.exceptions.ColorNotExistException;
import com.example.zoo.ass3.exceptions.GeneralException;
import com.example.zoo.ass3.general.enums.Color;
import com.example.zoo.ass3.general.enums.Pattern;

import java.io.Serializable;

public class GoldFish extends Fish implements Serializable {
    private final static double EAT = 1f;
    public static final Color[] AVAILABLE_COLORS = {Color.GOLD, Color.ORANGE, Color.YELLOW, Color.BLACK};
    public final static int LIFE_EXPECTANCY = 12;
    private Color color;

    public GoldFish(int age, double length, Color color) throws GeneralException {
        super(age, length, Pattern.SMOOTH, LIFE_EXPECTANCY);
        setColor(color);
    }

    public Color[] getColors() {
        return new Color[]{color};
    }

    public void setColor(Color color) throws ColorNotExistException {
        for (Color availableColor : AVAILABLE_COLORS) {
            if (availableColor.equals(color)) {
                this.color = color;
                return;
            }
        }
        throw new ColorNotExistException();
    }

    @Override
    public double feed() {
        happiness = MAX_HAPPINESS;
        return EAT;
    }

    @Override
    public String toString() {
        return super.toString() + ", color: " + color;
    }
}
