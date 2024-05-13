package com.awaion.demo008.dao;

import java.util.List;

import com.awaion.demo008.domain.Student;

public interface IStudentDAO {
	
	/**
	 * 
	 * @param stu  需要被存储的学生对象
	 */
	void save(Student stu);
	
	/**
	 * 
	 * @param id 要被删除的元素的索引
	 */
	void delete(Long id);
	
	/**
	 * 
	 * @param stu  用来做更新操作的学生对象
	 */
	void update(Student stu);
	
	/**
	 * 
	 * @param id  要查询的对象在数据库中的id
	 * @return 学生对象
	 */
	Student getStudent(Long id);
	
	/**
	 * 
	 * @return  返回学生对象形成的list集合
	 */
	List<Student> getList();
	
	

}
