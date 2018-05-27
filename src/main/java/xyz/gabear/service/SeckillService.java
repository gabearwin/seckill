package xyz.gabear.service;

import xyz.gabear.dto.Exposer;
import xyz.gabear.dto.SeckillExecution;
import xyz.gabear.entity.Seckill;
import xyz.gabear.exception.RepeatKillException;
import xyz.gabear.exception.SeckillCloseException;
import xyz.gabear.exception.SeckillException;

import java.util.List;

/**
 * 业务接口：站在“使用者”的角度设计接口
 * 设计接口三个要点：方法的定义粒度；方法参数；返回类型return 值或异常
 * Created by xiong on 2018/5/27.
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     *
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     *
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时输出秒杀地址，否则输出当前系统时间和秒杀时间
     *
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作，md5是exposer中传过来的用于验证用户秒杀是否合法，比如秒杀地址被篡改等
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;
}
