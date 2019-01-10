package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.AjaxResult;
import com.atguigu.atcrowdfunding.bean.Page;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.service.RoleService;
import com.atguigu.atcrowdfunding.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService; 
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 用户的添加页面的跳转
	 * @return
	 */
	@RequestMapping("/add")
	public String userAdd() {
		return "user/add";
	}
	@RequestMapping("/assign")
	public String assign(Integer id,Model model) {
		List<Role> roles=roleService.queryAllRole();
		model.addAttribute("roles", roles);
		return "user/assign";
	}
	
	/** 用户修改页面的跳转
	 * @param id
	 * @param m
	 * @return
	 */
	@RequestMapping("/edit")
	public String userEdit(Integer id,Model m) {
		User user=userService.getUserById(id);
		m.addAttribute("user", user);
		return "/user/edit";
	}
	
	/**用户的更新
	 * @param user
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Object userUpdate(User user) {
		AjaxResult result=new AjaxResult();
		try {
			userService.updateUser(user);
			result.setSuccess(true);
		}catch(Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 用户的添加
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("insert")
	public Object insertUser(User user) {
		//希望新增的数据就能够看见，需要做修改
		AjaxResult result=new AjaxResult();
		try {
			userService.insertUser(user);
			result.setSuccess(true);
		}catch(Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 单个用户的删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object deleteUser(int id) {
		AjaxResult result=new AjaxResult();
		try {
			userService.deleteUserById(id);
			result.setSuccess(true);
		}catch(Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 批量删除
	 * @param userid
	 * @return
	 */
	@RequestMapping("/deleteUsers")
	@ResponseBody
	public Object deleteUser(Integer[] userid) {
		AjaxResult result=new AjaxResult();
		Map<String,Object> map=new HashMap<>();
		try {
			map.put("ids", userid);
			userService.deleteUsers(map);
			result.setSuccess(true);
		}catch(Exception ex) {
			result.setSuccess(false);
			ex.printStackTrace();
		}
		return result;
	}
	
/*	@RequestMapping("index1")
	public String index(@RequestParam(required=false,defaultValue="1")Integer pageno,@RequestParam(required=false,defaultValue="2")Integer pagesize,Model model) {
		Map<String,Object> map=new HashMap<>();
		map.put("start",(pageno-1)*pagesize);
		map.put("size",pagesize);
		List<User> users=userService.pageQuery(map);
		model.addAttribute("user", users);
		//当前页
		model.addAttribute("pageno", pageno);
		//总页数
		int totalUser=userService.pageQueryCount(map);
		int totalPage=totalUser%pagesize==0?totalUser/pagesize:totalUser/pagesize+1;
		model.addAttribute("totalPage",totalPage);
		return "user/index";
	}*/
	
	/**
	 * 主页面跳转
	 * @return
	 */
	@RequestMapping("index")
	public String index() {
		return  "/user/index";
	}
	/**分页查询
	 * @param queryText
	 * @param pageno
	 * @param pagesize
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
			   List<User> users=userService.pageQuery(map);		 
			   //总的记录数
			   int totalsize=userService.pageQueryCount(map);
			   
			   int totalno=0;
			   if(totalsize%pagesize==0){
				   totalno=totalsize/pagesize;
			   }else{
				   totalno=totalsize/pagesize+1;
			   }
			   Page<User> userPage=new Page<User>();
			   userPage.setDatas(users);
			   userPage.setTotalno(totalno);
			   userPage.setTotalsize(totalsize);
			   userPage.setPageno(pageno);
			   result.setData(userPage);
			   result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

}
