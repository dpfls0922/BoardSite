package com.Board.Board.Controller;

import com.Board.Board.Domain.Entity.Board;
import org.springframework.ui.Model;
import com.Board.Board.Dto.BoardDto;
import com.Board.Board.Service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    private BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    // 전체 목록 뷸러오기
    @GetMapping("/list")
    public String list(Model model){
        // 로깅 확인
        List<Board> boards = boardService.getAllBoards();
        for (Board board : boards) {
            logger.info("Board: {}", board);
        }
        model.addAttribute("boards", boardService.getAllBoardsReversed());
        return "list";
    }
    // 상세 보기
    @GetMapping("/list/{id}")
    public String detail(@PathVariable("id") int num, Model model) {
        boardService.increaseHitCount(num);
        BoardDto boardDto = boardService.getBoard(num);
        model.addAttribute("board", boardDto);
        return "article";
    }

    // 생성
    @GetMapping("/register")
    public String register(){
        return "created";
    }

    @PostMapping("/register")
    public String register(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/list";
    }

    // 수정
    @GetMapping("/list/edit/{id}")
    public String edit(@PathVariable("id") int num, Model model) {
        BoardDto boardDto = boardService.getBoard(num);
        model.addAttribute("board", boardDto);
        return "edit";
    }

    @PutMapping("/list/edit/{id}")
    public String edit(@PathVariable("id") int num, @ModelAttribute BoardDto boardDto) {
        boardDto.setNum(num);
        boardService.savePost(boardDto);
        return "redirect:/list/{id}";
    }

    @DeleteMapping("/list/{id}")
    public String delete(@PathVariable("id") int num) {
        boardService.deletePost(num);
        return "redirect:/list";
    }
}