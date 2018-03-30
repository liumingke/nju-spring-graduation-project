package nju.ktv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nju.ktv.vo.Staff;

//本接口继承了jpa提供的JpaRepository接口  就会自动继承其方法
//不用在这里添加任何注解  继承之后就自动被spring扫描了  此时spring 的IOC容器中已经有了StaffDao类型的bean 叫 staffDao

public interface StaffDao extends JpaRepository<Staff, Integer> {

	@Query(value = "select id, staffname, post, birthday, age, salary from staff order by id limit ?1 , ?2" , nativeQuery=true)
	List<Staff> listByPage(int offset_row , int page_size);
	
	@Query(value = "select count(id) from staff where post = ?" , nativeQuery=true)
	int post_count(String post);
	
	@Query(value = "select id, staffname, post, birthday, age, salary from staff where post = ?1 order by id limit ?2 , ?3" , nativeQuery=true)
	List<Staff> listByPost(String post, int offset_row , int page_size);
	
	@Query(value = "select count(id) from staff where staffname = ?", nativeQuery=true)
	int name_count(String name);
	
	@Query(value = "select id, staffname, post, birthday, age, salary from staff where staffname = ?1 order by id limit ?2 , ?3" , nativeQuery=true)
	List<Staff> findByName(String name , int offset_row , int page_size);
}
