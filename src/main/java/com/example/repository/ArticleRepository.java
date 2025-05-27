package com.example.repository;

import com.example.domain.Article;
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

    public static RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, rowNum) ->{
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setName(rs.getString("name"));
        article.setContent(rs.getString("content"));
        return article;
    };

    /**
     * すべての記事をDBから取得する.
     *
     * @return すべての記事
     */
    public List<Article> findAll() {
        String sql = """
                SELECT id, name, content FROM articles 
                ORDER BY id;
                """;
        List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
        return  articleList;
    }


    /**
     * DBに新しい記事を登録する.
     *
     * @param article 登録する記事
     */
    public void insert(Article article){
        String sql = """
                INSERT INTO articles name, content 
                VALUES (:name, :comment);
                """;
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", article.getName())
                .addValue("content", article.getContent());
        template.update(sql, param);
    }

    /**
     * idを指定して記事を削除する.
     *
     * @param id 削除する記事のid
     */
    public void deleteById(Integer id){
        String sql = """
                DELETE FROM articles WHERE id = :id;
                """;
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);
        template.update(sql, param);
    }
}
