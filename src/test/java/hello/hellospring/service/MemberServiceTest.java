package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void join() {
        //given 무언가 주어졌는데
        Member member = new Member();
        member.setName("spring");
        //when 무언가 실행 하였을때
        Long saveId = memberService.join(member);
        //then 결과가 주어져야함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
            memberService.join(member1);
            IllegalStateException e = assertThrows(IllegalStateException.class,() -> memberService.join(member2));
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            /*
        try {
            fail();
        }catch (IllegalStateException e){
            System.err.println(e.getMessage());
        }
*/
        //then
    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}