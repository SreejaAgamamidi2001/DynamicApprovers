package com.enhisecure.dynamicApprover.controller;

import java.util.*;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.enhisecure.dynamicApprover.dto.*;
import com.enhisecure.dynamicApprover.service.*;
// Annotation
@RestController
@RequestMapping("/api/v1")
// Main class
public class approver {

    @Autowired
    private approverService approverService;
   // Method
    @PostMapping("/approver")
    public Approver getApprover(@RequestBody Request request) throws IOException {
        try {
            return approverService.getApprover(request);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/token")
    public String getAccessToken() throws IOException
    {
        return approverService.getAccessToken();
    }
}