package br.com.jsoncall.exception;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1488696850517418398L;

	private int code;

    private String reason;

    public ServiceException() {
        super();
    }

    public ServiceException(int code, String reason) {
        super(reason);
        this.code = code;
        this.reason = reason;
    }

}
