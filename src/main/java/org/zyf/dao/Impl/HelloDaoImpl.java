package org.zyf.dao.Impl;

import org.zyf.dao.HelloDao;

import java.util.Arrays;
import java.util.List;

/**
 * @author ZYF
 * @create 2021-4-2 11:23
 */
public class HelloDaoImpl implements HelloDao {
    @Override
    public List<String> findAll() {
        return Arrays.asList("1", "2", "3");
    }
}
