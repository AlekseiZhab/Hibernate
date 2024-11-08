package ru.netology.hibernate.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MethodSecurityController {
    @Secured({"ROLE_READ"})
    @GetMapping("/read")
    public String getRead() {
        return "Read";
    }
    @RolesAllowed({"ROLE_WRITE"})
    @GetMapping("/write")
    public String getWrite() {
        return "Write";
    }
    @PreAuthorize("hasAnyRole('WRITE', 'DELETE')")
    @GetMapping("/wd")
    public String getWriteOrDelete() {
        return "Write or Delete";
    }
    @GetMapping("/")
    @PostAuthorize("#username == authentication.principal.username")
    public String greetingUser(String username) {
        return "Hi, " + username;
    }
}