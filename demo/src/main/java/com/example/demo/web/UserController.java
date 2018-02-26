package com.example.demo.web;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.example.demo.domain.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;


/**
 * 用户控制层
 * <p>
 * Created by bysocket on 24/07/2017.
 */
@Controller
@RequestMapping(value = "/users")     // 通过这里配置使下面的映射都在 /users
public class UserController {

    @Autowired
    private UserService userService;          // 用户服务层

    /**
     * 获取用户列表
     * 处理 "/users" 的 GET 请求，用来获取用户列表
     * 通过 @RequestParam 传递参数，进一步实现条件查询或者分页查询
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getUserList(ModelMap map) {

        return "userList";
    }

    /**
     * 显示创建用户表单
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUserForm(ModelMap map) {
        map.addAttribute("user", new User());
        map.addAttribute("action", "create");
        return "userForm";
    }

    /**
     * 创建用户
     * 处理 "/users" 的 POST 请求，用来获取用户列表
     * 通过 @ModelAttribute 绑定参数，也通过 @RequestParam 从页面中传递参数
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        userService.insertByUser(user);
        return "redirect:/users/";
    }

    /**
     * 显示需要更新用户表单
     * 处理 "/users/{id}" 的 GET 请求，通过 URL 中的 id 值获取 User 信息
     * URL 中的 id ，通过 @PathVariable 绑定参数
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Long id, ModelMap map) {
        map.addAttribute("user", userService.findById(id));
        map.addAttribute("action", "update");
        return "userForm";
    }

    /**
     * 处理 "/users/{id}" 的 PUT 请求，用来更新 User 信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String putUser(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/users/";
    }

    /**
     * 处理 "/users/{id}" 的 GET 请求，用来删除 User 信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id) {

        userService.delete(id);
        return "redirect:/users/";
    }

    @RequestMapping("/find")
    @ResponseBody
    public List<User> findUser() {
        List<User> list = userService.findAll();
        return list;
    }

    @RequestMapping("/sort/{name}")
    @ResponseBody
    public List<User> findByName(@PathVariable String name) {
        List<User> list = userService.findByName(name, new Sort(new Sort.Order(Sort.Direction.DESC, "age")));
        return list;
    }

    @RequestMapping("/page/{age}/{pageNo}/{size}")
    @ResponseBody
    public Page<User> findByAge(@PathVariable Integer age, @PathVariable Integer pageNo, @PathVariable Integer size) {
        Page<User> userPage = userService.findByAge(age, new PageRequest(pageNo, size));
        return userPage;
    }

    @RequestMapping("/page/{name}/{age}/{pageNo}/{size}")
    @ResponseBody
    public Page<User> pageUser(@PathVariable String name, @PathVariable Integer age, @PathVariable Integer pageNo, @PathVariable Integer size) {
        Page<User> page = this.userService.findAll((Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();
            if (!StringUtils.isEmpty(name)) {
                predicates.add(criteriaBuilder.equal(root.get("name"), name));
            }
            if (age != null) {
                predicates.add(criteriaBuilder.equal(root.get("age"), age));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, new PageRequest(pageNo, size));
        return page;

    }

}