package hello.servlet.basic.web.frontController.v2;

import hello.servlet.basic.web.frontController.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    MyView process(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException;


}
