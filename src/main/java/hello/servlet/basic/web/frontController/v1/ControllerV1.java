package hello.servlet.basic.web.frontController.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {
    
    //여러 컨트롤러를 만드는 뼈대이기 대문에 Inferface를 생성한다
    
    void process(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException;


    
}
