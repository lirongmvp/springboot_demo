package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User 业务层实现
 *
 * Created by bysocket on 24/07/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User insertByUser(User user) {
        LOGGER.info("新增用户：" + user.toString());
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        LOGGER.info("更新用户：" + user.toString());
        return userRepository.save(user);
    }

    @Override
    public User delete(Long id) {
//        以前这里是User user = userRepository.findById(id).get();
        /**
         * findById(id)  JPA版本2.0.0.M4（也是parent的版本）java.util.Optional<T>（是JDK1.8的新特性）
         * get()方法是 如果对象不为空isPresent()返回ture 就得到对象
         */
        User user = userRepository.findById(id);
        userRepository.delete(user);

        LOGGER.info("删除用户：" + user.toString());
        return user;
    }

    @Override
    public User findById(Long id) {
        LOGGER.info("获取用户 ID ：" + id);
        return userRepository.findById(id);
    }
    //分页
    @Override
    public Page<User> findByAge(Integer age, Pageable pageable) {
        Page<User> userPage = userRepository.findByAge(age,pageable);
        return userPage;
    }

    @Override
    public Page<User> findAll(Specification<User> userSpecification, Pageable pageable) {
        Page<User> page = userRepository.findAll(userSpecification, pageable);
        return page;
    }

    @Override
    public List<User> findByName(String name, Sort sort) {
        List<User> list = userRepository.findByName(name, sort);
        return list;
    }
}
