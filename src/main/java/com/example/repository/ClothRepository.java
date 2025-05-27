package com.example.repository;

import com.example.domain.Cloth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * clothesテーブルを操作するリポジトリ.
 *
 */
@Repository
public class ClothRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    public static RowMapper<Cloth> CLOTH_ROW_MAPPER = (rs, rowNum) ->{
        Cloth cloth = new Cloth();
        cloth.setId(rs.getInt("id"));
        cloth.setCategory(rs.getString("category"));
        cloth.setGenre(rs.getString("genre"));
        cloth.setGender(rs.getInt("gender"));
        cloth.setColor(rs.getString("color"));
        cloth.setPrice(rs.getInt("price"));
        cloth.setSize(rs.getString("size"));
        return cloth;
    };


    /**
     * 条件に合う服の情報を検索する.
     *
     * @return 条件に合う服の一覧
     */
    public List<Cloth> findByGenderColor(final int gender, final String color){
        String sql = "SELECT id, category, genre, gender, color, price, size " +
                "FROM clothes WHERE gender=:gender AND color=:color ORDER BY id;";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("gender", gender).addValue("color", color);
        List<Cloth> clothList = template.query(sql,param,  CLOTH_ROW_MAPPER);
        return  clothList;
    }

    /**
     * すべての服の情報を検索する.
     *
     * @return すべての服の一覧
     */
    public List<Cloth> findAll(){
        String sql = "SELECT id, category, genre, gender, color, price, size " +
                "FROM clothes ORDER BY id;";
        List<Cloth> clothList = template.query(sql, CLOTH_ROW_MAPPER);
        return  clothList;
    }


}
