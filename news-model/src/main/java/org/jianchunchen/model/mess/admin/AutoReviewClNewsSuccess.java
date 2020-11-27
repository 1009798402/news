package org.jianchunchen.model.mess.admin;


import org.jianchunchen.model.article.pojos.ApArticleConfig;
import org.jianchunchen.model.article.pojos.ApArticleContent;
import org.jianchunchen.model.article.pojos.ApAuthor;
import lombok.Data;

@Data
public class AutoReviewClNewsSuccess {
    private ApArticleConfig apArticleConfig;
    private ApArticleContent apArticleContent;
    private ApAuthor apAuthor;

}
