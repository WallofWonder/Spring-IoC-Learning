package org.zyf.dao.Impl;

import org.zyf.dao.HelloDao;

import java.util.Arrays;
import java.util.List;

/**
 * @author ZYF
 * @create 2021-4-2 11:23
 */
public class HelloDaoImpl2 implements HelloDao {
    @Override
    public List<String> findAll() {
        return Arrays.asList("7", "8", "9");
    }
}
