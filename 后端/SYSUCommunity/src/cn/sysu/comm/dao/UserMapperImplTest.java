package cn.sysu.comm.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.sysu.comm.entity.User;

public class UserMapperImplTest {

	@Test
	public void testFindUserById() {
		UserMapperImpl userImpl = new UserMapperImpl();
		System.out.println(userImpl.findUserById("123").getUsername());
	}
	
	@Test
	public void edit() {
		UserMapperImpl userImpl = new UserMapperImpl();
		User user = new User();
		user.setUser_id("123");
		user.setSex("男");
		user.setEmail("this@sysu.mail.com");
		user.setPassword("3333");
		user.setGrade("2015");
		user.setUsername("区块链");
		userImpl.editUser(user);
	}

}
