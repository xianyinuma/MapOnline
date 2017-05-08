package com.sota.repository;

import com.sota.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by wenjin on 2017/5/8.
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query("select t from Tag t where t.imageID = :imageID")
    Tag[] findAllByImageID(@Param("imageID") int imageID);
}
