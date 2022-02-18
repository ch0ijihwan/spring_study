package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    @DisplayName("save() 실행 시, 파라미터로 부터입력 받은 멤버를 저장한다.")
    void save() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    @DisplayName("finaByName() 실행 시, 파라미터로부터 입력 받은 이름을 가진 멤버를 찾아 반환한다. ")
    void findByName() {
        //given
        Member member = new Member();
        member.setName("Spring");
        repository.save(member);

        Member otherMember = new Member();
        otherMember.setName("otherSpring");
        repository.save(otherMember);

        //when
        Member result = repository.findByName("Spring").get();

        //then
        assertThat(result).isEqualTo(member);
    }

    @Test
    @DisplayName("findAll() 호출 시, 모든 멤버 자료를 리스트화하여 반환한다.")
    void findAll() {
        //given
        Member member = new Member();
        member.setName("Spring");
        repository.save(member);
        Member otherMember = new Member();
        otherMember.setName("otherSpring");
        repository.save(otherMember);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
