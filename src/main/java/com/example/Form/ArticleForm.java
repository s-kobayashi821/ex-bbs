package com.example.Form;

/**
 * 記事のフォームクラス.
 */
public class ArticleForm {
    /**名前*/
    private String name;

    /**記事内容x*/
    private String content;

    @Override
    public String toString() {
        return "ArticleDomain{" +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
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
}
