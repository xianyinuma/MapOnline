package com.sota.service;

import com.sota.entity.Image;
import com.sota.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wenjin on 2017/5/8.
 */
@Service
@Transactional()
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

    public Image saveImage(Image image) {
        Image image0 = imageRepository.save(image);
        return image0;
    }

    public Image findById(int id) {
        Image image = imageRepository.findOne(id);
        return image;
    }

}
