package nju.ktv.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nju.ktv.dao.StaffDao;
import nju.ktv.vo.PageDto;
import nju.ktv.vo.Staff;

@Controller
public class StaffController {

	@Autowired
	private StaffDao staffDao;
	
	@RequestMapping(path= {"/staff"} , method = {RequestMethod.POST})
	public String add(Staff staff) {
		
		staffDao.save(staff);
		return "redirect:/staff/1";
	}
	
	@Value("${page_size}")
	private int page_size;
	
	@RequestMapping(path= {"/staff/{current_page}"} , method= {RequestMethod.GET})
	public String list(@PathVariable(name="current_page") int current_page , Map<String , Object> map) {
		//第一种方式  使用jpaRepository准备好的分页查询方法  需要大量修改页面的取值
		
		//第二种方式   使用之前我们自定义的分页查询工具   因为页面上已经配置好了数据的迭代和取值   这里我就是用自定义的分页工具
		
		//查询总记录数
		PageDto pageDto = new PageDto((int) staffDao.count(), current_page, page_size);
//		List<Staff> staffs = staffDao.listByPage(pageDto.getOffset_row(), pageDto.getPage_size());
//		
//		if(staffs.size()==0) {
//			return "redirect:/staff/" + --current_page;
//		}
		map.put("pageDto", pageDto);
//		map.put("staffs", staffs);
		
		return "staff/list";
	}
	
	@RequestMapping(path= {"/staff/list/{current_page}/{post}/{name}"},method= {RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> query(@PathVariable(name="current_page") int current_page, @PathVariable(name="post") String post, @PathVariable(name="name") String name){
		Map<String, Object> map = new HashMap<>();
		List<Staff> staffs = new ArrayList<>();
		if(post!=null&&name.equals("null")) {
			int total_rows = 0;
			if(!post.equals("所有类别")) {
				 total_rows = staffDao.post_count(post);
			} else {
				 total_rows = (int)staffDao.count();
			}
			PageDto pageDto = new PageDto(total_rows, current_page, page_size);
			if(!post.equals("所有类别")) {
				 staffs= staffDao.listByPost(post,pageDto.getOffset_row(),pageDto.getPage_size());
			} else {
				 staffs = staffDao.listByPage(pageDto.getOffset_row(), pageDto.getPage_size());
			}
			map.put("pageDto", pageDto);
		}
		else if(!name.equals("null")) {
			int total_rows = staffDao.name_count(name);
			PageDto pageDto = new PageDto(total_rows, current_page, page_size);
			staffs = staffDao.findByName(name,pageDto.getOffset_row(), pageDto.getPage_size());
			map.put("pageDto", pageDto);
		}
		
		map.put("staffs", staffs);
		map.put("msg", "success");
		
		return map;
	}
	
	
	@RequestMapping(path= {"/staff/{current_page}/{id}/{zb}"} , method = {RequestMethod.GET})
	public String del (@PathVariable(name="current_page") int current_page, @PathVariable(name="id") int id) {
		staffDao.delete(id);
		return "redirect:/staff/" + current_page;
	}
	
	//修改跳转链接
	@RequestMapping(path= {"/staff/{current_page}/{id}"} , method = {RequestMethod.GET})
	public String load (@PathVariable(name="current_page") int current_page, @PathVariable(name="id") int id, Model model){
		Staff staff = staffDao.findOne(id);
		model.addAttribute("staff" , staff);
		return "staff/edit";
	}
	
	@RequestMapping(path= {"/staff/0/{current_page}"} , method = {RequestMethod.POST})
	public String edit (@PathVariable(name="current_page") int current_page , Staff staff) {
		
		staffDao.saveAndFlush(staff);
		return "redirect:/staff/" + current_page ;
	}
}
