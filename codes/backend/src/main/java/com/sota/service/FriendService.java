package com.sota.service;

import com.sota.entity.Friend;
import com.sota.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wenjin on 2017/5/8.
 */
@Service
public class FriendService {
    @Autowired
    private FriendRepository friendRepository;

    public Friend[] findAllByUserID(int userID1) {
        Friend[] friends = null;
        try {
            friends = friendRepository.findAllByUserID(userID1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return friends;
    }

    public Friend saveFriend(Friend friend) {
        Friend friend0 = friendRepository.save(friend);
        return friend0;
    }

    public void deleteFriend(Friend friend) {
        friendRepository.delete(friend);
    }
}
