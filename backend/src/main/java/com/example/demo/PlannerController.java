package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/planner")
@CrossOrigin(origins = "*")
public class PlannerController {
    private final PlannerService plannerService;

    public PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService; // Spring auto-provides this
    }

    @PostMapping("/recommend")
    public PlannerResponse recommend(@RequestBody PlannerRequest request) {
        Map<String, List<String>> prereqGraph = PreReqs.prereqs();
        List<String> eligible = plannerService.getEligibleclasses(request.getCompletedCourses(), prereqGraph);
        List<String> curated = plannerService.giveRecommendedClasssesBasedOnYear(eligible, request.getStanding(), request.getCompletedCourses());
        return new PlannerResponse(eligible, curated);
    }
}
