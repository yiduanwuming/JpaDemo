package com.example.demo.strategy;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Supermarket {
    private static Map<Integer, Integer> priceOnGoods = new HashMap<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Order order = new Order();
    private static PayStrategy payStrategy;

    static {
        priceOnGoods.put(1, 1350);
    }

    public static void main(String[] args) {
        try {
            while (!order.isClosed()) {
                int cost = 0;
                String choose;
                do {
                    System.out.print("请选择您要购买的英雄：" + "\n" +
                            "1 - 无畏战车"+ "\n" +
                            "2 - 影流之主" + "\n");
                    String s = reader.readLine();
                    if (!StringUtils.isEmpty(s) && s.matches("^\\d+$")){
                        int i = Integer.parseInt(s);
                        cost = priceOnGoods.get(i);
                    }
                    System.out.print("数量为：");
                    int count = 0;
                    String countStr = reader.readLine();
                    if (!StringUtils.isEmpty(countStr) && countStr.matches("^\\d+$")){
                        count = Integer.parseInt(countStr);
                    }
                    order.setTotalCost(cost * count);
                    System.out.print("是否继续购物");
                    choose = reader.readLine();
                } while ("Y".equals(choose));

                if (payStrategy != null) {
                    System.out.println("请选择您的支付方式：\n"
                    +"1.支付宝\n"
                    +"2.微信\n");
                    String payWay = reader.readLine();
                    if ("1".equals(payWay)) {
                        payStrategy = new ALiPay();
                    } 
                    order.processOrder(payStrategy);

                    System.out.println("您需要支付了" + order.getTotalCost() + "元，是否继续购物？");
                    String want = reader.readLine();
                    if ("Y".equals(want)) {
                        if (payStrategy.pay(order.getTotalCost())) {
                            System.out.println("支付成功！");
                        } else {
                            System.out.println("没钱你买个锤子啊，滚");
                        }
                        order.setClosed(true);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
