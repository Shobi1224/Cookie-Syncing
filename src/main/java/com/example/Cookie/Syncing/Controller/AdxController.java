package com.example.Cookie.Syncing.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/adx")
public class AdxController {


    @GetMapping("/sync")
    public void sspRedirect(@RequestParam String redirect, @RequestParam String sync_id, HttpServletResponse response) {
        String adxId = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("adx_id", "adx_" + adxId);
        cookie.setPath("/");
        response.addCookie(cookie);
        String separator = redirect.contains("?") ? "&" : "?";
        String finalRedirect = redirect + separator + "adx_id=" + adxId + "&sync_id=" + sync_id;

        response.setStatus(302);
        response.setHeader("Location", finalRedirect);
    }

}