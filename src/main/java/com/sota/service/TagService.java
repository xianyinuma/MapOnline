package com.sota.service;

import com.sota.entity.Tag;
import com.sota.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wenjin on 2017/5/8.
 */
@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public Tag[] findAllByImageID(int imageID) {
        Tag[] tags = null;
        try {
            tags = tagRepository.findAllByImageID(imageID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tags;
    }
}
