package com.sota.repository;

import com.sota.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Song on 2017/2/15.
 * User表操作接口
 */
@Repository
public interface UserRepositoty extends JpaRepository<User, Integer>{
    @Query("select t from User t where t.name = :name")
    User findByUserName(@Param("name") String name);

    @Query("select t from User t where t.id = :id")
    User findByUserID(@Param("id") int id);

}
