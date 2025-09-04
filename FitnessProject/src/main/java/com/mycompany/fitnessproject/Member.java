/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitnessproject;

public class Member {
    private String id;
    private String name;

    public Member(String id, String name) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("ID required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
}

class GymLog {
    private Member[] members;
    private WorkOut[] workouts;
    private int[][] sessions; // rows = members, cols = workouts (number of times done)

    public GymLog(Member[] members, WorkOut[] workouts) {
        this.members = members;
        this.workouts = workouts;
        this.sessions = new int[members.length][workouts.length];
    }

    public void recordSession(String memberId, String workoutName) {
        int mIndex = -1, wIndex = -1;
        for (int i = 0; i < members.length; i++)
            if (members[i].getId().equals(memberId)) mIndex = i;
        for (int j = 0; j < workouts.length; j++)
            if (workouts[j].getName().equals(workoutName)) wIndex = j;

        if (mIndex == -1 || wIndex == -1) throw new IllegalArgumentException("Invalid member or workout");
        sessions[mIndex][wIndex]++;
    }

    public int[][] getSessions() { return sessions; }
    public Member[] getMembers() { return members; }
    public WorkOut[] getWorkouts() { return workouts; }
}

