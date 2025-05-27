package com.example.domain;

/**
 * コメントを表すドメイン.
 */
public class CommentDomain {
    /**コメントID*/
    private Integer id;

    /**名前*/
    private String name;

    /**コメント内容x*/
    private String content;

    /**投稿ID*/
    private Integer article_id;

    @Override
    public String toString() {
        return "CommentDomain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", article_id=" + article_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }
}
