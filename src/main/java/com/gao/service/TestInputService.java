package com.gao.service;

import com.gao.pojo.TestInput;


import java.util.List;

public interface TestInputService {
    //增加测试用例
    int addTestInput(TestInput testInput);
    //删除测试用例
    int deleteTestInput(int id);
    //更新测试用例
    int updateTestInput(TestInput testInput);
    //查询测试
    List<TestInput> queryAllTestInput();
    //    由名字查询测试
    List<TestInput> queryTestInputByName(String name);

    TestInput queryTestInputByID(int id);
}
