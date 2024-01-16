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
    @GetMapping("/showCow")
    public List<Cow> showCow() {
        return this.restService.getAllCows();
    }
    @GetMapping("/cowNew")
    public String getAddView(Model model){
        model.addAttribute("cow",new Cow(0,"", 0));
        return "cowNew";
    }

    @PostMapping(value = "/cowNew")
    public String cowNew(Model model,@ModelAttribute Cow cow){
        restService.addCow(cow);
        return "redirect:/welcome";
    }

    @GetMapping("/editCow/{id}")
    public String cowEdit(@PathVariable Long id, Model model){
        Cow cow = restService.getCowById(id);

        model.addAttribute("cow", cow);

        return "editCow";
    }
    @PostMapping("/editCow/{id}")
    public String cowEditSubmit(@PathVariable Long id, @ModelAttribute Cow cow){
        restService.editCow(id, cow);
        return "redirect:/welcome";
    }
    @PostMapping("/deleteCow/{id}")
    public String cowDel(@PathVariable Long id) {
        restService.deleteCow(id);

        return "redirect:/welcome";
    }
    @GetMapping("/welcome")
    public String getViewAll(Model model){
        model.addAttribute("cows",restService.getAllCows());
        return "welcome";
    }
}