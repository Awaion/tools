package com.awaion.demo008.dao.impl;

import java.util.List;

import com.awaion.demo008.dao.IStudentDAO;
import com.awaion.demo008.domain.Student;
import com.awaion.demo008.handler.impl.BeanHandler;
import com.awaion.demo008.handler.impl.BeanListHandler;
import com.awaion.demo008.uitl.JDBCTemplate;

public class StudentDAOImpl implements IStudentDAO {

	@Override
	public void save(Student stu) {
		
		String sql = " insert into t_student values(null,?,?) ";
		JDBCTemplate.update(sql, stu.getName(),stu.getAge());
	}

	@Override
	public void delete(Long id) {
		String sql = " delete from t_student where id =? ";
		JDBCTemplate.update(sql, id);
	}

	@Override
	public void update(Student stu) {

		String sql = " update t_student set name = ? , age = ? where id = ? ";
		JDBCTemplate.update(sql, stu.getName(),stu.getAge(),stu.getId());
		
	}

	@Override
	public Student getStudent(Long id) {
		
		String sql = " select * from t_student where id = ? ";
		return JDBCTemplate.query(sql, new BeanHandler<>(Student.class), id);
	}

	@Override
	public List<Student> getList() {
		String sql = " select * from t_student ";
		return JDBCTemplate.query(sql, new BeanListHandler<>(Student.class));
		
		
	}

}
