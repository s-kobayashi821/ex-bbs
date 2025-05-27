package com.example.controller;

import com.example.domain.Hotel;
import com.example.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * ホテル検索を管理するコントローラ.
 */
@Controller
@RequestMapping("/ex02")
public class HotelController {

    @Autowired
    HotelService service;

    @GetMapping("")
    public String index(Model model){
        return  "hotelList";
    }

    /**
     *検索された結果を表示する.
     *
     * @param model
     * @return ホテル検索ページに遷移する
     */
    @PostMapping("")
    public String updateHotels(String inputPrice ,Model model){

        if(isOnlyNumber(inputPrice) == false){
                model.addAttribute("msg", "正しい入力ではない");
            return "hotelList";
        }

        Integer price = inputPrice.isEmpty()?null:Integer.valueOf(inputPrice);
        List<Hotel> hotelList = service.getHotelList(price);
        if(hotelList.size() == 0){
            model.addAttribute("msg", "検索結果が0件でした");
            return "hotelList";
        }

        model.addAttribute("inputPrice", inputPrice);
        model.addAttribute("hotelList", hotelList);
        return  "hotelList";
    }

    /**
     * 文字列が数字のみで構成されているか.
     *
     * @return 真偽値
     */
    private static boolean isOnlyNumber(String inputPrice){
        for(char ch: inputPrice.toCharArray()){
            if(Character.isDigit(ch) == false)
                return false;
        }
        return true;
    }
}
