package org.example.volodyanoy.SecurityApp.controllers;

import org.example.volodyanoy.SecurityApp.security.PersonDetails;
import org.example.volodyanoy.SecurityApp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTUsersController {
    private final AdminService adminService;

    @Autowired
    public RESTUsersController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        return personDetails.getUsername();
    }

    @GetMapping("/admin")
    public String adminPage(){

        return adminService.doAdminStuff();
    }
}
