package hello.servlet.basic.web.frontController.v2.controller;

import hello.servlet.basic.web.frontController.MyView;
import hello.servlet.basic.web.frontController.v2.ControllerV2;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        //request.getParameter의 반환 값은 항사 String이라 값을 변환해주어야 한다

        Member member = new Member(username ,age);
        memberRepository.save(member);

        //model 에 데이터를 보관한다.
        request.setAttribute("member" , member);

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
