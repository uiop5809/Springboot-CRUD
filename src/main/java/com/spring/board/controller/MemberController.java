package com.spring.board.controller;

import com.spring.board.entity.Member;
import com.spring.board.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    // 로그아웃 기능
    @GetMapping("/member/logout")
    @ResponseBody
    public Map<String, Object> logout(HttpSession session) {
        HashMap<String, Object> result = new HashMap<>();

        Object object = session.getAttribute("member");
        String message = "";
        if(object == null) {
            message += "로그인 안 된 상태";
        } else {
            message += "로그인 된 상태";
        }

        // 브라우저만의 정보를 담은 session 지우고 다시 만듦
        // 이로써 session에 저장했던 모든 정보를 없애버리니 로그인 안 된 상태로 바꾸는 것
        session.invalidate(); // 로그인 정보 삭제
        result.put("code", "ok");
        result.put("message", message + "로그아웃완료");

        System.out.println(result);
        return result;
    }

    // 로그인 체크
    @GetMapping("/member/check_login")
    @ResponseBody
    public Map<String, Object> check_login(HttpSession session) {
        HashMap<String, Object> result = new HashMap<>();

        Object object = session.getAttribute("member");
        if (object == null) {
            result.put("code", "error");
            result.put("message", "Not Login");
        } else {
            result.put("code", "ok");
            result.put("message", "On Login");
            Member m = (Member)object;
            result.put("data", m); // 로그인된 사용자 정보
        }
        System.out.println(result);
        return result;
    }
}
