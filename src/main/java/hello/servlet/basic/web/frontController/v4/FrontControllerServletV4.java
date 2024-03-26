package hello.servlet.basic.web.frontController.v4;

import hello.servlet.basic.web.frontController.ModelView;
import hello.servlet.basic.web.frontController.MyView;
import hello.servlet.basic.web.frontController.v3.ControllerV3;
import hello.servlet.basic.web.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.basic.web.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.basic.web.frontController.v3.controller.MemberSaveControllerV3;
import hello.servlet.basic.web.frontController.v4.controller.MemberFormControllerV4;
import hello.servlet.basic.web.frontController.v4.controller.MemberListControllerV4;
import hello.servlet.basic.web.frontController.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4" , urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String , ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form" , new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save" , new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members" , new MemberListControllerV4());

    }

    @Override
    protected void service (HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);

        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //param Map
        Map<String , String> paramMap = createParamMap(request, response);
        Map<String , Object> model = new HashMap<>();
        //ModelView mv = controller.process(paramMap);
        String viewName = controller.process(paramMap , model);
        System.out.println("model.get(\"member\")" + model.get("member"));

        //논리 이름 물리 이름으로 바꾸기
        //String viewName = mv.getViewName();
        MyView view =  new MyView("/WEB-INF/views/" + viewName + ".jsp");;

        view.render(model, request,response);
    }

    private Map<String , String> createParamMap (HttpServletRequest request , HttpServletResponse response){
        Map<String , String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }

}
