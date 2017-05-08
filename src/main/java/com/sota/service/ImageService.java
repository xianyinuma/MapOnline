package com.sota.service;

import com.sota.entity.Image;
import com.sota.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wenjin on 2017/5/8.
 */
@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Image[] findAllByUserID(int userID) {
        Image[] images = null;
        try {
            images = imageRepository.findAllByUserID(userID);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }
}
