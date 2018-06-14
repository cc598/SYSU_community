package cn.sysu.comm.service;

import java.sql.SQLException;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.JdbcUtils;
import cn.sysu.comm.dao.AnswerMapper;
import cn.sysu.comm.dao.AnswerMapperImpl;
import cn.sysu.comm.dao.ArticleMapper;
import cn.sysu.comm.dao.ArticleMapperImpl;
import cn.sysu.comm.dao.CommentMapper;
import cn.sysu.comm.dao.CommentMapperImpl;
import cn.sysu.comm.dao.QuestionMapper;
import cn.sysu.comm.dao.QuestionMapperImpl;
import cn.sysu.comm.dao.UserMapper;
import cn.sysu.comm.dao.UserMapperImpl;
import cn.sysu.comm.entity.Question;
import cn.sysu.comm.entity.User;
/**
 * 业务层
 * 这一层以Dao层作为依赖，即对数据的任何需求都是访问dao层函数来得到
 * 不能直接操作数据库
 * 所以可以用两种方法
 * 1.实现Dao层接口，实例化实现后的对象，调用其方法
 * 2.使用代理
 */
public class UserService {
	
	UserMapper userDao = new UserMapperImpl();
	ArticleMapper articleDao = new ArticleMapperImpl();
	QuestionMapper questionDao = new QuestionMapperImpl();
	AnswerMapper answerDao = new AnswerMapperImpl();
	CommentMapper commentDao = new CommentMapperImpl();
	
	public boolean login(String cid, String password) {
		User user = userDao.findUserById(cid);
		if(user != null) {
			if(user.getPassword().equals(password))
				return true;
		}
		return false;
	}

	public User findUser(String userid) {
		
		User user = userDao.findUserById(userid);
		return user;
		
	}

	public void edit(User user) {
		try {
			JdbcUtils.beginTransaction();
			User originalUser = userDao.findUserById(user.getUser_id());
			User newUser = implementUser(user, originalUser);
			userDao.editUser(newUser);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
			}
		}
	}
	
	/**
	 * 
	 * @Description: 个人资料
	 * 我的所有个人资料
	 * 包括User、Article、Comment、Question、Answer
	 * @author: bee
	 * @CreateTime: 2018-5-9 下午3:50:00 
	 */
	public User me(String userid) {
		User user = findUser(userid);
		user.setComments(commentDao.findCommentsByAuthorId(userid));
		user.setArticles(articleDao.findArticlesByAuthorId(userid));
		user.setAnswers(answerDao.findAnswersByAuthorId(userid));
		user.setQuestions(questionDao.findQuestionsByAuthorId(userid));
		return user;
	}
	
	private User implementUser(User newUser, User originalUser) {
		newUser.setUsername(newUser.getUsername() == null ? originalUser.getUsername():newUser.getUsername());
		newUser.setSex(newUser.getSex() == null ? originalUser.getSex():newUser.getSex());
		newUser.setPassword(newUser.getPassword() == null? originalUser.getPassword():newUser.getPassword());
		newUser.setIcon(newUser.getIcon() == null ? originalUser.getIcon():newUser.getIcon());
		newUser.setGrade(newUser.getGrade() == null ? originalUser.getGrade():newUser.getGrade());
		newUser.setEmail(newUser.getEmail() == null ? originalUser.getEmail():newUser.getEmail());
		newUser.setCollege(newUser.getCollege() == null ? originalUser.getCollege():newUser.getCollege());
		return newUser;
	}
	
}
