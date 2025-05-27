package com.example.service;

import com.example.domain.Team;
import com.example.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * チーム情報を操作するクラス.
 */
@Service
@Transactional
public class TeamService {
    @Autowired
    private TeamRepository repository;

    /**
     * チーム一覧を取得する.
     *
     * @return チーム情報のリスト
     */
    public List<Team> getAllTeam(){
        return  repository.findAll();
    }

    /**
     * idによってチーム情報を検索する.
     *
     * @param id チームid
     * @return チーム情報
     */
    public Team getTeamById(int id){
        return repository.findById(id);
    }

}
