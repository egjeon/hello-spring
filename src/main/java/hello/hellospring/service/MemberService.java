package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
       회원가입
     */

    public Long join(Member member){
        //같은 이름이 있는 중복 회원X
        validateDupicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDupicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    /*
     전체회원 조회
     */
    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    /*
     ID로 회원 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
