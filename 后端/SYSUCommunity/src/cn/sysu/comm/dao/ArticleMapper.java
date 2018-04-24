package cn.sysu.comm.dao;

import cn.sysu.comm.entity.Article;

/**
 * 
 * @Description: 
 * Article数据层接口：增删改查
 * @CreateTime: 2018-4-24 下午8:12:44 
 * @author: bee
 * @version V1.0
 */

public interface ArticleMapper {
	public Article findArticleByName(String artName);
}
