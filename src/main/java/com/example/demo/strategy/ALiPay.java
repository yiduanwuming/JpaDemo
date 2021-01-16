package com.example.demo.strategy;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service("aLiPay")
public class ALiPay implements PayStrategy {

    private static final Map<String, String> Data = new HashMap<>();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private boolean signedIn;
    private String aLiCount;
    private String password;

    static {
        Data.put("1098046904", "as123456");
    }

    @Override
    public boolean pay(int price) {
        if (signedIn) {
            System.out.println("支付宝付款"+ price +"元");
        }
        return false;
    }

    @Override
    public void collectPaymentDetails() {
        try {
            while (!signedIn) {
                System.out.print("请输入您的支付宝账号：");
                aLiCount = reader.readLine();
                System.out.print("请输入您的密码：");
                password = reader.readLine();
            }
            if (validated()) {
                System.out.println("登录成功");
            } else {
                System.out.println("账号或密码错误");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validated() {
        signedIn = password.equals(Data.get(aLiCount));
        return signedIn;
    }
}
