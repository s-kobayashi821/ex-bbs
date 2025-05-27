package com.example.controller;

import com.example.domain.Cloth;
import com.example.service.ClothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 服情報を管理するコントローラ.
 */
@Controller
@RequestMapping("/ex03")
public class ClothController {
    @Autowired
    ClothService service;

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

    /**
     * 条件に合う服を検索する.
     *
     * @param gender 性別の入力
     * @param color 色の入力
     * @param model　モデル
     * @return　条件に合う服の一覧を表示
     */
    @PostMapping("")
    public String showList(Integer gender, String color , Model model) {
        List<String> colorList = service.getColorList();
        model.addAttribute("colorList", colorList);
        model.addAttribute("initGender", gender);
        model.addAttribute("initColor", color);
        List<Cloth> clothList = service.getClothByGenderColor(gender, color);

        if(clothList.size() == 0){
            model.addAttribute("msg", "検索結果が0件でした");
            return "clothList";
        }

        model.addAttribute("clothList",clothList);
        return "clothList";
    }
}
