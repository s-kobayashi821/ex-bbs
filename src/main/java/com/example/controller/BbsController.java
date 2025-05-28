package com.example.controller;

import com.example.Form.ArticleForm;
import com.example.Form.CommentForm;
import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.service.BbsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 記事とコメントを管理するコントローラ.
 */
@Controller
@RequestMapping("")
public class BbsController {
    @Autowired
    BbsService service;

    /**
     * すべての記事とそれに対するコメント一覧を表示する
     *
     * @param model       モデル
     * @param articleForm 記事のフォーム
     * @param commentForm コメントのフォーム
     * @return 記事とコメントの表示
     */
    @GetMapping("")
    public String index(Model model, ArticleForm articleForm, CommentForm commentForm) {
        List<Article> articleList = service.getAllArticleAndComment();
        model.addAttribute("articleList", articleList);
        return "bbs";
    }

    /**
     * 新しく記事を投稿する.
     *
     * @param articleForm 記事の入力フォーム
     * @return 同じページにリダイレクトする
     */
    @PostMapping("/postArticle")
    public String postArticle(Model model,
                              @Validated ArticleForm articleForm,
                              BindingResult result,
                              CommentForm commentForm) {
        if (result.hasErrors()) {
            return index(model, articleForm, commentForm);
        }

        Article article = new Article();
        BeanUtils.copyProperties(articleForm, article);
        service.insertArticle(article);
        return "redirect:";
    }

    /**
     * 新しくコメントを投稿する.
     *
     * @param commentForm コメントの入力フォーム
     * @return 同じページにリダイレクトする
     */
    @PostMapping("/postComment")
    public String postComment(Model model,
                              ArticleForm articleForm,
                              @Validated CommentForm commentForm,
                              BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errorArticleId", commentForm.getArticleId());
            return index(model, articleForm, commentForm);
        }

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentForm, comment);
        service.insertComment(comment);
        return "redirect:";
    }

    /**
     * 記事とコメントを削除する.
     *
     * @param articleId 記事のID
     * @return 同じページにリダイレクトする
     */
    @PostMapping("/deleteArticle")
    public String deleteArticle(Integer articleId) {
        service.deleteArticleAndComment(articleId);
        return "redirect:";
    }
}
