package com.ppmg.ei.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@Scope("prototype")
@ApiIgnore()
public class IndexController {


    @RequestMapping({"/", "/index"})
    public String index(Model model, String keyValues) {
        model.addAttribute("keyValues", keyValues);
        return "index";
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/platformInformation")
    public String platformInformation() {
        return "platformInformation";
    }

    @RequestMapping("/projectProgress")
    public String projectProgress() {
        return "projectProgress";
    }

    @RequestMapping("/fundName")
    public String fundName() {
        return "fundName";
    }

    @RequestMapping("/fundList")
    public String fundList() {
        return "fundList";
    }

    @RequestMapping("/projList")
    public String projList() {
        return "projList";
    }

    @RequestMapping("/fundPlat")
    public String fundPlat() {
        return "fundPlat";
    }

    @RequestMapping("/basicManage")
    public String basicManage() {
        return "basicManage";
    }


    @RequestMapping("/performance")
    public String performance() {
        return "performance";
    }

    /**
     *运营监控
     * @return
     */
    @RequestMapping("/provinceInvest")
    public String provinceInvest() {
        return "provinceInvest";
    }
}
