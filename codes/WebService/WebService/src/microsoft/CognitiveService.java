package microsoft;

import entity.Adult;
import entity.Description;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author Victor
 * */

@WebService
public interface CognitiveService {
    @WebMethod public Description depictPicture(String filePath);
    @WebMethod public Adult adultJudge(String filePath);
}
