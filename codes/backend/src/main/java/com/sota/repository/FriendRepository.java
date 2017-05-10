package com.sota.repository;

import com.sota.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by wenjin on 2017/5/8.
 */
@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer>{
    @Query("select t from Friend t where t.userID1 = :userID1")
    Friend[] findAllByUserID(@Param("userID1") int userID1);
}
