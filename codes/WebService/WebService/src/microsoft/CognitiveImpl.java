package microsoft;
/**
 * Created by Victor on 2017/5/4.
 */

import java.io.File;
import java.net.URI;


import entity.Adult;
import entity.AdultReturn;
import entity.AnalyzeReturn;
import entity.Description;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


/**
 * 这个类主要是微软认知服务中的计算机视觉
 *
 * @author Victor
 */
@WebService(name = "CognitiveService")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public class CognitiveImpl implements CognitiveService {
    private final String KEY = "c260a50f89de456db22a5c97406ffb6d";

    // 图像分析的链接
    private final String ANALYZE_IMAGE = "https://api.projectoxford.ai/vision/v1.0/analyze";


    private HttpClient httpclient = HttpClients.createDefault();

    @Override
    public Description depictPicture(String filePath) {
        JSONObject jsonObject = analyzeImage(filePath, "Description");
        System.out.println(jsonObject);

        AnalyzeReturn analyzeReturn = (AnalyzeReturn) JSONObject.toBean(jsonObject, AnalyzeReturn.class);

        return analyzeReturn.getDescription();
    }

    @Override
    public Adult adultJudge(String filePath){

        JSONObject jsonObject = analyzeImage(filePath, "Adult");
        System.out.println(jsonObject);
        System.out.println();

        AdultReturn adultReturn = (AdultReturn) JSONObject.toBean(jsonObject, AdultReturn.class);

        return adultReturn.getAdult();
    }


    /**
     * 分析图片的接口
     *
     * @param filePath
     */
    private JSONObject analyzeImage(String filePath, String value) {
        try {
            URIBuilder builder = new URIBuilder(ANALYZE_IMAGE);


// 为链接配置的一些参数
/*
* 图像特征点： Categories:种类 ImageType:图片类型 Faces:人脸描述 Adult：监测成人内容
* Color:颜色 Tags:标签 Description:用一句完成的英文描述图片
*/
            builder.setParameter("visualFeatures", value);
/*
* 对一些特定领域的细节的返回
*
* 目前只支持“名人”识别:Celebrities
*/
// builder.setParameter("details", "{string}");
/*
* 以哪种语言返回，默认为英语 en 英文 zh 中文
*/
            builder.setParameter("language", "en");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
/**
 * 请求类型 json application/json 图片以url方式发送 二进制流
 * application/octet-stream 图片以二进制流发送 多文件发送 multipart/form-data
 * 图片以二进制流进行发送
 */
// request.setHeader("Content-Type", "application/json");
// 以json方式访问，图片路径需要为一个能访问到的地址
            request.setHeader("Content-Type", "application/octet-stream"); // 以位进制文件流的方式访问，图片需要转化成一个二进制的流


// 还有一种请求方式multipart/form-data


/*
* 请求API的秘钥（需要去微软官方申请）
*/
            request.setHeader("Ocp-Apim-Subscription-Key", KEY);


            File file = new File(filePath);


            if (!file.exists()) {
                System.out.println("图片不存在");
                return null;
            }


// Request body
// 以json方式访问时传递的是一个json字符串
// StringEntity reqEntity = new StringEntity("{\"Url\":
// \""+filePath+"\"}");


// 以文件流方式访问时传递的是一个文件（最终会以位二进制进行请求）
            FileEntity reqEntity = new FileEntity(file);
            request.setEntity(reqEntity);


            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();


            if (entity != null) {
                String result = EntityUtils.toString(entity);

//                System.out.println(result);

                return JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }



}

