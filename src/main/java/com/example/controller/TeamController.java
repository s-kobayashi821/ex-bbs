package com.example.controller;

import com.example.domain.Team;
import com.example.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * チーム情報を管理するコントローラ.
 *
 */
@Controller
@RequestMapping("/ex01")
public class TeamController {

    @Autowired
    private TeamService service;

    /**
     *チーム一覧画面表示.
     *
     * @param model リクエストスコープ
     * @return チーム一覧画面
     */
    @GetMapping("")
    public  String teamList(Model model){
        List<Team> teamList = service.getAllTeam();
        model.addAttribute("teamList", teamList);
        return "teamlist";
    }

    /**
     * チーム詳細画面を表示.
     *
     * @param id チームid
     * @return チーム詳細画面
     */
    @GetMapping("/Detail")
    public String Detail(String id, Model model){
        Team team = service.getTeamById(Integer.valueOf(id));
        model.addAttribute("team", team);
        return "teamdetail";
    }
}
