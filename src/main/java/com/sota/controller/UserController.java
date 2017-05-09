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

    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public String loginShow(@RequestParam(value = "username")String username,
                            @RequestParam(value = "password")String password,
            HttpServletResponse response) throws IOException {
//        System.out.println(username + "   " + password);
//        LoginResponse loginResponse = new LoginResponse(username, password);
//        return loginResponse.getLoginResult();
        response.setHeader("Access-Control-Allow-Origin", "*");
        //return
        LoginResponse loginResponse = new LoginResponse();
        boolean login_state = loginCheck(username, password);
        loginResponse.setLoginResult(login_state);
        if (login_state) {
            User user = userService.findUserByName(username);
            loginResponse.setUserID(user.getId());
            getFriendMessages(loginResponse);
            getImageMessages(loginResponse);
        }
        //json data return
        return getJsonData(loginResponse);
    }


    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadShow(@RequestParam(value = "username")String username,
                             @RequestParam(value = "password")String password,
                             @RequestParam(value = "base64Str")String base64Str,
                             @RequestParam(value = "latitude")double latitude,
                             @RequestParam(value = "longitude")double longitude,
                             HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if(loginCheck(username, password)) {
            User tempUser = userService.findUserByName(username);
            StringBuilder stringBuilder = new StringBuilder(base64Str);
            //deal with the ' ', replace with '+'
            for (int i = 0 ; i < base64Str.length(); i++) {
                if (stringBuilder.charAt(i) == ' ') {
                    stringBuilder.setCharAt(i, '+');
                }
            }
            base64Str = stringBuilder.toString();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
            String currentDateString = format.format(new Date());
            System.out.println(currentDateString);
            String tempName = username + "-" + currentDateString + ".jpg";
            String tempUrl = "src/main/resources/images/" + tempName;
            Base64String.base64ToImage(base64Str, tempUrl);

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
        return "upload format error";
    }



    @RequestMapping(value = "register", method = RequestMethod.GET)
    @ResponseBody
    public Object testShow(@RequestParam(value = "testParams")String testParams, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        return testParams;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String editShow(@RequestBody EditResponse editResponse, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println(editResponse.getUsername());

        if (loginCheck(editResponse.getUsername(), editResponse.getPassword())) {
            //login success
//            System.out.println(editResponse.getUsername());
//            System.out.println(editResponse.getPassword());
//            System.out.println(uploadResponse.getTags());
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

//            System.out.println(editResponse.getUploadResponse().getTags().length);
            for (int i = 0; i < editResponse.getUploadResponse().getTags().length; i++) {
                Tag tag = new Tag();
                tag.setImageID(editedImage.getId());
                tag.setTagContent(editResponse.getUploadResponse().getTags()[i]);
                tagService.saveTag(tag);
            }
            return "edit success";
        } else {
            return "login error";
        }
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

    private void getFriendMessages(LoginResponse loginResponse) {
        Friend[] friends = friendService.findAllByUserID(loginResponse.getUserID());
        if (friends != null) {
            FriendMesaage[] friendMesaages = new FriendMesaage[friends.length];
            for (int i = 0; i < friends.length; i++) {
                friendMesaages[i] = new FriendMesaage();
                int tempUserID = friends[i].getUserID2();
                friendMesaages[i].setUserID(tempUserID);
                User tempUser = userService.findByUserID(tempUserID);
                friendMesaages[i].setName(tempUser.getName());
            }
            loginResponse.setFriendMesaages(friendMesaages);
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
}
