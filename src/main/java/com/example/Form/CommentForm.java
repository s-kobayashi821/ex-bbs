package com.example.Form;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * コメントのフォームクラス.
 */
public class CommentForm {
    /**名前*/
    @NotBlank(message = "投稿者名を入力してください")
    @Length(max=50, message = "投稿者名が長すぎます")
    private String name;

    /**コメント内容x*/
    @NotBlank(message = "コメントを入力してください")
    @Length(max=200, message = "コメントが長すぎます")
    private String content;

    /**投稿ID*/
    private Integer articleId;

    @Override
    public String toString() {
        return "Comment{" +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", article_id=" + articleId +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
