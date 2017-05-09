package com.sota.repository;

import com.sota.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by wenjin on 2017/5/8.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>{
    @Query("select t from Image t where t.userID = :userID")
    Image[] findAllByUserID(@Param("userID") int userID);

    @Query("select t from Image t where t.id = :id")
    Image findByImageId(@Param("id")int id);

}
