package com.example.demo;

import java.util.List;

public class PlannerResponse {
    private List<String> eligibleCourses;
    private List<String> curatedCourses;

    public PlannerResponse(List<String> eligibleCourses, List<String> curatedCourses) {
        this.eligibleCourses = eligibleCourses;
        this.curatedCourses = curatedCourses;
    }
    public List<String> getEligibleCourses() { return eligibleCourses; }
    public List<String> getCuratedCourses() { return curatedCourses; }
}
