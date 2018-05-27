package xyz.gabear.exception;

/**
 * 秒杀相关异常
 * Created by xiong on 2018/5/27.
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
