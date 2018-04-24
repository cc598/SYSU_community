package cn.sysu.comm.service;

import cn.sysu.comm.dao.UserMapper;
/**
 * 业务层
 * 这一层以Dao层作为依赖，即对数据的任何需求都是访问dao层函数来得到
 * 不能直接操作数据库
 * 所以可以用两种方法
 * 1.实现Dao层接口，实例化实现后的对象，调用其方法
 * 2.使用代理
 */
public class UserService {

	public boolean login() {
		return false;
	}
	
}
