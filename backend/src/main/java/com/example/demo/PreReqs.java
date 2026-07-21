package com.example.demo;

import java.util.*;

public class PreReqs {
    public static Map<String, List<String>> prereqs() {
        Map<String, List<String>> prereqGraph = new HashMap<>();

        //CS Classes
        prereqGraph.put("CSE 101", List.of());
        prereqGraph.put("CSE 205", List.of("CSE 101"));
        prereqGraph.put("CSE 240", List.of("CSE 205"));
        prereqGraph.put("CSE 230", List.of("EEE 120"));
        prereqGraph.put("CSE 310", List.of("CSE 240", "MAT 243"));
        prereqGraph.put("CSE 365", List.of("CSE 230", "CSE 240"));
        prereqGraph.put("CSE 355", List.of("CSE 310"));
        prereqGraph.put("CSE 360", List.of("CSE 240"));
        prereqGraph.put("CSE 330", List.of("CSE 230", "CSE 310"));
        prereqGraph.put("CSE 340", List.of("CSE 310"));
        prereqGraph.put("CSE 485", List.of("CSE 360", "CSE 355", "CSE 301", "CSE 330", "CSE 340"));
        prereqGraph.put("CSE 486", List.of("CSE 485"));

        //Math Classes
        prereqGraph.put("MAT 265", List.of());
        prereqGraph.put("MAT 266", List.of("MAT 265"));
        prereqGraph.put("MAT 267", List.of("MAT 266"));
        prereqGraph.put("MAT 243", List.of("MAT 265"));
        prereqGraph.put("IEE 380", List.of("MAT 266"));
        prereqGraph.put("MAT 343", List.of("MAT 266"));

        //English Classes
        prereqGraph.put("ENG 101", List.of());
        prereqGraph.put("ENG 102", List.of("ENG 101"));

        //Miscellaneous Classes
        prereqGraph.put("EEE 120", List.of());
        prereqGraph.put("FSE 100", List.of());
        prereqGraph.put("CSE 301", List.of("CSE 205", "FSE 100"));

        return prereqGraph;

    }
}
