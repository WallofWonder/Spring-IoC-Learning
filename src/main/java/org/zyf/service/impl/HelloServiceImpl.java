package org.zyf.service.impl;

import org.zyf.service.HelloService;
import org.zyf.service.dao.HelloDao;
import org.zyf.service.dao.Impl.HelloDaoImpl;

import java.util.List;

/**
 * @author ZYF
 * @create 2021-4-2 11:21
 */
public class HelloServiceImpl implements HelloService {

    private HelloDao helloDao = new HelloDaoImpl();

    @Override
    public List<String> findAll() {
        return this.helloDao.findAll();
    }
}
