package com.nju.edu.gtms;

import com.nju.edu.gtms.dao.AccountDao;
import com.nju.edu.gtms.dao.StudentDao;
import com.nju.edu.gtms.dao.TeacherDao;
import com.nju.edu.gtms.model.po.StudentPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GtmsApplicationTests {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TeacherDao teacherDao;
    @Test
    void contextLoads() {
        System.out.println(studentDao.findOneById("201250187"));
        System.out.println(accountDao.findByAccountAndPassword("201250187","11111111"));
        System.out.println(teacherDao.findOneById("teacher1"));
    }

}
