package nju.ktv.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="staff")//声明与哪一张表对应
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder

public class Staff {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;//员工ID
	@NonNull
	@Column(length=40)
	private String staffname;//员工姓名
	@NonNull
	@Column(length=40)
	private String post;//员工岗位
	@NonNull
	@DateTimeFormat(pattern="yyyy-MM-dd")//标注该属性只接收指定格式的日期，默认为 yyyy/MM/dd
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	@NonNull
	private Integer age;
	@NonNull
	private Double salary;
}
