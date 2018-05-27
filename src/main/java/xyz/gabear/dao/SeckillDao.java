package xyz.gabear.dao;

import org.apache.ibatis.annotations.Param;
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
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀商品对象
     *
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * <p>
     * 注意：这里因为Java都是形参传递，所以用MyBatis的注解来指定参数名字
     *
     * @param offset 偏移量
     * @param limit  取多少条
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

}
