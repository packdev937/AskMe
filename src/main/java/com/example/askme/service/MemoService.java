package com.example.askme.service;

import com.example.askme.domain.Memo;
import com.example.askme.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public void save(Memo memo) {
        memoRepository.save(memo);
    }

    public Memo findOne(Long memoId) {
        return memoRepository.findOne(memoId);
    }

    public List<Memo> findAll() {
        return memoRepository.findAll();
    }
}
