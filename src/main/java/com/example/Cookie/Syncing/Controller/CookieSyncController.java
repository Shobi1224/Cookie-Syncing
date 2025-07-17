package com.example.Cookie.Syncing.Controller;


import com.example.Cookie.Syncing.Service.CookieSyncService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping
public class CookieSyncController {

  private final CookieSyncService cookieSyncService;

    public CookieSyncController(CookieSyncService cookieSyncService) {
        this.cookieSyncService = cookieSyncService;
   }


    @GetMapping("/mro")
    public String handleVisit(@CookieValue(value = "mro_cookie_id", required = false) String mroCookieId, HttpServletResponse res) {
        if (mroCookieId == null) {
            mroCookieId = "mro_" + UUID.randomUUID().toString();
            Cookie c = new Cookie("mro_cookie_id", mroCookieId);
            c.setPath("/");
            c.setMaxAge(3600);
            res.addCookie(c);
        }

        cookieSyncService.handleVisit(mroCookieId);
        return "<html><body>" + "<img src='http://localhost:8080/cksync/sync?mro_cookie_id=" + mroCookieId + "'width='1' height='1'/>" + "</body></html>";
    }

    @GetMapping("/cksync/sync")
    public void sync(@CookieValue("mro_cookie_id") String mroCookieId, @CookieValue(value = "mro_sync_id", required = false) String mroSyncId, HttpServletResponse res) {
        if (mroSyncId == null) {
            mroSyncId = cookieSyncService.sync(mroCookieId);
            Cookie syncCookie = new Cookie("mro_sync_id", mroSyncId);
            syncCookie.setPath("/");
            syncCookie.setMaxAge(3600);
            res.addCookie(syncCookie);
        }
        String redirectToAdx = "http://localhost:8080/adx/sync?redirect=http://localhost:8080/cksync/callback&sync_id=" + mroSyncId;
        res.setStatus(302);
        res.setHeader("Location", redirectToAdx);

//        String redirectToFluct = "http://localhost:8080/fluct/sync?redirect=http://localhost:8080/cksync/callback&sync_id=" + mroSyncId;
//        res.setStatus(302);
//        res.setHeader("Location", redirectToFluct);

    }


    @GetMapping("cksync/callback")
    public ResponseEntity<String> receiveCallback(@RequestParam String sync_id,
                                                  @RequestParam(required = false) String adx_id,
                                                  @RequestParam(required = false) String fluct_id) {

        if (adx_id != null) {
            cookieSyncService.adxCookie(sync_id, adx_id);
        }
        if (fluct_id != null) {
            cookieSyncService.fluctCookie(sync_id, fluct_id);
        }
        return ResponseEntity.ok("Success");
    }


}


