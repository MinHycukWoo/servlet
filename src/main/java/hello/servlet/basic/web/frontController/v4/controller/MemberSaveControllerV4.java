package hello.servlet.basic.web.frontController.v4.controller;

import hello.servlet.basic.web.frontController.ModelView;
import hello.servlet.basic.web.frontController.v3.ControllerV3;
import hello.servlet.basic.web.frontController.v4.ControllerV4;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    /*@Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }*/

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);


        //model을 따로 반환하지 않아도 frontControler 의 model에 매개변수이기 때문에
        //값을 넣어주면 frontController에서도 반영되게 된다.
        model.put("member" , member);
        return "save-result";
    }
}
