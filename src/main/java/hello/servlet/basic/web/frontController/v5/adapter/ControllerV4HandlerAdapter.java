package hello.servlet.basic.web.frontController.v5.adapter;

import hello.servlet.basic.web.frontController.ModelView;
import hello.servlet.basic.web.frontController.v4.ControllerV4;
import hello.servlet.basic.web.frontController.v5.MyHandlerAdapter;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        System.out.println("handler+++  " + handler);
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String , String> paramMap = createParamMap(request);
        HashMap<String , Object> model = new HashMap<>();

        String viewName = controller.process(paramMap , model);
        // 반환타입이 맞지않다.
        //어뎁터가 modelView가 변환해 주어야한다.
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);
        return mv;
    }

    private Map<String , String> createParamMap(HttpServletRequest request){
        Map<String , String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }
}
