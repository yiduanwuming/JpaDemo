//package com.example.demo.util;
//
//import com.example.demo.contants.StudentErrorCode;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import javax.servlet.http.HttpServletRequest;
//import java.nio.file.AccessDeniedException;
//
///**
// * 自定义异常处理类
// * 针对不同的异常自定义不同的方法
// * 环绕通知
// * 切面:针对所有的controller中抛出的异常
// * 若使用@ControllerAdvice,则不会自动转换为JSON格式
// */
//@RestControllerAdvice
//public class RestExceptionHandler {
//
//    /**
//     * 业务异常处理
//     * @param e
//     * @return StudentErrorCode
//     */
//    @ExceptionHandler({StudentErrorCode.class})
//    public StudentErrorCode businessExceptionHandler(HttpServletRequest request, StudentErrorCode e) throws StudentErrorCode {
//        return new StudentErrorCode(e.getCode(), e.getMessage());
//    }
//
//    /**
//     * 业务异常处理
//     * @param e
//     * @return StudentErrorCode
//     */
//    @ExceptionHandler({AccessDeniedException.class})
//    public StudentErrorCode BusinessExceptionHandler(HttpServletRequest request, AccessDeniedException e) throws StudentErrorCode {
//        return new StudentErrorCode(401, e.getMessage());
//    }
//
//    /**
//     * 只要抛出该类型异常,则由此方法处理
//     * 并由此方法响应出异常状态码及消息
//     * 如:RoleController.getRoleById(String id)方法
//     * @param request
//     * @param e
//     * @return
//     * @throws Exception
//     */
//    @ExceptionHandler(value = Exception.class)
//    public StudentErrorCode handleException(HttpServletRequest request, Exception e) throws Exception {
//
//        StudentErrorCode body = new StudentErrorCode();
//        body.setCode(500);
//        body.setMessage(e.getMessage());
//
//        //可以根据公司情况不同，类似的异常可能需要不同的状态码
//        return body;
//    }
//
//}