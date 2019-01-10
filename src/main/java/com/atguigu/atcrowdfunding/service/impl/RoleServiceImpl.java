package com.atguigu.atcrowdfunding.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.dao.RoleDao;
import com.atguigu.atcrowdfunding.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;

	@Override
	public int pageQueryCount(Map<String, Object> map) {
		return roleDao.pageQueryCount(map);
	}

	@Override
	public List<Role> pageQuery(Map<String, Object> map) {
		return roleDao.rolePage(map);
	}

	@Override
	public void deleteRoleById(Integer id) {
		roleDao.deleteRoleById(id);
		
	}

	@Override
	public void insertRole(Role role) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		role.setCreatetime(sdf.format(new Date()));
		roleDao.insertRole(role);
	}

	@Override
	public Role getRoleById(Integer id) {
		return roleDao.getRoleById(id);
	}

	@Override
	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	@Override
	public void deleteRoles(Map<String,Object> map) {
		roleDao.deleteRoles(map);
	}

	@Override
	public List<Role> queryAllRole() {
		return roleDao.queryAllRole();
	}
}
