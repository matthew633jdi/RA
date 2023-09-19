package com.matthew633jdi.RA.web;

import com.matthew633jdi.RA.service.problem.ProblemService;
import com.matthew633jdi.RA.service.user.UserService;
import com.matthew633jdi.RA.web.dto.problem.ProblemResponseDto;
import com.matthew633jdi.RA.web.dto.user.UserResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final ProblemService problemService;
    private final UserService userService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String home(Model model) {
        UserResponseDto user = (UserResponseDto) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
            user = userService.findById(user.getId());
            if (!CollectionUtils.isEmpty(user.getProblems())) {
                List<ProblemResponseDto> problems = user.getProblems();
                model.addAttribute("problems", problems);
            }
        }

        return "index";
    }

    @GetMapping("/logout")
    public String logout() {
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/problems/save")
    public String problemsSave() {
        return "problems-save";
    }

    @GetMapping("/problems/update/{id}")
    public String problemsUpdate(@PathVariable Long id, Model model) {
        ProblemResponseDto dto = problemService.getProblemById(id);
        model.addAttribute("problem", dto);
        return "problems-update";
    }

    @GetMapping("/login")
    public String login() {
        return "user-login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "user-signup";
    }

}
