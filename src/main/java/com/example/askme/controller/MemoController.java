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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

    @GetMapping("/memos")
    public String list(Model model) {
        model.addAttribute("memos", memoService.findMemos());
        return "memos/memoList";
    }

    @GetMapping("/memos/test")
    public String test(RedirectAttributes redirectAttributes) {
        List<Memo> memos = memoService.findMemos();

        // memo의 Id 값들을 리스트로 꺼내온 다음
        List<Long> memosId = memos.stream()
                .map(m -> m.getId())
                .collect(Collectors.toList());

        for (int i = 0; i < memosId.size(); i++) {
            System.out.println(memosId.get(i));
        }
        // 랜덤하게 memo의 ID를 참고해서 테스트할 메모를 가져오는것
        Random random = new Random();
        Long randomIdx = 0L;

        randomIdx = memosId.get(random.nextInt(memosId.size()));
        if (memoService.isCheckedTrue(randomIdx))
            return "redirect:/memos/test";
        else return "redirect:/memos/" + randomIdx;
    }

    @GetMapping("/memos/{id}")
    public String memo(@PathVariable(name = "id") Long id, Model model) {
        Memo memo = memoService.findOne(id);
        model.addAttribute("memo", memo);
        return "memos/memoDetail";
    }
}
