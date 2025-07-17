package com.example.Cookie.Syncing.Service;

import com.example.Cookie.Syncing.Model.CookieMatch;
import com.example.Cookie.Syncing.Repository.CookieMatchingRepo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class CookieSyncService {

    private final CookieMatchingRepo repo;
    public CookieSyncService(CookieMatchingRepo repo){
        this.repo=repo;
    }

    public void handleVisit(String mroCookieId) {
        CookieMatch match=repo.findById(mroCookieId)
                .orElseGet(()->{
                        CookieMatch m=new CookieMatch();
                        m.setMroCookieId(mroCookieId);
                    return m;
                });
        match.setTimestamp(new Date());
        repo.save(match);
    }

    public String sync(String mroCookieId) {
        CookieMatch match=repo.findById(mroCookieId)
                .orElseGet(()->{
                    CookieMatch m=new CookieMatch(mroCookieId);
                    return m;
                });
        if(match.getMroSyncId()==null){
            match.setMroSyncId("sync_"+UUID.randomUUID().toString());
        }
        match.setTimestamp(new Date());
        repo.save(match);
        return match.getMroSyncId();

    }
    public void adxCookie(String mroSyncId, String adxId) {
        CookieMatch match = repo.findByMroSyncId(mroSyncId);
        if (match != null) {
            match.setAdxCookieId("adx_"+adxId);
            match.setTimestamp(new Date());
            repo.save(match);
        }
    }

    public void fluctCookie(String mroSyncId, String fluctId) {
        CookieMatch match = repo.findByMroSyncId(mroSyncId);
        if (match != null) {
            match.setFluctCookieId("fluct_"+fluctId);
            match.setTimestamp(new Date());
            repo.save(match);
        }
    }





}
