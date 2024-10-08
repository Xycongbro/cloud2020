package com.itxushen.springcloud.controller;

import com.itxushen.springcloud.entities.CommonResult;
import com.itxushen.springcloud.entities.Payment;
import com.itxushen.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    public PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*****插入结果：" + result);
        if(result > 0){
            return new CommonResult(200, "插入数据库成功, serverPort: " + serverPort, result);
        }
        return new CommonResult(444, "插入数据库失败, serverPort: " + serverPort, null);
    }
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果：" + payment);
        if(payment != null){
            return new CommonResult(200, "查询成功, serverPort: " + serverPort, payment);
        }
        return new CommonResult(444, "没有对应记录，查询ID：" + id, null);
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
