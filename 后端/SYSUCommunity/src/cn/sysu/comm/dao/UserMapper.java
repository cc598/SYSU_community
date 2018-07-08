package cn.sysu.comm.dao;

import java.util.List;

import cn.sysu.comm.entity.User;

/*
author: kingchiu
 */


public interface UserMapper {
	/*
	 * 通过UserId查询User
	 * 用于登录
	 */
	public User findUserById(String id);
	/*
	 * 通过用户名查找用户
	 */
	public List<User> findUserByName(String username);
	/*
	 * 编辑用户，更改用户信息
	 */
	public void editUser(User user);
	/*
	 * 增加用户 管理员可用 
	 */
	public void addUser(User user);
}
