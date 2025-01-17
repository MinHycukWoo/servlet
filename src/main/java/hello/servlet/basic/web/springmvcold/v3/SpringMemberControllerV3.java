package hello.servlet.basic.web.springmvcold.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //@RequestMapping(value = "/new-form" , method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm(){
        return "new-form";
        //return new ModelAndView("new-form");
    }

    //@RequestMapping(method = RequestMethod.GET)
    @PostMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members"); //경로
        model.addAttribute("members" , members);
        return "members";
    }

    //@RequestMapping(value = "/save" , method = RequestMethod.POST)
    @GetMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
    ) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }
}