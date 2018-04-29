package cn.sysu.comm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.sysu.comm.entity.User;

public class UserMapperImpl implements UserMapper {

	private QueryRunner qRunner = new TxQueryRunner();
	
	@Override
	public User findUserById(String id) {
		String sql = "select * from t_user where user_id = ?";
		try {
			return qRunner.query(sql, new BeanHandler<User>(User.class), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<User> findUserByName(String username) {
		String sql = "select * from t_user where username like ?";
		try {
			return qRunner.query(sql, new BeanListHandler<User>(User.class), "%"+username+"%");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editUser(User user) {
		String sql = "update t_user set username=?, password=?, icon=?, sex=?," +
					" email=? where user_id=?";
		Object[] params = {user.getUsername(), user.getPassword(), user.getIcon_url(),
							user.getSex(), user.getEmail(),user.getUser_id()};
		try {
			qRunner.update(sql, params);
		} catch (SQLException e) {
		throw new RuntimeException(e);
		}

	}

}
