package helo.core.discount;

import helo.core.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
