package com.example.boardproject.controller;

import com.example.boardproject.domain.Board;
import com.example.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    @GetMapping("/list")
    public String findPaginated(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Board> boards = boardService.findPaginated(pageable);
        model.addAttribute("boards", boards);
        model.addAttribute("currentPage", page);

        return "list";
    }
}
