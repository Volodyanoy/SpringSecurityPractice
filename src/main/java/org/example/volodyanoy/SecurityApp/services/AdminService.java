package org.example.volodyanoy.SecurityApp.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_')")
    public String doAdminStuff(){
        return "Only admin here";
    }
}
