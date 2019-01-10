package com.atguigu.atcrowdfunding.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.dao.UserDao;
import com.atguigu.atcrowdfunding.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	

	@Autowired
	private UserDao userDao;
	@Override
	public List<User> queryAll() {
		List<User> queryAll = userDao.queryAll();
		return queryAll;
	}
	@Override
	public User quey4User(User user) {
		User doUser=userDao.query4User(user);
		return doUser;
	}
	@Override
	public List<User> pageQuery(Map<String, Object> map) {
		return userDao.pageQuery(map);
	}
	@Override
	public int pageQueryCount(Map<String, Object> map) {
		return userDao.pageQueryCount(map);
	}
	@Override
	public void insertUser(User user) {
		user.setPassword("123456");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setCreatetime(sdf.format(new Date()));
		userDao.insertUser(user);
	}
	@Override
	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	@Override
	public void deleteUserById(int id) {

		userDao.deleteUserById(id);
	}
	@Override
	public void deleteUsers(Map<String,Object> map) {
		userDao.deleteUsers(map);
	}

}
