package spring.boot.core.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * 用户持久层操作接口
 *
 * Created by bysocket on 21/07/2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(Long id);
//    List<User> findByName(String name);
}
