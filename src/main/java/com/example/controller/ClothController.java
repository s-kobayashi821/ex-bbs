package com.example.controller;

import com.example.service.BbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 記事とコメントを管理するコントローラ.
 */
@Controller
@RequestMapping("")
public class ClothController {
    @Autowired
    BbsService service;

    /**
     * 検索ページを表示する.
     *
     * @param model
     * @return
     */
    @GetMapping("")
    public String index(Model model) {
        List<String> colorList = service.getColorList();
        model.addAttribute("colorList", colorList);
        model.addAttribute("initGender", 0);
        model.addAttribute("initColor", colorList.get(0));
        return "clothList";
    }

}
