package com.example.demo.domain;


import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


/**
 * 用户持久层操作接口
 *
 * Created by bysocket on 21/07/2017.
 */
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

    User findById(Long id);
//    List<User> findByName(String name);
    List<User> findByName(String name, Sort sort);
    Page<User> findByAge(Integer age, Pageable pageable);

}
