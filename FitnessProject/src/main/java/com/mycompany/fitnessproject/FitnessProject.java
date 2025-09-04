
package com.mycompany.fitnessproject;

public class FitnessProject {

    public static void main(String[] args) {
        
      Member[] members = {
            new Member("M1", "Alice"),
            new Member("M2", "Bob"),
            new Member("M3", "Charlie")
        };

        WorkOut[] workouts = {
            new CardioWorkOut("Treadmill", 30, 8.5, "Treadmill Machine"),
            new StrengthWorkOut("Bench Press", 20, 6.0, 4),
            new YogaWorkOut("Morning Flow", 45, 4.0, "Intermediate")
        };

        GymLog log = new GymLog(members, workouts);

        // Simulate some sessions
        log.recordSession("M1", "Treadmill");
        log.recordSession("M1", "Bench Press");
        log.recordSession("M2", "Morning Flow");
        log.recordSession("M2", "Treadmill");
        log.recordSession("M3", "Treadmill");
        log.recordSession("M3", "Morning Flow");

        ReportGenerator report = new ReportGenerator(log);
        report.printDailyReport();
    }
}  
      
    

