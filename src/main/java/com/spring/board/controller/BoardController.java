package com.spring.board.controller;

import com.spring.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BoardController {

    @Autowired
    private BoardRepository brdRepo;

    @GetMapping("/board/count")
    @ResponseBody
    public Map<String, Object> count(){
        Map<String, Object> result = new HashMap<>();
        long count = brdRepo.count();
        result.put("code", "ok"); // 정상 ok, 오류 error
        result.put("data", count);
        return result;
    }
}
