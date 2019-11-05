package tk.fishfish.rest;

/**
 * 业务异常
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class BizException extends RuntimeException {

    private final Integer code;

    public BizException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public BizException(Integer code, String msg, Throwable throwable) {
        super(msg, throwable);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
