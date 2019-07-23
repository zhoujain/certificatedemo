package com.zhoujian.controller;

import com.zhoujian.domain.Menu;
import com.zhoujian.service.CertificateService;
import com.zhoujian.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class MenuController {
    @Resource(name="menuService")
    private MenuService menuService;

}
