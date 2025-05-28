package com.example.repository;

import com.example.domain.Article;
import com.example.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * articlesテーブルを操作するリポジトリ.
 */
@Repository
public class ArticleRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    public static RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, rowNum) -> {
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
        return articleList;
    }

    /**
     * すべての記事とコメントをDBから取得する.
     *
     * @return すべての記事
     */
    public List<Article> findAllArticleAndComment() {
        final ResultSetExtractor<List<Article>> ARTICLE_RESULT_SET = (rs) -> {
            List<Article> articleList = new ArrayList<>();
            List<Comment> commentList = null;
            Integer beforeArticleId = 0;

            while (rs.next()) {
                int nowArticleId = rs.getInt("id");

                //1つ前の行の記事とは異なる場合
                if (nowArticleId != beforeArticleId) {
                    commentList = new ArrayList<>();
                    Article article = new Article();
                    article.setId(rs.getInt("id"));
                    article.setName(rs.getString("name"));
                    article.setContent(rs.getString("content"));
                    article.setCommentList(commentList);
                    articleList.add(article);
                }

                Comment comment = new Comment();
                comment.setId(rs.getInt("com_id"));
                if(rs.wasNull() == false){
                    comment.setName(rs.getString("com_name"));
                    comment.setContent(rs.getString("com_content"));
                    comment.setArticleId(rs.getInt("article_id"));
                    commentList.add(comment);
                }
                beforeArticleId = nowArticleId;
            }
            return articleList;
        };

        String sql = """
                SELECT a.id AS id, a.name AS name, a.content AS content, c.id AS com_id, 
                c.name AS com_name, c.content AS com_content, c.article_id AS article_id 
                FROM articles AS a
                LEFT OUTER JOIN comments AS c
                ON a.id = c.article_id
                ORDER BY a.id, c.id;
                """;
        List<Article> articleList = template.query(sql, ARTICLE_RESULT_SET);
        return articleList;
    }


    /**
     * DBに新しい記事を登録する.
     *
     * @param article 登録する記事
     */
    public void insert(Article article) {
        String sql = """
                INSERT INTO articles (name, content) 
                VALUES (:name, :content);
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
    public void deleteById(Integer id) {
        String sql = """
                DELETE FROM articles WHERE id = :id;
                """;
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);
        template.update(sql, param);
    }
}
