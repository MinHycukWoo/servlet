package hello.servlet.basic.web.frontController.v3.controller;

import hello.servlet.basic.web.frontController.ModelView;
import hello.servlet.basic.web.frontController.v3.ControllerV3;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3  implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();
        ModelView mv = new ModelView("members"); //경로
        mv.getModel().put("members" , members);

        return mv;
    }
}
