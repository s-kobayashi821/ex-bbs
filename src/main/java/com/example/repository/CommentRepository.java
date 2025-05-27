package com.example.repository;

import com.example.domain.ArticleDomain;
import com.example.domain.CommentDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * commentsテーブルを操作するリポジトリ.
 */
@Repository
public class CommentRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    public static RowMapper<CommentDomain> COMMENT_ROW_MAPPER = (rs, rowNum) ->{
        CommentDomain comment = new CommentDomain();
        comment.setId(rs.getInt("id"));
        comment.setName(rs.getString("name"));
        comment.setContent(rs.getString("content"));
        comment.setContent(rs.getString("article_id"));
        return comment;
    };


    public List<CommentDomain> findByArticleId(int articleId){
        String sql = """
                SELECT id, name, content, article_id 
                FROM comments
                WHERE article_id = articleId;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
        List<CommentDomain> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
        return  commentList;
    }
}
