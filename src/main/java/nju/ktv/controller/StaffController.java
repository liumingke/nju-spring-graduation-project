package nju.ktv.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nju.ktv.dao.StaffDao;
import nju.ktv.vo.PageDto;
import nju.ktv.vo.Staff;

@Controller
public class StaffController {

	@Autowired
	private StaffDao staffDao;
	
	@RequestMapping(path= {"/staff"} , method = {RequestMethod.POST})
	public String add(Staff staff) {
		
		Staff s = staffDao.save(staff);
		System.out.println(s);
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
		List<Staff> staffs = staffDao.listByPage(pageDto.getOffset_row(), pageDto.getPage_size());
		
		if(staffs.size()==0) {
			return "redirect:/staff/" + --current_page;
		}
		map.put("pageDto", pageDto);
		map.put("staffs", staffs);
		
		return "staff/list";
	}
	
	@RequestMapping(path= {"/staff/{current_page}/{id}/{zb}"} , method = {RequestMethod.GET})
	public String del (@PathVariable(name="current_page") int current_page, @PathVariable(name="id") int id) {
		staffDao.delete(id);
		return "redirect:/staff/" + current_page;
	}
	
	//修改跳转链接
	@RequestMapping(path= {"/staff/{current_page}/{id}"} , method = {RequestMethod.GET})
	public String load (@PathVariable(name="current_page") int current_page , @PathVariable(name="id") int id, Model model){
		Staff staff = staffDao.findOne(id);
		
		model.addAttribute("staff" , staff);
		
		return "staff/edit";
	}
	
	@RequestMapping(path= {"/staff/0/{current_page}"} , method = {RequestMethod.POST})
	public String edit (@PathVariable(name="current_page") int current_page , Staff staff) {
		
		Staff staff_dao = staffDao.saveAndFlush(staff);
		
		System.out.println(staff_dao);
		
		return "redirect:/staff/" + current_page ;
	}
}
