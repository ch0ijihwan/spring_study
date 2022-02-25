package helo.core;

import helo.core.discount.DiscountPolicy;
import helo.core.discount.RateDiscountPolicy;
import helo.core.member.MemberRepository;
import helo.core.member.MemberService;
import helo.core.member.MemberServiceImpl;
import helo.core.member.MemoryMemberRepository;
import helo.core.order.OrderService;
import helo.core.order.OrderServiceIml;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceIml(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
