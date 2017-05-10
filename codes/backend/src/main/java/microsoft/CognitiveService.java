package microsoft;

import entity.Adult;
import entity.Description;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Victor on 2017/5/5.
 */
@WebService
public interface CognitiveService {
    @WebMethod public Description depictPicture(String filePath);
    @WebMethod public Adult adultJudge(String filePath);
}
