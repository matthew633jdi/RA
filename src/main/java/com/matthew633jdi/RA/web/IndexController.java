package com.matthew633jdi.RA.web;

import com.matthew633jdi.RA.service.problem.ProblemService;
import com.matthew633jdi.RA.service.user.UserService;
import com.matthew633jdi.RA.web.dto.problem.ProblemRequestDto;
import com.matthew633jdi.RA.web.dto.problem.ProblemResponseDto;
import com.matthew633jdi.RA.web.dto.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final ProblemService problemService;
    private final UserService userService;

    @GetMapping("/")
    public String home(Model model, @SessionAttribute(name = "userId", required = false) Long userId) {
        if (userId == null) {
            List<ProblemResponseDto> responseDtos = problemService.findProblem(new ProblemRequestDto());
            model.addAttribute("problems", responseDtos);
        } else {
            UserResponseDto user = userService.findById(userId);
            if (user != null) {
                model.addAttribute("userName", user.getName());
            }

            if (!CollectionUtils.isEmpty(user.getProblems())) {
                model.addAttribute("problems", user.getProblems());
            }
        }

        return "index";
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
