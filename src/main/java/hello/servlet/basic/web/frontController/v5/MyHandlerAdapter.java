package hello.servlet.basic.web.frontController.v5;

import hello.servlet.basic.web.frontController.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    boolean supports(Object handler);

    ModelView handle(HttpServletRequest request , HttpServletResponse response , Object handler) throws ServletException , IOException;

}
