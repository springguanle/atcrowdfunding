package com.atguigu.atcrowdfunding.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.atguigu.atcrowdfunding.bean.Role;

public interface RoleDao {

	List<Role> rolePage(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	@Delete("delete from role where id=#{id}")
	void deleteRoleById(Integer id);

	void insertRole(Role role);

	@Select("select * from role where id=#{id}")
	Role getRoleById(Integer id);

	void updateRole(Role role);

	void deleteRoles(Map<String,Object> map);
	@Select("select * from role")
	List<Role> queryAllRole();

}
