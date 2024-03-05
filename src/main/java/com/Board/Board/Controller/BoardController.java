package com.Board.Board.Controller;

import com.Board.Board.Domain.Entity.Board;
import org.springframework.ui.Model;
import com.Board.Board.Dto.BoardDto;
import com.Board.Board.Service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    private BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

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

    @GetMapping("/article")
    public String article(){
        return "article";
    }

    @GetMapping("/register")
    public String register(){
        return "created";
    }

    @PostMapping("/register")
    public String register(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/list";
    }
}