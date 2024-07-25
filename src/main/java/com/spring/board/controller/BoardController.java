package com.spring.board.controller;

import com.spring.board.entity.Board;
import com.spring.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepo;

    @GetMapping("/board/count")
    @ResponseBody
    public Map<String, Object> count(){
        Map<String, Object> result = new HashMap<>();
        long count = boardRepo.count();
        result.put("code", "ok");
        result.put("data", count);
        return result;
    }

    @GetMapping("/board/list")
    @ResponseBody
    public Map<String, Object> list(){
        Map<String, Object> result = new HashMap<>();
        List<Board> list = boardRepo.findAll(Sort.by(Sort.Direction.DESC, "no"));
        result.put("code", "ok");
        result.put("data", list);
        return result;
    }

    @GetMapping("/board/detail/{no}")
    @ResponseBody
    public Map<String, Object> detail(@PathVariable("no") int no) {
        Map<String, Object> result = new HashMap<>();
        Optional<Board> board = boardRepo.findById(no);
        if(board.isPresent()) {
            result.put("code", "ok");
            result.put("data", board);
        } else {
            result.put("code", "error");
            result.put("message", "없거나 삭제된 번호입니다.");
        }
        return result;
    }

    @PostMapping("/board/regist")
    @ResponseBody
    public Map<String, Object> regist(Board board) {
        Map<String, Object> result = new HashMap<>();
        Date date = new java.util.Date();
        board.setRegdate(date);
        boardRepo.save(board);
        result.put("code", "ok");
        return result;

    }
}
