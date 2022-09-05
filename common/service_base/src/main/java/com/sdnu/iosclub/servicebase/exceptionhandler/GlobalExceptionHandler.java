package com.sdnu.iosclub.servicebase.exceptionhandler;


import com.sdnu.iosclub.serviceutil.ExceptionUtil;
import com.sdnu.iosclub.serviceutil.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
@Slf4j
public class  GlobalExceptionHandler {

    //处理类型参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R exceptionHandler(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            if (errors != null) {
                errors.forEach(p -> {
                    FieldError fieldError = (FieldError) p;
                    log.warn("Bad Request Parameters: dto entity [{}],field [{}],message [{}]",fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
                    stringBuilder.append(fieldError.getDefaultMessage());
                });
            }
        }
        return R.error().message(stringBuilder.toString());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public R messageExceptionHandler(HttpMessageNotReadableException e) {
        log.warn("http请求参数转换异常: "+ e.getMessage());
        return R.error().message("http请求参数转换异常");
    }

    // 指定出现了什么异常才会调用次方法
    @ExceptionHandler(Exception.class)
    @ResponseBody  // 为了能够返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }

    // 特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody  // 为了能够返回数据
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理");
    }

    // 自定义异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody  // 为了能够返回数据
    public R error(CustomException e) {
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
