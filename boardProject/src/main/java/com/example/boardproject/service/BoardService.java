package com.example.boardproject.service;

import com.example.boardproject.domain.Board;
import com.example.boardproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public Page<Board> findPaginated(Pageable pageable){
        Pageable sortedByDescId = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "created_at", "updated_at"));
        Page<Board> boards = boardRepository.findAll(sortedByDescId);
        boards.forEach(board -> board.setPassword(null));
        return boards;
    }

    public Board findById(Long id){
        Board board = boardRepository.findById(id).orElse(null);
        board.setPassword(null);
        return board;
    }

    public Board save(Board board){
        LocalDateTime now = LocalDateTime.now();
        board.setCreated_at(now);
        board.setUpdated_at(now );
        return boardRepository.save(board);
    }

    public Integer verifyPassword(Long id, String password){
        Board board = boardRepository.findById(id).orElse(null);

        if (board == null){
            return 2;
        }
        if (board.getPassword().equals(password)){
            return 1;
        }else{
            return 0;
        }
    }

    public void deleteById(Long id){
        boardRepository.deleteById(id);
    }
}
