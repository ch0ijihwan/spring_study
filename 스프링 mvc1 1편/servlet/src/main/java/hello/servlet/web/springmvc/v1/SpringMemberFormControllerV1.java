package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Component // -> 핸들러로 인식 가능
//@RequestMapping // ->  컨트롤러를 안해도 이거 두개만 하면 인식 가능 -> 뭐로? 핸들러로 인식 가능
@Controller
public class SpringMemberFormControllerV1 {
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
