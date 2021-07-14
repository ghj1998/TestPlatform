package com.gao.service;

import com.gao.dao.TestInputMapper;
import com.gao.pojo.TestInput;
import org.junit.Test;

import java.util.List;

public class TestInputServiceImpl implements TestInputService {

    private TestInputMapper testInputMapper;

    @Override
    public int addTestInput(TestInput testInput) {
        return testInputMapper.addTestInput(testInput);
    }

    @Override
    public int deleteTestInput(int id) {
        return testInputMapper.deleteTestInput(id);
    }

    @Override
    public int updateTestInput(TestInput testInput) {
        return testInputMapper.updateTestInput(testInput);
    }

    @Override
    public List<TestInput> queryAllTestInput() {
        return testInputMapper.queryAllTestInput();
    }

    @Override
    public List<TestInput> queryTestInputByName(String name) {
        return testInputMapper.queryTestInputByName("%"+name+"%");
    }

    @Override
    public TestInput queryTestInputByID(int id) {
        return testInputMapper.queryTestInputByID(id);
    }


    public void setTestInputMapper(TestInputMapper testInputMapper) {
        this.testInputMapper = testInputMapper;
    }
}


