
package com.mycompany.fitnessproject;

class ReportGenerator {
    private GymLog log;

    public ReportGenerator(GymLog log) {
        this.log = log;
    }

    public void printDailyReport() {
        Member[] members = log.getMembers();
        WorkOut[] workouts = log.getWorkouts();
        int[][] sessions = log.getSessions();

        System.out.println("=== Daily Gym Report ===");
        double totalCalories = 0;
        int totalSessions = 0;

        for (int i = 0; i < members.length; i++) {
            double memberCalories = 0;
            int memberSessions = 0;
            for (int j = 0; j < workouts.length; j++) {
                int count = sessions[i][j];
                if (count > 0) {
                    double cal = count * workouts[j].caloriesBurned();
                    memberCalories += cal;
                    memberSessions += count;
                }
            }
            totalCalories += memberCalories;
            totalSessions += memberSessions;
            System.out.printf("%s: %d sessions, %.2f calories burned%n",
                    members[i].getName(), memberSessions, memberCalories);
        }

        System.out.println("\nTotal sessions today: " + totalSessions);
        System.out.println("Total calories burned: " + totalCalories);
    }
}
