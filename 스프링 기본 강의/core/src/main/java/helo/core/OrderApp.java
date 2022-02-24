package helo.core;

import helo.core.member.Grade;
import helo.core.member.Member;
import helo.core.order.Order;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        appConfig.memberService().join(member);
        Order order = appConfig.orderService().createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
    }
}
