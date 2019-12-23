package com.larionov.epam;

import com.larionov.epam.service.DemoService;
import com.larionov.epam.service.impl.DemoServiceImpl;

public class Main {
    public static void main(String[] args) {
        DemoService demoService = new DemoServiceImpl();
        demoService.service("1 + ( 4 / 6 )");
    }
}
