package xyz.gabear.dao;

import org.apache.ibatis.annotations.Param;
import xyz.gabear.entity.SuccessKilled;

public interface SuccessKilledDao {

    /**
     * 插入秒杀成功明细，由于数据库设计是id和userPhone联合唯一主键，所以可以过滤重复秒杀
     *
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);


    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     *
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
