package com.sota.controller;

import com.google.gson.Gson;
import com.sota.entity.Friend;
import com.sota.entity.Image;
import com.sota.entity.Tag;
import com.sota.entity.User;
import com.sota.message.FriendMesaage;
import com.sota.message.ImageMessage;
import com.sota.message.LoginResponse;
import com.sota.service.FriendService;
import com.sota.service.ImageService;
import com.sota.service.TagService;
import com.sota.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Song on 2017/2/15.
 * User控制层
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/index")
    public String index(){
        return "user/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String loginShow(@RequestParam(value = "username")String username, @RequestParam(value = "password")String password) {
//        System.out.println(username + "   " + password);
//        LoginResponse loginResponse = new LoginResponse(username, password);
//        return loginResponse.getLoginResult();

        //return
        String login_state;
        User user = userService.findUserByName(username);
        LoginResponse loginResponse = new LoginResponse();
        if(null != user && password.equals(user.getPassword())) {
            login_state = "true";
            loginResponse.setLoginResult("true");
            loginResponse.setUserID(user.getId());

            Friend[] friends = friendService.findAllByUserID(user.getId());
            FriendMesaage[] friendMesaages;
            if(null != friends) {
                friendMesaages = new FriendMesaage[friends.length];
                System.out.println(friends.length);
                //query all friends' name
                for (int i = 0; i < friends.length; i++) {
                    friendMesaages[i] = new FriendMesaage();
                    int tempUserID = friends[i].getUserID2();
//                    System.out.println(tempUserID);
                    friendMesaages[i].setUserID(tempUserID);
                    User tempUser = userService.findByUserID(tempUserID);
//                    System.out.println(tempUser.getName());
                    friendMesaages[i].setName(tempUser.getName());
                }
                loginResponse.setFriendMesaages(friendMesaages);
            }

            Image[] images = imageService.findAllByUserID(user.getId());
            ImageMessage[] imageMessages;
            if (null != images) {
                imageMessages = new ImageMessage[images.length];
                System.out.println(images.length);
                // query all images
                for (int i = 0; i < images.length; i++) {
                    imageMessages[i] = new ImageMessage();
                    imageMessages[i].setTitle(images[i].getTitle());
                    System.out.println(images[i].getTitle());
                    imageMessages[i].setDescription(images[i].getImageDescription());
                    imageMessages[i].setLatitude(images[i].getLatitude());
                    imageMessages[i].setLongitude(images[i].getLongitude());
                    //get image content todo
                    //query all tags
                    Tag[] tags = tagService.findAllByImageID(images[i].getId());
                    if (null != tags) {
                        System.out.println(tags.length);
                        imageMessages[i].setTags(tags);
                    }
                }
                loginResponse.setImageMessages(imageMessages);
            }

        }
        else {
            login_state = "false";
            loginResponse.setLoginResult(login_state);
        }

        Gson gson = new Gson();
        String json = gson.toJson(loginResponse);
        System.out.println(json);
        return login_state;
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadShow(@RequestParam(value = "username")String username, @RequestParam(value = "password")String password) {
        return "upload";
    }
}
