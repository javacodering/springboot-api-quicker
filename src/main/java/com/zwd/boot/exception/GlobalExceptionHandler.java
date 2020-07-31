package com.zwd.boot.exception;

/**
 * @author 随风逐梦
 * @create 2020-07-20 17:01
 */

import com.zwd.boot.common.result.ResponseVO;
import com.zwd.boot.common.enums.ResponseCodeEnum;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 统一异常处理
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseVO exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if (e instanceof CustomException) {
            CustomException ex = (CustomException) e;
            return ResponseVO.error(ex.getMessage());
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return ResponseCodeEnum.INVALID_PARAMS.formatArgs(msg);
        } else {
            return ResponseVO.error(ResponseCodeEnum.ERROR);
        }
    }
}
