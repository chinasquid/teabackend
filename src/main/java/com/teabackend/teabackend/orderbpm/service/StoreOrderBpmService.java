package com.teabackend.teabackend.orderbpm.service;

import com.teabackend.teabackend.orderbpm.controller.StoreOrderBpmController;
import com.teabackend.teabackend.orderbpm.dao.StoreOrderBpmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-18 10:11
 */
@Service
public class StoreOrderBpmService {
    @Autowired
    private StoreOrderBpmDao bpmDao;
}
