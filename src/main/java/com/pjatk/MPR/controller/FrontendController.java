package com.pjatk.MPR.controller;

import com.pjatk.MPR.AlreadyExists;
import com.pjatk.MPR.model.Cow;
import com.pjatk.MPR.service.MyRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FrontendController {
    private final MyRestService restService;

    @Autowired
    public FrontendController(MyRestService restService) {
        this.restService = restService;
    }

//    @GetMapping("/cow/{name}")
//    public Cow getCowByName(@PathVariable("name") String name) {
//        return this.restService.getCowByName(name);
//    }
    @GetMapping("/showCow")
    public List<Cow> showCow() {
        return this.restService.getAllCows();
    }

    @GetMapping(value = "/cowNew")
    public String getAddView(Model model){
        model.addAttribute("cow",new Cow("", 10));
        return "cowNew";
    }

    @PostMapping(value = "/cowNew")
    public String cowNew(@ModelAttribute Cow cow) throws AlreadyExists {
        restService.addCow(cow);
        return "redirect:/welcome";
    }

//    @PostMapping("/cowNew")
//    public void cowNew(@RequestBody Cow cow) throws AlreadyExists {
//        this.restService.addCow(cow);
//    }

    @PutMapping("/editCow/{id}")
    public void cowEdit(){

    }

//    @DeleteMapping("/cowDel/{name}")
//    public void cowDel(@PathVariable("name") String name) {
//        this.restService.deleteCowByName(name);
//    }

    @GetMapping("/welcome")
    public String getViewAll(Model model){
        model.addAttribute("cows",restService.getAllCows());
        return "welcome";
    }
}