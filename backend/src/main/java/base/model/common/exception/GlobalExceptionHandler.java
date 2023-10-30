package base.model.common.exception;


import cn.dev33.satoken.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<ProblemDetail> exception(Exception e) {
        log.error("拦截器错误", e);
        return createProblem("内部错误", null);
    }

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<ProblemDetail> exception(BadRequestException e) {
        return createProblem(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    @ExceptionHandler(NotLoginException.class)
    ResponseEntity<ProblemDetail> exception(NotLoginException e) {
        return createProblem("登录信息不存在", null, HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<ProblemDetail> exception(IllegalStateException e) {
        return createProblem(e.getMessage(), null);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    ResponseEntity<ProblemDetail> exception(HttpClientErrorException.Forbidden e) {
        return createProblem("禁止访问", null);
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    ResponseEntity<ProblemDetail> exception(HttpClientErrorException.Unauthorized e) {
        return createProblem("未通过授权", null);
    }

    private ResponseEntity<ProblemDetail> createProblem(String title, String detail, int status) {
        ProblemDetail p = ProblemDetail.forStatus(HttpStatus.valueOf(status));
        p.setTitle(title);
        p.setDetail(detail);
        return ResponseEntity.of(p).build();
    }

    private ResponseEntity<ProblemDetail> createProblem(String title, String detail) {
        return createProblem(title, detail, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
