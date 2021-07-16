package com.gao.controller;

import com.gao.pojo.TestInput;
import com.gao.service.TestInputService;
import com.gao.utils.Shell;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Controller
public class TestInputController {
    private String host;
    private String user;
    private String password;
    private int port;


    // controller 调用service层
    @Autowired
    @Qualifier("TestInputService")
    private TestInputService testInputService;

    @RequestMapping("/allTestInput")
    public String allTestInput(Model model){
        List<TestInput> list = testInputService.queryAllTestInput();
        model.addAttribute("list", list);
        return "allTestInput";
    }


    @RequestMapping("/toAllTestInput")
    //查询全部测试用例
    public String list(@RequestParam("host") String host,
                       @RequestParam("user") String user,
                       @RequestParam("password") String password,
                       Model model) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.port = 22;
        return "redirect:allTestInput";
    }

    @RequestMapping("/toAddTestInput")
    public String toAddBook() {
        return "addTestInput";
    }

    @RequestMapping("/addTestInput")
    public String addTestInput(@RequestParam("name") String name,
                               @RequestParam("input") CommonsMultipartFile input,
                               @RequestParam("output") CommonsMultipartFile output,
                               @RequestParam("permissibleError") double permissibleError,
                               @RequestParam("run") String[] strings,
                               @RequestParam("mpi") String mpi,
                               @RequestParam("omp") String omp) throws IOException {
        TestInput testInput = generateTestInput(1, name, input, output, strings, mpi, omp, permissibleError);
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
    public String updateTestInput(@RequestParam("id") int id,
                                  @RequestParam("name") String name,
                                  @RequestParam("input") CommonsMultipartFile input,
                                  @RequestParam("output") CommonsMultipartFile output,
                                  @RequestParam("run") String[] strings,
                                  @RequestParam("mpi") String mpi,
                                  @RequestParam("omp") String omp,
                                  @RequestParam("permissibleError") double permissibleError) throws IOException {
        TestInput testInput = generateTestInput(id, name, input, output, strings, mpi, omp, permissibleError);
        testInputService.updateTestInput(testInput);
        return "redirect:/allTestInput";
    }

    @RequestMapping("/deleteTestInput")
    public String deleteTestInput(int id) {
        testInputService.deleteTestInput(id);
        return "redirect:/allTestInput";
    }

    @RequestMapping("/selectTestInputByName")
    public String selectByName(String name, Model model) {
        List<TestInput> list = testInputService.queryTestInputByName(name);
        model.addAttribute("list", list);
        return "allTestInput";
    }

    @RequestMapping("/executeTestCase")
    public String executeTestCase(int id, Model model) throws IOException {
        List<TestInput> list = testInputService.queryAllTestInput();
        TestInput testInput = testInputService.queryTestInputByID(id);
        System.out.println(testInput);
        File file = new File("./"+testInput.getInputFileName());
        file.createNewFile();
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(testInput.getInput().getBytes(StandardCharsets.UTF_8));
        outputStream.close();





        Shell.execCmd("ls -a >> "+"~/"+testInput.getInputFileName(),this.user,this.password,this.host,this.port);
        model.addAttribute("executeResult", "success");
        model.addAttribute("executeId", id);
        model.addAttribute("list", list);
        return "allTestInput";
    }

    public TestInput generateTestInput(int id,
                                       String name,
                                       CommonsMultipartFile input,
                                       CommonsMultipartFile output,
                                       String[] strings,
                                       String mpi,
                                       String omp,
                                       double permissibleError) throws IOException {
        String inputFileName = input.getOriginalFilename();
        String inputString = IOUtils.toString(input.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
        String outputFileName = output.getOriginalFilename();
        String outputString = IOUtils.toString(output.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
        String runCommand;
        if (Arrays.asList(strings).contains("mpi") && Arrays.asList(strings).contains("omp")) {
            runCommand = "mpirun" + " -n " + mpi + " --allow-run-as-root" + " mcx" + " --threads " + omp;
        } else if (Arrays.asList(strings).contains("mpi")) {
            runCommand = "mpirun" + " -n " + mpi + " --allow-run-as-root" + " mcx";
        } else if (Arrays.asList(strings).contains("omp")) {
            runCommand = "mcx" + " --threads " + omp;
        } else {
            runCommand = "mcx";
        }
        if (Arrays.asList(strings).contains("plot")) {
            runCommand = runCommand + " -p";
        }
        runCommand = runCommand + " " + inputFileName;
        TestInput case1 = new TestInput(1, name, inputFileName, inputString, outputFileName, outputString, permissibleError, runCommand);
        return case1;
    }
}
