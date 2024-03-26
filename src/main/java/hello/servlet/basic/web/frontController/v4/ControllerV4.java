package hello.servlet.basic.web.frontController.v4;

import java.util.Map;

public interface ControllerV4 {

    //슬레쉬 별별 엔터
    /**
     * 
     * @param paramMap
     * @param model
     * @return
     */
    String process(Map<String , String> paramMap, Map<String,Object> model);
}
