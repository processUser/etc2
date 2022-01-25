package com.koreait.ap;

import com.koreait.ap.model.SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ApController {

    @Autowired private ApService service;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("location", service.selLocation());
        return "main";
    }

    @PostMapping("/result")
    public String result(SearchDto dto, RedirectAttributes reAttr) {

        reAttr.addFlashAttribute("apart", service.selApartmentInfo(dto));
        return "redirect:/";
    }
}
