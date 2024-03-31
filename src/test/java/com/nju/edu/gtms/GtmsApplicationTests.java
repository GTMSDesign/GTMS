package com.nju.edu.gtms;

import com.nju.edu.gtms.dao.StudentDao;
import com.nju.edu.gtms.model.po.StudentPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GtmsApplicationTests {

    @Autowired
    private StudentDao studentDao;
    @Test
    void contextLoads() {
        System.out.println(studentDao.findOneByNumber("12345678"));
    }

}
