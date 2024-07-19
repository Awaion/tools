package com.awaken.tool.demo003.service;

import com.awaken.tool.demo003.model.Teacher;
import com.awaken.tool.demo003.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository repository;

    public List<Teacher> getTeachers(String subject) {
        // 第一种方法实例化出 Sort 类，根据年龄进行升序排列
        Sort sort1 = Sort.by(Sort.Direction.ASC, "age");

        //定义多个字段的排序
        Sort sort2 = Sort.by(Sort.Direction.DESC, "id", "age");

        // 通过实例化 Sort.Order 来排序多个字段
        List<Sort.Order> orders = new ArrayList<>();
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.DESC, "age");
        orders.add(order1);
        orders.add(order2);
        Sort sort3 = Sort.by(orders);

        //可以传不同的 sort1,2,3 去测试效果
        return repository.getTeachers(subject, sort1);
    }

    public Page<Teacher> getPage(@PathVariable("subject") String subject) {
        // 第一种方法实例化 Pageable
        Pageable pageable1 = PageRequest.of(1, 2);

        //第二种实例化 Pageable
        Sort sort = Sort.by(Sort.Direction.ASC, "age");
        Pageable pageable2 = PageRequest.of(1, 2, sort);

        //第三种实例化 Pageable
        Pageable pageable3 = PageRequest.of(1, 2, Sort.Direction.DESC, "age");

        //可以传入不同的 Pageable,测试效果
        Page page = repository.getPage(subject, pageable3);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
        System.out.println(page.getNumberOfElements());
        System.out.println(page.getSize());
        return page;
    }

    public List<Teacher> queryBanner(Integer location) {
        return repository.findAll(
                //实例化 Specification 类
                (root, query, criteriaBuilder) -> {
                    // 构建查询条件
                    Predicate predicate = criteriaBuilder.equal(root.get("id"), location);
                    // 使用 and 连接上一个条件
                    predicate = criteriaBuilder.and(
                            predicate,
                            criteriaBuilder.equal(root.get("age"), location));
                    return predicate;
                },
                //传入Sort排序参数进行排序
                Sort.by(Sort.Order.desc("数据库字段")));
    }

    public void save(Teacher teacher) {
        repository.save(teacher);
    }
}
