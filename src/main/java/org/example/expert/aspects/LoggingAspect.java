package org.example.expert.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Aspect
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ObjectMapper objectMapper;

    public LoggingAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Pointcut("@annotation(org.example.expert.aspects.RequestAndResponseInfoCheck)")
    public void requestAndResponseInfoCheckPointCut() {

    }

    @Around("requestAndResponseInfoCheckPointCut()")
    public Object adviceMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        // 현재 요청의 HttpServletRequest에 접근
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object[] args = joinPoint.getArgs();

        Long userId = (Long) request.getAttribute("userId");
        LocalDateTime requestTime = LocalDateTime.now();
        String requestUrl = request.getRequestURL().toString();
        String requestBody = objectMapper.writeValueAsString(args);

        Map<String, Object> results = new HashMap<>();

        results.put("userId", userId);
        results.put("requestTime", requestTime);
        results.put("requestUrl", requestUrl);
        results.put("requestBody", requestBody);

        Object result = joinPoint.proceed();

        String responseBody = objectMapper.writeValueAsString(result);
        results.put("responseBody", responseBody);

        log.info("API LOG - 요청한 사용자 ID: {}, API 요청 시각: {}, API 요청 URL: {}, Request Body: {}, Response Body: {}",
                results.get("userId"),
                results.get("requestTime"),
                results.get("requestUrl"),
                results.get("requestBody"),
                results.get("responseBody"));

        return result;
    }
}
