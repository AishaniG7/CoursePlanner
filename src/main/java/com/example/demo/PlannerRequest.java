package com.example.demo;

import java.util.List;

public class PlannerRequest {
    private List<String> completedCourses;
    private int standing;

    // getters and setters — required for Spring to convert JSON into this object
    public List<String> getCompletedCourses() { return completedCourses; }
    public void setCompletedCourses(List<String> completedCourses) { this.completedCourses = completedCourses; }
    public int getStanding() { return standing; }
    public void setStanding(int standing) { this.standing = standing; }
}
