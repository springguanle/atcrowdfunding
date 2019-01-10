package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.AjaxResult;
import com.atguigu.atcrowdfunding.bean.Page;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.service.RoleService;

@RequestMapping("/role")
@Controller
public class RoleController {
	
	@Autowired
	private  RoleService roleService;
	
	@RequestMapping("/index")
	public String index() {
		return "role/index";
	}
	
	@RequestMapping("add")
	public String add() {
		return "role/add";
	}
	
	@RequestMapping("deleteRoles")
	@ResponseBody
	public Object deleteRoles(Integer[] roleid) {
		AjaxResult result =new AjaxResult();
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			map.put("ids",roleid);
			roleService.deleteRoles(map);
			result.setSuccess(true);
		}catch(Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("update")
	public Object update(Role role) {
		AjaxResult result =new AjaxResult();
		try {
			roleService.updateRole(role);
			result.setSuccess(true);
		}
		catch(Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * edit页面的跳转
	 * @param id 主键
	 * @param model 
	 * @return
	 */
	@RequestMapping("edit")
	public String edit(Integer id,Model model) {
		Role role=roleService.getRoleById(id);
		model.addAttribute("role", role);
		return "role/edit";
	}
	
	/**
	 * 添加角色
	 * @param role 返回role对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("insert")
	public Object insert(Role role) {
		AjaxResult result=new AjaxResult();
		try {
			roleService.insertRole(role);
			result.setSuccess(true);
		}catch(Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除
	 * @param id 主键
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(Integer id) {
		AjaxResult result=new AjaxResult();
		try {
			roleService.deleteRoleById(id);
			result.setSuccess(true);
		}catch(Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}
	
	/**角色的分页
	 * @param queryText 模糊查询内容
	 * @param pageno 当前页
	 * @param pagesize 每页显示的数量
	 * @return
	 */
	@RequestMapping("/pageQuery")
	@ResponseBody
	public Object pageQuery(String queryText,Integer pageno,Integer pagesize) {
		AjaxResult result=new AjaxResult();
		try {
			   Map<String,Object> map=new HashMap<String , Object>();
			   map.put("start", (pageno-1)*pagesize);
			   map.put("size", pagesize);
			   map.put("queryText", queryText);
			   List<Role> roles=roleService.pageQuery(map);		 
			   //总的记录数
			   int totalsize=roleService.pageQueryCount(map);
			   
			   int totalno=0;
			   if(totalsize%pagesize==0){
				   totalno=totalsize/pagesize;
			   }else{
				   totalno=totalsize/pagesize+1;
			   }
			   Page<Role> rolePage=new Page<Role>();
			   rolePage.setDatas(roles);
			   rolePage.setTotalno(totalno);
			   rolePage.setTotalsize(totalsize);
			   rolePage.setPageno(pageno);
			   result.setData(rolePage);
			   result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
