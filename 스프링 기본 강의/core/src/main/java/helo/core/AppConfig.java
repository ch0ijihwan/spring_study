package helo.core;

import helo.core.discount.FixDiscountPolicy;
import helo.core.member.MemberService;
import helo.core.member.MemberServiceImpl;
import helo.core.member.MemoryMemberRepository;
import helo.core.order.OrderService;
import helo.core.order.OrderServiceIml;

public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceIml(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
