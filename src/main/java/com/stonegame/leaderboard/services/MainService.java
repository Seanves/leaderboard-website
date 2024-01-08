package com.stonegame.leaderboard.services;

import com.stonegame.leaderboard.entities.User;
import com.stonegame.leaderboard.entities.UserInfo;
import com.stonegame.leaderboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

    private final UserRepository userRepository;
    private final Sort sort;
    private List<UserInfo> cachedTop;
    private long lastCacheUpdate;

    private static final long CACHE_UPDATE_FREQUENCY = 1000 * 60;

    @Autowired
    public MainService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.sort = Sort.by(
                Sort.Order.desc("rating"),
                Sort.Order.desc("maxRating"),
                Sort.Order.asc("nickname")
        );
    }


    public List<UserInfo> getTop() {
        if(System.currentTimeMillis() > lastCacheUpdate + CACHE_UPDATE_FREQUENCY) {
            updateTopCache();
        }
        return cachedTop;
    }

    private void updateTopCache() {
        List<User> users = userRepository.findAll(sort);

        List<UserInfo> top = new ArrayList<>();
        for(int i=0; i<10 && i<users.size(); i++) {
            top.add(new UserInfo(users.get(i), i+1));
        }

        cachedTop = top;
        lastCacheUpdate = System.currentTimeMillis();
    }

}
