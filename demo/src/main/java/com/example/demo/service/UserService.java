package com.example.demo.service;

import com.example.demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * User 业务层接口
 *
 * Created by bysocket on 24/07/2017.
 */
public interface UserService {

    List<User> findAll();

    User insertByUser(User user);

    User update(User user);

    User delete(Long id);

    User findById(Long id);

    List<User> findByName(String name, Sort sort);

    Page<User> findByAge(Integer age, Pageable pageable);

    Page<User> findAll(Specification<User> userSpecification, Pageable pageable);
}
