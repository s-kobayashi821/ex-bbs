package com.example.domain;

/**
 * 記事を表すドメイン.
 */
public class Article {
    /**投稿ID*/
    private Integer id;

    /**名前*/
    private String name;

    /**記事内容x*/
    private String content;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
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
}
