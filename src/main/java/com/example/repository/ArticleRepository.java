package com.example.repository;

import com.example.domain.ArticleDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * articlesテーブルを操作するリポジトリ.
 */
@Repository
public class ArticleRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    public static RowMapper<ArticleDomain> ARTICLE_ROW_MAPPER = (rs, rowNum) ->{
        ArticleDomain article = new ArticleDomain();
        article.setId(rs.getInt("id"));
        article.setName(rs.getString("name"));
        article.setContent(rs.getString("content"));
        return article;
    };

    public List<ArticleDomain> findAll(){
        String sql = """
                SELECT id, name, content FROM articles;
                """;
        List<ArticleDomain> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
        return  articleList;
    }
}
