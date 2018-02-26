package com.example.demo.collection;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


/**
 * @author lirong
 * @create 2018/2/25
 */
public class CollectionTest {

    public static void main(String[] args) {

//        test2();
        test3();
    }

//用collection的sort(Comparator<? super E> c)对集合进行排序
    public static void test2(){
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("c");
        list.add("f");
        list.add("b");
        list.sort((a, b) -> a.compareTo(b));
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.printf(s);
        }
    }
//用Collections.sort(list);对集合进行标准排序
//快捷键 iter 生成增强  itit  生成iterator 迭代 itar 生成array for代码块 fori普通循环
    public static void test3(){
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("4");
        list.add("2");
        list.add("3");

        //标准比较
//        Collections.sort(list);
        //可以自己写比较
        Collections.sort(list, (a, b) -> a.compareTo(b));
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.printf(s);
        }
    }

}
