package xyz.gabear.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.gabear.entity.SuccessKilled;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {
        long id = 1000L;
        long phone = 12345678901L;
        int insertCount = successKilledDao.insertSuccessKilled(id, phone);
        System.out.println("insertCount = " + insertCount);
    }

    @Test
    public void queryByIdWithSeckill() {
        long id = 1000L;
        long phone = 12345678901L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}