package com.atguigu.atcrowdfunding.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.atguigu.atcrowdfunding.bean.User;

public interface UserDao {
	
	@Select("select * from t_user")
	List<User> queryAll();
	
	@Select("select * from user where loginacct=#{loginacct} and password=#{password}")
	User query4User(User user);

	List<User> pageQuery(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	void insertUser(User user);
	@Select("select * from  user where id=#{id}")
	User getUserById(Integer id);

	void updateUser(User user);
	
	@Delete("delete from user where id=#{id}")
	void deleteUserById(int id);

	void deleteUsers(Map<String,Object> map);
}
