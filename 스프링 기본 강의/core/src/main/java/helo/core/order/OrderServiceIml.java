package helo.core.order;

import helo.core.discount.DiscountPolicy;
import helo.core.discount.FixDiscountPolicy;
import helo.core.member.Member;
import helo.core.member.MemberRepository;
import helo.core.member.MemoryMemberRepository;

public class OrderServiceIml implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
