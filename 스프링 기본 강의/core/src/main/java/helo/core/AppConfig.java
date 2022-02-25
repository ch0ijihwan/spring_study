package helo.core;

import helo.core.discount.DiscountPolicy;
import helo.core.discount.FixDiscountPolicy;
import helo.core.member.MemberRepository;
import helo.core.member.MemberService;
import helo.core.member.MemberServiceImpl;
import helo.core.member.MemoryMemberRepository;
import helo.core.order.OrderService;
import helo.core.order.OrderServiceIml;

public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceIml(memberRepository(), discountPolicy());
    }

    private DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
