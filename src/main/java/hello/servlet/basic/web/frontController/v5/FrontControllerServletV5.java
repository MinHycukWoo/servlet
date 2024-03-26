package hello.servlet.basic.web.frontController.v5;

import hello.servlet.basic.web.frontController.ModelView;
import hello.servlet.basic.web.frontController.MyView;
import hello.servlet.basic.web.frontController.v3.ControllerV3;
import hello.servlet.basic.web.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.basic.web.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.basic.web.frontController.v3.controller.MemberSaveControllerV3;
import hello.servlet.basic.web.frontController.v4.controller.MemberFormControllerV4;
import hello.servlet.basic.web.frontController.v4.controller.MemberListControllerV4;
import hello.servlet.basic.web.frontController.v4.controller.MemberSaveControllerV4;
import hello.servlet.basic.web.frontController.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.basic.web.frontController.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5" , urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //아무 컨트롤러나 다 받기 위해 Object를 받는다.
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapers();
    }

    private void initHandlerMappingMap(){
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form" , new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save" , new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members" , new MemberListControllerV3());
        //v4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form" , new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save" , new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members" , new MemberListControllerV4());
    }

    private void initHandlerAdapers(){
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //String requestURI = request.getRequestURI();
        //ControllerV3 controller = controllerMap.get(requestURI);
        //Object handler = handlerMappingMap.get(requestURI);

        Object handler = getHanler(request);

        if(handler == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        /*MyHandlerAdapter a;
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)){
                a = adapter;
            }
        }*/
        /*MyHandlerAdapter a;
        getHandlerAdapter(handler);*/
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView mv = adapter.handle(request, response , handler);
        //param Map
        //Map<String , String> paramMap = createParamMap(request, response);
        //ModelView mv = controller.process(paramMap);

        //논리 이름 물리 이름으로 바꾸기
        String viewName = mv.getViewName();
        MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");

        view.render(mv.getModel(), request,response);
    }

    private Object getHanler(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    //여기서 v3 가 오는지 v4가 오는지에 따라서 판단
    private MyHandlerAdapter getHandlerAdapter(Object handler){
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler Adapter를 찾을 수 없습니다. handler" + handler );
    }

}
