package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.User;

public interface UserService {
	
	List<User> queryAll();

	User quey4User(User user);

	List<User> pageQuery(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	void insertUser(User user);

	User getUserById(Integer id);

	void updateUser(User user);

	void deleteUserById(int id);

	void deleteUsers(Map<String,Object> map);
}
