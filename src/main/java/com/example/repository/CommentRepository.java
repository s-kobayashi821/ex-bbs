package com.example.repository;

import com.example.domain.Article;
import com.example.domain.Comment;
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

    public static RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, rowNum) -> {
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setName(rs.getString("name"));
        comment.setContent(rs.getString("content"));
        comment.setArticleId(rs.getInt("article_id"));
        return comment;
    };

    /**
     * articleIdによってコメントを検索する.
     *
     * @param articleId 記事のid
     * @return
     */
    public List<Comment> findByArticleId(Integer articleId) {
        String sql = """
                SELECT id, name, content, article_id 
                FROM comments
                WHERE article_id = :articleId 
                ORDER BY id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
        List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
        return commentList;
    }

    /**
     * 新しいコメントをDBに追加する.
     *
     * @param comment コメント
     */
    public void insert(Comment comment) {
        String sql = """
                INSERT INTO comments (name, content, article_id) 
                VALUES (:name, :content, :articleId);
                """;
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", comment.getName())
                .addValue("content", comment.getContent())
                .addValue("articleId", comment.getArticleId());
        template.update(sql, param);
    }

    /**
     * articleIdを指定してDBからコメントを削除する.
     * @param articleId　記事のID
     */
    public void deleteByArticleId(int articleId) {
        String sql = """
                DELETE FROM comments WHERE article_id=:articleId;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
        template.update(sql, param);
    }
}
