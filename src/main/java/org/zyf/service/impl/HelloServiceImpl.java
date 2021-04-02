package org.zyf.service.impl;

import org.zyf.factory.BeanFactory;
import org.zyf.service.HelloService;
import org.zyf.dao.HelloDao;

import java.util.List;

/**
 * @author ZYF
 * @create 2021-4-2 11:21
 */
public class HelloServiceImpl implements HelloService {

    public HelloServiceImpl() {
        for (int i = 0; i < 10; i++) {
            System.out.println(BeanFactory.getDao("helloDao"));
        }
    }

    private HelloDao helloDao = (HelloDao) BeanFactory.getDao("helloDao");

    @Override
    public List<String> findAll() {
        return this.helloDao.findAll();
    }
}
