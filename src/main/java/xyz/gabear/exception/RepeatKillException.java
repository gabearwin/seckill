package xyz.gabear.exception;

/**
 * 重复秒杀异常，运行时异常
 * Created by xiong on 2018/5/27.
 */
public class RepeatKillException extends SeckillException {
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
