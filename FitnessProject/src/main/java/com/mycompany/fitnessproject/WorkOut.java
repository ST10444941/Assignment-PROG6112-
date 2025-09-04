/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitnessproject;

abstract class WorkOut {
    private String name;
    private int durationMinutes; // per session
    private double caloriesPerMinute;

    public WorkOut(String name, int durationMinutes, double caloriesPerMinute) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        if (durationMinutes <= 0) throw new IllegalArgumentException("Duration must be > 0");
        if (caloriesPerMinute <= 0) throw new IllegalArgumentException("Calories must be > 0");
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.caloriesPerMinute = caloriesPerMinute;
    }

    public String getName() { return name; }
    public int getDurationMinutes() { return durationMinutes; }
    public double getCaloriesPerMinute() { return caloriesPerMinute; }

    public double caloriesBurned() {
        return durationMinutes * caloriesPerMinute;
    }

    public abstract String getCategory();
}

class CardioWorkOut extends WorkOut {
    private String machineType;
    public CardioWorkOut(String name, int duration, double calPerMin, String machineType) {
        super(name, duration, calPerMin);
        this.machineType = machineType;
    }
    @Override
    public String getCategory() { return "Cardio"; }
}

class StrengthWorkOut extends WorkOut {
    private int sets;
    public StrengthWorkOut(String name, int duration, double calPerMin, int sets) {
        super(name, duration, calPerMin);
        this.sets = sets;
    }
    @Override
    public String getCategory() { return "Strength"; }
}

class YogaWorkOut extends WorkOut {
    private String difficulty;
    public YogaWorkOut(String name, int duration, double calPerMin, String difficulty) {
        super(name, duration, calPerMin);
        this.difficulty = difficulty;
    }
    @Override
    public String getCategory() { return "Yoga"; }
}

