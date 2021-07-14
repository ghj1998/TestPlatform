package com.gao.controller;

import com.gao.pojo.TestInput;
import com.gao.service.TestInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestInputController {
    // controller 调用service层
    @Autowired
    @Qualifier("TestInputService")
    private TestInputService testInputService;

    @RequestMapping("/allTestInput")
    //查询全部测试用例
    public String list(Model model) {
        List<TestInput> list = testInputService.queryAllTestInput();
        model.addAttribute("list", list);
        return "allTestInput";
    }

    @RequestMapping("/toAddTestInput")
    public String toAddBook() {
        return "addTestInput";
    }

    @RequestMapping("/addTestInput")
    public String addTestInput(HttpServletRequest request, TestInput testInput) {
        System.out.println("addBook=>" + testInput);
        testInputService.addTestInput(testInput);
        return "redirect:/allTestInput";
    }

    @RequestMapping("/toUpdateTestInput")
    public String toUpdateTestInput(int id, Model model) {
        TestInput testInput = testInputService.queryTestInputByID(id);
        model.addAttribute("testInput", testInput);
        return "updateTestInput";
    }

    @RequestMapping("/updateTestInput")
    public String updateTestInput(TestInput testInput) {
        System.out.println(testInput);
        testInputService.updateTestInput(testInput);
        return "redirect:/allTestInput";
    }

    @RequestMapping("/deleteTestInput")
    public String deleteTestInput(int id) {
        testInputService.deleteTestInput(id);
        return "redirect:/allTestInput";
    }

    @RequestMapping("/selectTestInputByName")
    public String selectByName(String name,Model model){
        List<TestInput> list = testInputService.queryTestInputByName(name);
        model.addAttribute("list",list);
        return "allTestInput";
    }
    @RequestMapping("/executeTestCase")
    public String executeTestCase(int id,Model model){
        List<TestInput> list = testInputService.queryAllTestInput();
        model.addAttribute("executeResult","success");
        model.addAttribute("executeId",id);
        model.addAttribute("list",list);
        return "allTestInput";
    }
}
