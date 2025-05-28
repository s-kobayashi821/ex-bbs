package com.example.Form;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * 記事のフォームクラス.
 */
public class ArticleForm {
    /**名前*/
    @NotBlank(message = "投稿者名を入力してください")
    @Length(max=50, message = "投稿者名が長すぎます")
    private String name;

    /**記事内容x*/
    @NotBlank(message = "投稿内容を入力してください")
    @Length(max=200, message = "投稿内容が長すぎます")
    private String content;

    @Override
    public String toString() {
        return "Article{" +
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
