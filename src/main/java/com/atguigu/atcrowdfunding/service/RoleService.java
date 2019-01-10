package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Role;

public interface RoleService {

	List<Role> pageQuery(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	void deleteRoleById(Integer id);

	void insertRole(Role role);

	Role getRoleById(Integer id);

	void updateRole(Role role);

	void deleteRoles(Map<String,Object> map);

	List<Role> queryAllRole();

}
