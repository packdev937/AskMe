package com.example.askme.controller;

import com.example.askme.domain.Memo;
import com.example.askme.dto.MemoDto;
import com.example.askme.service.MemoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @GetMapping("/memos/new")
    public String createMemo(Model model) {
        model.addAttribute("memoForm", new MemoDto());
        return "memos/CreateMemoForm";
    }

    @PostMapping("/memos/new")
    public String create(@Valid MemoDto memoDto) {
        Memo memo = new Memo();

        memo.setQuestion(memoDto.getQuestion());
        memo.setAnswer(memoDto.getAnswer());
        memo.setCreatedDate(LocalDateTime.now());

        memoService.save(memo);
        return "redirect:/";
    }
}
