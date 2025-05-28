package com.example.service;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.*;

/**
 * 記事とコメントの情報を操作するクラス.
 */
@Service
@Transactional
public class BbsService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    /**
     * すべての「記事とそれに対するコメントのリスト」のリストを取得する.
     *
     * @return articleAndComment 「記事とそれに対するコメントのリスト」のリスト
     */
//    public List<Article> getAllArticleAndComment(){
//        List<Article>  articleList= articleRepository.findAll();
//        for(Article article: articleList){
//            List<Comment> commentList = commentRepository.findByArticleId(article.getId());
//            article.setCommentList(commentList);
//        }
//        return articleList;
//    }

    /**
     * 中級者課題用:すべての「記事とそれに対するコメントのリスト」のリストを取得する.
     *
     * @return articleAndComment 「記事とそれに対するコメントのリスト」のリスト
     */
    public List<Article> getAllArticleAndComment(){
//        List<Article>  articleList= articleRepository.findAll();
//        for(Article article: articleList){
//            List<Comment> commentList = commentRepository.findByArticleId(article.getId());
//            article.setCommentList(commentList);
//        }
//        return articleList;
        return articleRepository.findAllArticleAndComment();
    }



    /**
     * 記事IDを指定して該当する記事,コメントを削除する.
     *
     * @param articleId 記事ID
     */
    public void deleteArticleAndComment(int articleId){
//        commentRepository.deleteByArticleId(articleId);
        articleRepository.deleteById(articleId);
    }

    /**
     * 新しい記事を追加する.
     *
     * @param article コメント
     */
    public void insertArticle(Article article){
        articleRepository.insert(article);
    }


    /**
     * 新しいコメントを追加する.
     *
     * @param comment コメント
     */
    public void insertComment(Comment comment){
        commentRepository.insert(comment);
    }



}
