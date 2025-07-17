package com.example.Cookie.Syncing.Repository;

import com.example.Cookie.Syncing.Model.CookieMatch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CookieMatchingRepo extends ElasticsearchRepository<CookieMatch,String> {
    CookieMatch findByMroSyncId(String mroSyncId);
}
