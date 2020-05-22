package com.teabackend.teabackend.orderbpm.controller;

import com.teabackend.teabackend.orderbpm.service.StoreOrderBpmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-18 10:10
 */
@RestController
public class StoreOrderBpmController {
    @Autowired
    private StoreOrderBpmService bpmService;


}
