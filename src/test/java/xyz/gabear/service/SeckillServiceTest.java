package xyz.gabear.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.gabear.dto.Exposer;
import xyz.gabear.dto.SeckillExecution;
import xyz.gabear.entity.Seckill;
import xyz.gabear.exception.RepeatKillException;
import xyz.gabear.exception.SeckillCloseException;
import xyz.gabear.exception.SeckillException;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xiong on 2018/5/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void getById() {
        long id = 1000L;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void exportSeckillUrl() {
        long id = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}", exposer);
        // exposer=Exposer{exposed=false, md5='null', seckillId=1000,
        // now=1527440041998, start=1526745600000, end=1526832000000}

        // exposer=Exposer{exposed=true, md5='bb662feb50357d5a97a31ff750412a31',
        // seckillId=1000, now=0, start=0, end=0}
    }

    @Test
    public void executeSeckill() {
        long id = 1000L;
        long phone = 15878093210L;
        String md5 = "bb662feb50357d5a97a31ff750412a31";
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
            logger.info("result = {}", seckillExecution);
        } catch (RepeatKillException e) {
            logger.error(e.getMessage());
        } catch (SeckillCloseException e) {
            logger.error(e.getMessage());
        } catch (SeckillException e) {
            logger.error(e.getMessage());
        }
    }

    // 集成测试代码完整逻辑，注意可重复执行
    @Test
    public void seckillLogic() {
        long id = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            long phone = 15878093210L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
                logger.info("result = {}", seckillExecution);
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            } catch (SeckillException e) {
                logger.error(e.getMessage());
            }
        } else {
            // 秒杀未开启
            logger.info("exposer={}", exposer);
        }
    }

}