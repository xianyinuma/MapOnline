package com.sota.controller;

import com.google.gson.Gson;

import com.sota.entity.Friend;
import com.sota.entity.Image;
import com.sota.entity.Tag;
import com.sota.entity.User;
import com.sota.message.*;
import com.sota.service.FriendService;
import com.sota.service.ImageService;
import com.sota.service.TagService;
import com.sota.service.UserService;
import entity.Description;
import microsoft.CognitiveService;
import moji.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Song on 2017/2/15.
 * User控制层
 */
@Controller
@RequestMapping(value = "/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "index")
    public String index(){
        return "user/index";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public String registerShow(@RequestParam(value = "username")String username,
                           @RequestParam(value = "password")String password,
                           HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        User user = userService.findUserByName(username);
        RegisterResponse registerResponse = new RegisterResponse();
        if ( null == user) {
            registerResponse.setRegisterResult(true);
            user = new User();
            user.setName(username);
            user.setPassword(password);
            userService.saveUser(user);
            registerResponse.setUserID(user.getId());
        }
        else {
            registerResponse.setRegisterResult(false);
        }
        return getJsonData(registerResponse);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String loginShow(@RequestParam(value = "username")String username,
                            @RequestParam(value = "password")String password,
            HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        //return
        LoginResponse loginResponse = new LoginResponse();
        boolean login_state = loginCheck(username, password);
        loginResponse.setLoginResult(login_state);
        if (login_state) {
            User user = userService.findUserByName(username);
            loginResponse.setUserID(user.getId());
            loginResponse.setFriendMessages(getFriendMessagesByUserID(user.getId()));
            loginResponse.setImageMessages(getImageMessagesByUserID(user.getId()));
        }
        //json data return
        return getJsonData(loginResponse);
    }


    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadShow(@RequestParam(value = "username")String username,
                             @RequestParam(value = "password")String password,
                             @RequestParam(value = "base64Coding")String base64Coding,
                             @RequestParam(value = "latitude")double latitude,
                             @RequestParam(value = "longitude")double longitude,
                             HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if(loginCheck(username, password)) {
            User tempUser = userService.findUserByName(username);
            StringBuilder stringBuilder = new StringBuilder(base64Coding);
            //deal with the ' ', replace with '+'
            for (int i = 0 ; i < base64Coding.length(); i++) {
                if (stringBuilder.charAt(i) == ' ') {
                    stringBuilder.setCharAt(i, '+');
                }
            }
            base64Coding = stringBuilder.toString();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
            String currentDateString = format.format(new Date());
            System.out.println(currentDateString);
            String tempName = username + "-" + currentDateString + ".jpg";
            String tempUrl = "src/main/resources/images/" + tempName;
            Base64String.base64ToImage(base64Coding, tempUrl);
            String absolutePath = new File(tempUrl).getAbsolutePath();
            System.out.println(absolutePath);
            Image newImage = new Image();
            newImage.setUserID(tempUser.getId());
            newImage.setLongitude(longitude);
            newImage.setLatitude(latitude);
            newImage.setTitle(tempName);
            newImage.setImageUrl(tempUrl);
            newImage = imageService.saveImage(newImage);

            //request message from WS;
            UploadResponse uploadResponse = getUploadResponse(newImage);
            return getJsonData(uploadResponse);
//            response.setStatus(200);
//            response.getWriter().write(getJsonData(uploadResponse));
        }
        return "upload fail";
    }


    @RequestMapping(value = "edit", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String editShow(@RequestBody EditResponse editResponse, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println(editResponse.getUsername());

        if (loginCheck(editResponse.getUsername(), editResponse.getPassword())) {
            //login success
            Image editedImage = new Image();
            Image preImage = imageService.findById(editResponse.getUploadResponse().getImageID());
            editedImage.setId(preImage.getId());
            editedImage.setUserID(userService.findUserByName(editResponse.getUsername()).getId());
            editedImage.setImageDescription(editResponse.getUploadResponse().getDescription());
            editedImage.setLongitude(preImage.getLongitude());
            editedImage.setLatitude(preImage.getLatitude());
            editedImage.setImageUrl(preImage.getImageUrl());
            editedImage.setTitle(editResponse.getUploadResponse().getTitle());
            imageService.saveImage(editedImage);

            for (int i = 0; i < editResponse.getUploadResponse().getTags().length; i++) {
                Tag tag = new Tag();
                tag.setImageID(editedImage.getId());
                tag.setTagContent(editResponse.getUploadResponse().getTags()[i]);
                tagService.saveTag(tag);
            }
            return "edition succeed";
        } else {
            return "edition failed";
        }
    }


    @RequestMapping(value = "view-friend", method = RequestMethod.POST)
    @ResponseBody
    public String friendImage(@RequestParam(value = "username")String username,
                              @RequestParam(value = "password")String password,
                              @RequestParam(value = "friendID")int friendID,
                              HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
            if (loginCheck(username, password)) {
                User currentUser = userService.findUserByName(username);
                Friend[] friends = friendService.findAllByUserID(currentUser.getId());
                boolean isFriend = false;
                for (int i = 0; i < friends.length; i++) {
                    if (friends[i].getUserID2() == friendID) {
                        isFriend = true;
                        break;
                    }
                }
                if (isFriend) {
                    FriendImage friendImage = new FriendImage();
                    friendImage.setUserID(friendID);
                    friendImage.setUsername(userService.findByUserID(friendID).getName());
                    friendImage.setImageMessages(getImageMessagesByUserID(friendID));
                    return getJsonData(friendImage);
                } else {
                    return "illegal access";
                }
            } else {
                return "illegal access";
            }
    }

    @RequestMapping(value = "delete-image", method = RequestMethod.POST)
    @ResponseBody
    public String deleteImage(@RequestParam(value = "username")String username,
                              @RequestParam(value = "password")String password,
                              @RequestParam(value = "imageID")int imageID,
                              HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (loginCheck(username, password)) {
            Image image = imageService.findById(imageID);
            if ((image != null) &&  (image.getUserID() == userService.findUserByName(username).getId())) {
                //delete all related tags
                Tag[] tags = tagService.findAllByImageID(imageID);
                if (tags != null) {
                    for (int i = 0; i < tags.length; i++) {
                        tagService.deleteTag(tags[i].getId());
                    }
                }
                File file = new File(image.getImageUrl());
                file.delete();
                imageService.deleteImage(imageID);
                //login success, delete image
                return "delete successfully";
            }
        }

        return "illegal access";
    }


    @RequestMapping(value = "follow-friend", method = RequestMethod.POST)
    @ResponseBody
    public String followFriend(@RequestParam(value = "username")String username,
                              @RequestParam(value = "password")String password,
                              @RequestParam(value = "friendUsername")String friendUsername,
                              HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (loginCheck(username, password)) {
            User user = userService.findUserByName(friendUsername);
            int currentID = userService.findUserByName(username).getId();
            if (user != null && !user.getName().equals(username)) {
                // test is followed
                Friend[] friends = friendService.findAllByUserID(currentID);
                boolean isFollowed = false;
                if (friends != null) {
                    for (int i = 0; i < friends.length; i++) {
                        if (userService.findByUserID(friends[i].getUserID2()).getName().equals(friendUsername)) {
                            isFollowed = true;
                            break;
                        }
                    }
                }
                if (isFollowed) {
                    // had followed
                    return "follow failed";
                }
                else {
                    Friend friend = new Friend();
                    friend.setUserID1(currentID);
                    friend.setUserID2(user.getId());
                    friendService.saveFriend(friend);
                    return "" + user.getId();
                }
            }
        }
        return "follow failed";
    }

    @RequestMapping(value = "delete-friend", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFriend(@RequestParam(value = "username")String username,
                              @RequestParam(value = "password")String password,
                              @RequestParam(value = "friendID")int friendID,
                              HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (loginCheck(username, password)) {
            User currentUser = userService.findUserByName(username);
            Friend[] friends = friendService.findAllByUserID(currentUser.getId());
            boolean isDeleted = false;
            if (friends != null) {
                for (int i = 0; i < friends.length; i++) {
                    if (friends[i].getUserID2() == friendID) {
                        //delete
                        friendService.deleteFriend(friends[i]);
                        isDeleted = true;
                        return "delete successfully";
                    }
                }
            }
        }
        return "delete failed";
    }


    private  boolean loginCheck(String username, String password) {
        User user = userService.findUserByName(username);
        if(null != user && user.getPassword().equals(password)) {
            System.out.println("login ok");
            return true;
        }
        else {
            System.out.println("login error");
            return false;
        }
    }

    private FriendMessage[] getFriendMessagesByUserID(int userID) {
        Friend[] friends = friendService.findAllByUserID(userID);
        FriendMessage[] friendMessages = null;
        if (friends != null) {
            friendMessages = new FriendMessage[friends.length];
            for (int i = 0; i < friends.length; i++) {
                friendMessages[i] = new FriendMessage();
                int tempUserID = friends[i].getUserID2();
                friendMessages[i].setUserID(tempUserID);
                User tempUser = userService.findByUserID(tempUserID);
                friendMessages[i].setName(tempUser.getName());
            }
        }
        return friendMessages;
    }

    private ImageMessage[] getImageMessagesByUserID(int userID) {
        Image[] images = imageService.findAllByUserID(userID);
        ImageMessage[] imageMessages = null;
        if (null != images) {
            imageMessages = new ImageMessage[images.length];
            // query all images
            for (int i = 0; i < images.length; i++) {
                imageMessages[i] = new ImageMessage();
                imageMessages[i].setImageID(images[i].getId());
                imageMessages[i].setTitle(images[i].getTitle());
                imageMessages[i].setDescription(images[i].getImageDescription());
                imageMessages[i].setBase64Coding(Base64String.imageToBase64(images[i].getImageUrl()));
                Tag[] tags = tagService.findAllByImageID(images[i].getId());
                if (null != tags) {
//                    System.out.println(tags.length);
                    imageMessages[i].setTags(tags);
                }
                imageMessages[i].setLatitude(images[i].getLatitude());
                imageMessages[i].setLongitude(images[i].getLongitude());
            }
        }
        return imageMessages;
    }

    private UploadResponse getUploadResponse(Image image) throws MalformedURLException {
        UploadResponse uploadResponse = new UploadResponse();
        uploadResponse.setImageID(image.getId());
        uploadResponse.setTitle(image.getTitle());
        //获取服务
        URL wsdlUrl = new URL("http://localhost:2333/CognitiveService?wsdl");
        QName qName = new QName("http://microsoft/", "CognitiveImplService");
        Service service = Service.create(wsdlUrl, qName);
        CognitiveService cognitiveService = service.getPort(CognitiveService.class);

        String absolutePath = new File(image.getImageUrl()).getAbsolutePath();

        Description description = cognitiveService.depictPicture(absolutePath);
//        System.out.println(cognitiveService.depictPicture(absolutePath).getCaptions()[0].getText());
        uploadResponse.setDescription(description.getCaptions()[0].getText());
        uploadResponse.setTags(description.getTags());
//        System.out.println(cognitiveService.adultJudge(absolutePath).getIsAdultContent());
        uploadResponse.setAdultOrNot(cognitiveService.adultJudge(absolutePath).getIsAdultContent());

        URL wsdlUrl1 = new URL("http://localhost:2333/WeatherService?wsdl");
        QName qName1 = new QName("http://moji/", "WeatherImplService");
        Service service1 = Service.create(wsdlUrl1, qName1);
        WeatherService weatherService = service1.getPort(WeatherService.class);
        uploadResponse.setWeatherData(weatherService.showWeather(image.getLatitude(),image.getLongitude()));
//        System.out.println(weatherService.showWeather(image.getLatitude(),image.getLongitude()));

        return uploadResponse;
    }

    public static String getJsonData(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }

    public static Object getObjectFromJson(String jsonData) {
        Gson gson = new Gson();
        Object object = gson.fromJson(jsonData, Object.class);
        return object;
    }


    private void getFriendMessages(LoginResponse loginResponse) {
        Friend[] friends = friendService.findAllByUserID(loginResponse.getUserID());
        if (friends != null) {
            FriendMessage[] friendMessages = new FriendMessage[friends.length];
            for (int i = 0; i < friends.length; i++) {
                friendMessages[i] = new FriendMessage();
                int tempUserID = friends[i].getUserID2();
                friendMessages[i].setUserID(tempUserID);
                User tempUser = userService.findByUserID(tempUserID);
                friendMessages[i].setName(tempUser.getName());
            }
            loginResponse.setFriendMessages(friendMessages);
        }
    }
    private void getImageMessages(LoginResponse loginResponse) throws IOException {
        Image[] images = imageService.findAllByUserID(loginResponse.getUserID());
        ImageMessage[] imageMessages;
        if (null != images) {
            imageMessages = new ImageMessage[images.length];
            // query all images
            for (int i = 0; i < images.length; i++) {
                imageMessages[i] = new ImageMessage();
                imageMessages[i].setImageID(images[i].getId());
                imageMessages[i].setTitle(images[i].getTitle());
                imageMessages[i].setDescription(images[i].getImageDescription());
                imageMessages[i].setBase64Coding(Base64String.imageToBase64(images[i].getImageUrl()));
//                System.out.println(imageMessages[i].getBase64Coding());
//                Base64String.base64ToImage(imageMessages[i].getBase64Coding(), "src/main/resources/testout/test.jpg");
                //query all tags
                Tag[] tags = tagService.findAllByImageID(images[i].getId());
                if (null != tags) {
//                    System.out.println(tags.length);
                    imageMessages[i].setTags(tags);
                }
                imageMessages[i].setLatitude(images[i].getLatitude());
                imageMessages[i].setLongitude(images[i].getLongitude());
            }
            loginResponse.setImageMessages(imageMessages);
        }
    }
}
