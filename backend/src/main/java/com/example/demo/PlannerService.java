package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PlannerService {
    public static List<String> getEligibleclasses(List<String> completedCourses, Map<String, List<String>> prereqGraph){
        List<String> eligible = new ArrayList<>();
        Set<String> completedSet = expandWithImpliedCompletions(completedCourses, prereqGraph);

        for (Map.Entry<String, List<String>> entry : prereqGraph.entrySet()){
            String currCourse = entry.getKey();
            List<String> prereqs = entry.getValue();


            if (completedSet.contains(currCourse)) {
                continue;
            }

            boolean valid = true;
            for (String prereq : prereqs){
                if (!completedSet.contains(prereq)){
                    valid = false;
                    break;
                }
            }

            if (valid){
                eligible.add(currCourse);
            }

        }
        return eligible;
    }

    public static Set<String> expandWithImpliedCompletions(
            List<String> completedCourses, Map<String, List<String>> prereqGraph) {

        Set<String> expanded = new HashSet<>(completedCourses);
        Deque<String> toProcess = new ArrayDeque<>(completedCourses);

        while (!toProcess.isEmpty()) {
            String course = toProcess.poll();
            List<String> prereqs = prereqGraph.getOrDefault(course, List.of());
            for (String prereq : prereqs) {
                if (expanded.add(prereq)) { // wasn't already in the set
                    toProcess.add(prereq);
                }
            }
        }
        return expanded;
    }

    public static List<String> giveRecommendedClasssesBasedOnYear(List<String> eligibleClasses, int standing, List<String> completedCourses) {
        List<String> curatedList = new ArrayList<>(eligibleClasses);

        if (standing == 1) {
            curatedList.add("ASU 101");
            curatedList.add("HUAD");

        } else if (standing == 2 && !(completedCourses.contains("SCIT Science"))) {
            curatedList.add("SCIT Science");
            curatedList.add("SOBE");
        } else if (standing == 3 && !(completedCourses.contains("LAB Science"))) {
            curatedList.add("LAB Science");
        } else if (standing == 4 && !(completedCourses.contains("LAB Science"))) {
            curatedList.add("LAB Science");
        } else if (standing == 5) {
            curatedList.add("AMIT");
        } else if (standing == 6) {
            curatedList.add("GSCI");
            curatedList.add("CIVI");
        } else if (standing == 7) {
            curatedList.add("SUST");
            curatedList.add("Tech Elective");
        } else if (standing == 8) {
            curatedList.add("Software Eng Elective");
            curatedList.add("Tech Elective");
        }

        if (eligibleClasses.contains("CSE 301") && (standing < 3)) {
            curatedList.remove("CSE 301");
        }

        if (eligibleClasses.contains("MAT 266") && eligibleClasses.contains("MAT 243")) {
            curatedList.remove("MAT 243");
        }

        if (eligibleClasses.contains("MAT 267")) {
            if (eligibleClasses.contains("MAT 343")) {
                curatedList.remove("MAT 343");
            }
            if (eligibleClasses.contains("IEE 380")) {
                curatedList.remove("IEE 380");
            }
        }

        if (eligibleClasses.contains("MAT 343") && eligibleClasses.contains("IEE 380")) {
            curatedList.remove("MAT 343");
        }

        if (standing < 2) {
            curatedList.remove("EEE 120");
        }


        if (eligibleClasses.contains("CSE 360") || eligibleClasses.contains("CSE 365") || eligibleClasses.contains("CSE 355")) {
            if (eligibleClasses.contains("CSE 340")) {
                curatedList.remove("CSE 340");
            }
            if (eligibleClasses.contains("CSE 330")) {
                curatedList.remove("CSE 330");
            }
        }
        return curatedList;
    }
}
