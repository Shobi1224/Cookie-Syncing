package com.example.Cookie.Syncing.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(indexName = "cookie_match_table")
public class CookieMatch {

    @Id
    private String mroCookieId;
    private String mroUserId;
    private String mroSyncId;
    private String adxCookieId;
    private String fluctCookieId;
    private Date timestamp;
    public CookieMatch(String mroCookieId) {
        this.mroCookieId = mroCookieId;
        this.timestamp = new Date();
    }



}
