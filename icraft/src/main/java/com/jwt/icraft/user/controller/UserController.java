package com.jwt.icraft.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("")
    public String test() {
        return "test";
    }

    @GetMapping("/only-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String onlyAdmin() {
        return "only-admin";
    }

    @GetMapping("/admin-user")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String adminUser() {
        return "admin-user";
    }



}
