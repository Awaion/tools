package com.awaion.demo008.test;

import java.util.List;

import org.junit.Test;

import com.awaion.demo008.dao.IStudentDAO;
import com.awaion.demo008.dao.impl.StudentDAOImpl;
import com.awaion.demo008.domain.Student;

public class StudentDAOTest {

	private static IStudentDAO dao = new StudentDAOImpl();

	@Test
	public void testSave() throws Exception {

		Student stu = new Student();
		stu.setName("周杰伦");
		stu.setAge(38);

		dao.save(stu);

	}

	@Test
	public void testDelete() {
		dao.delete(1013L);

	}

	@Test
	public void testUpdate() {

		Student stu = new Student();
		stu.setName("michael jackson");
		stu.setAge(55);
		stu.setId(1018L);

		dao.update(stu);

	}

	@Test
	public void testGetStudent() {

		Student stu = dao.getStudent(1018L);
		System.out.println(stu);

	}

	@Test
	public void testGetList() {
		List<Student> list = dao.getList();
		for (Student stu : list) {
			System.out.println(stu);
		}

	}

}
