package com.example.boardproject.controller;

import com.example.boardproject.domain.Board;
import com.example.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/view/{id}")
    public String findById(@PathVariable Long id, Model model){
        Board board = boardService.findById(id);
        model.addAttribute(board);
        return "view";
    }

    @GetMapping("/write")
    public String writeform(Model model){
        model.addAttribute("board", new Board());
        return "writeform";
    }

    @PostMapping("/write")
    public String saveBoard(@ModelAttribute Board board, RedirectAttributes redirectAttribute){
        boardService.save(board);
        redirectAttribute.addFlashAttribute("message","게시판 등록 성공!");
        return "redirect:/list";
    }

    @GetMapping("/deleteform/{id}")
    public String deleteBoard(@PathVariable Long id, Model model){
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "deletedform";
    }

    @PostMapping("/delete/{id}")
    public String verifyPassword(@PathVariable Long id, @RequestParam String password, RedirectAttributes redirectAttributes){
        Integer flag = boardService.verifyPassword(id, password);
        System.out.println(flag);
        if (flag == 1){
            boardService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "삭제 완료");
            return "redirect:/list";
        } else if (flag == 0) {
            redirectAttributes.addFlashAttribute("message", "비밀번호가 틀립니다.");
            return "redirect:/deleteform/{id}";
        }else{
            redirectAttributes.addFlashAttribute("message", "게시글이 이미 삭제됐거나 없습니다.");
            return "redirect:/list";
        }
    }
}
