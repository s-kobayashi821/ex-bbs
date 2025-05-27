package com.example.Form;

/**
 * コメントのフォームクラス.
 */
public class CommentForm {
    /**名前*/
    private String name;

    /**コメント内容x*/
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
