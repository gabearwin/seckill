package xyz.gabear.dao;

import xyz.gabear.entity.Seckill;

import java.util.Date;
import java.util.List;

public interface SeckillDao {
    /**
     * 减库存
     *
     * @param seckillId 秒杀商品的id
     * @param killTime  秒杀时间，从前台传过来的
     * @return 影响的行数
     */
    int reduceNumber(long seckillId, Date killTime);

    /**
     * 根据id查询秒杀商品对象
     *
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(int offset, int limit);

}
