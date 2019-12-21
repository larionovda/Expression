package com.larionov.epam;

import com.larionov.epam.service.DemoService;
import com.larionov.epam.service.impl.DemoServiceImpl;

public class Main {
    public static void main(String[] args) {
        DemoService demoService = new DemoServiceImpl();
        demoService.service("(40+50)*30+30/20+(40-30)");
    }
}
