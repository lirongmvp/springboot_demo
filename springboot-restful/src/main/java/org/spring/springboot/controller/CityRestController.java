package org.spring.springboot.controller;

import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 城市 Controller 实现 Restful HTTP 服务
 *
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public City findOneCity(@PathVariable("id") Long id) {
        return cityService.findCityById(id);
    }
    //RequestParam((value="id",defaultValue="0")) 这个相当于url后面加参数
    //如果name命一样 自动绑定
    @RequestMapping(value = "/api/city/postman", method = RequestMethod.POST)
    public City findCity(Long id) {
        System.out.printf("Postman..........");
        return cityService.findCityById(id);

    }

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public List<City> findAllCity() {
        return cityService.findAllCity();
    }
    //request中发送json数据用post方式发送Content-type用application/json;charset=utf-8方式发送的话，
    //直接用springMVC的@RequestBody标签接收后面跟实体对象就行了
    //如果你用application/json 发送JOSN数据，而不用@RequestBody去绑定，Controller层接受不到数据（null）

    //用x-www-form-urlencoded格式，不需要绑定,只要键值对一致就可以了
    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public void createCity( City city) {
        cityService.saveCity(city);
    }
    //注意 @RequestBody 只接受JSON数据格式的键值对（目前知道的）
    //如果Content-type设置成application/x-www-form-urlencoded;charset=utf-8就不能用spring的东西了，
    // @RequestBody只能以常规的方式获取json串了
    //所以用application/x-www-form-urlencoded是就不要用SpringMVC的@RequestBody去绑定
    @RequestMapping(value = "/api/city", method = RequestMethod.PUT)
    public void modifyCity(@RequestBody City city) {
        cityService.updateCity(city);
    }

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.DELETE)
    public void modifyCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
    }
}
