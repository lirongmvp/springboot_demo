package spring.boot.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.core.domain.User;
import spring.boot.core.domain.UserRepository;
import spring.boot.core.service.UserService;

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
    UserRepository userRepository;

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
}
