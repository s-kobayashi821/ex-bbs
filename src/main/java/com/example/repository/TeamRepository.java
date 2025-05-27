package com.example.repository;

import com.example.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * チーム情報を管理するクラス.
 *
 */
@Repository
public class TeamRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    public static RowMapper<Team> TEAM_ROW_MAPPER = (rs, rowNum) ->{
        Team team = new Team();
        team.setId(rs.getInt("id"));
        team.setLeagueName(rs.getString("league_name"));
        team.setTeamName(rs.getString("team_name"));
        team.setHeadquarters(rs.getString("headquarters"));
        team.setInauguration(rs.getString("inauguration"));
        team.setHistory(rs.getString("history"));
        return team;
    };


    /**
     * チーム一覧を取得する.
     *
     * @return チーム一覧情報
     */
    public List<Team> findAll(){
        String sql = "SELECT id, league_name, team_name, headquarters, inauguration, history FROM teams ORDER BY inauguration;";
        List<Team> teamList = template.query(sql, TEAM_ROW_MAPPER);
        return  teamList;
    }


    /**
     * id によってチーム情報を検索する.
     *
     * @param id　チームid
     * @return チーム情報
     */
    public  Team findById(int id){
        String sql = "SELECT id, league_name, team_name, headquarters, inauguration, history FROM teams WHERE id=:id;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Team team = template.queryForObject(sql, param, TEAM_ROW_MAPPER);
        return  team;
    }
}
