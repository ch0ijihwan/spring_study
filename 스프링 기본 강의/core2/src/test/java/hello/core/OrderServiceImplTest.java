package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceImplTest {

    @Test
    void createOrder(){
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        Member userA = new Member(1L, "userA", Grade.VIP);
        memoryMemberRepository.save(userA);

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(),new FixDiscountPolicy());
        Order itemA = orderService.createOrder(1L, "itemA", 10000);
        assertThat(itemA.getDiscountPrice()).isEqualTo(1000);
    }
}
