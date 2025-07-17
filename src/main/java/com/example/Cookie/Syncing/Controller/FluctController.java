package com.example.Cookie.Syncing.Controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/fluct")
public class FluctController {

@GetMapping("/sync")
public void sspRedirect(@RequestParam String redirect, @RequestParam String sync_id, HttpServletResponse response) {
    String fluctId = UUID.randomUUID().toString();
    Cookie cookie = new Cookie("fluct_id", "fluct_" + fluctId);
    cookie.setPath("/");
    response.addCookie(cookie);
    String separator = redirect.contains("?") ? "&" : "?";

    String finalRedirect = redirect + separator + "fluct_id=" + fluctId + "&sync_id=" + sync_id;

    response.setStatus(302);
    response.setHeader("Location", finalRedirect);
}

}
