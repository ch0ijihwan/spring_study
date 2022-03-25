package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void saveAll() {
        //given
        Member member = new Member("member", 20);
        Member otherMember = new Member("otherMember", 30);
        memberRepository.save(member);
        memberRepository.save(otherMember);

        //when
        int numberOfMembers = memberRepository.findAll().size();

        //then
        assertThat(numberOfMembers).isEqualTo(2);
    }
}