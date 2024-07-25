package com.spring.board.controller;

import com.spring.board.entity.Member;
import com.spring.board.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepo;

    // 로그인 기능
    @PostMapping("/member/login")
    @ResponseBody
    public Map<String, Object> login(
            @RequestParam("id") String id,
            @RequestParam("pw") String pw,
            HttpSession session){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "error");

        Optional<Member> member = memberRepo.findById(id);
        if(member.isPresent()) {
            Member m = member.get();
            if(m.getPw().equals(pw)) {
                result.put("code", "ok");
                result.put("message", "로그인완료");
                session.setAttribute("member", m);
            } else {
                result.put("message", "암호 틀림");
            }
        } else {
            result.put("message", "업거나 삭제된 id");
        }
        System.out.println(result);
        return result;
    }
}
