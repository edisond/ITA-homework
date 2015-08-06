package com.oocl.o2o.dao;



import java.util.List;

import com.oocl.o2o.pojo.Status;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public interface StatusDao {
    Status getById(Integer statusId);

    List<Status> findAll();
}
