package com.example.practice.controller;

import com.example.practice.dto.ResponseDTO;
import com.example.practice.dto.SomeItemDTO;
import com.example.practice.service.SomeItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final SomeItemService someItemService;

    @GetMapping("/")
    public ModelAndView main() {
        List<SomeItemDTO> itemList = someItemService.getList().getData();
        ModelAndView mv = new ModelAndView("index");

        mv.addObject("itemList", itemList);
        return mv;
    }
}
