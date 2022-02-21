package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("join() 실행 시 회원 가입이 제대로 되는지 확인한다.")
    void join() {
        //given
        Member member = new Member();
        member.setName("apple");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    @DisplayName("join() 실행 시, 이미 존재하는 회원이라면 예외처리를 반환한다.")
    void validateDuplicateMembers() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        memberService.join(member1);

        //then
        assertThatThrownBy(() -> memberService.join(member2)).isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 존재하는 회원입니다.");

    }
}