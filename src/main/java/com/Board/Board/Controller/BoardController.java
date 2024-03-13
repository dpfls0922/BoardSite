package com.Board.Board.Controller;

import com.Board.Board.Domain.Entity.Board;
import com.Board.Board.Dto.BoardDto;
import com.Board.Board.Service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    private BoardService boardService;
    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    /*
     * 게시글 전체 목록 뷸러오기
     * @return 게시글 전체 목록 페이지
     */
    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardService.getAllBoards();
        for (Board board : boards) {
            logger.info("Board: {}", board);
        }
        model.addAttribute("boards", boardService.getAllBoardsReversed());
        return "board/list";
    }

    /*
     * 게시글 상세보기
     * @param id 게시글 ID
     * @return 게시글 상세보기 페이지
     */
    @GetMapping("/list/{id}")
    public String detail(@PathVariable("id") int num, Model model) {
        boardService.increaseHitCount(num);
        BoardDto boardDto = boardService.getBoard(num);
        model.addAttribute("board", boardDto);
        return "board/article";
    }

    /*
     * 게시글 작성
     * @return 게시글 작성 페이지
     */
    @GetMapping("/register")
    public String register(){
        return "board/created";
    }

    /*
     * 게시글 작성 post
     * @param boardDto 게시글 작성 정보
     * @return 게시글 목록 페이지
     */
    @PostMapping("/register")
    public String register(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/list";
    }

    /*
     * 게시글 수정
     * @param id 게시글 ID
     * @return 게시글 수정 페이지
     */
    @GetMapping("/list/edit/{id}")
    public String edit(@PathVariable("id") int num, Model model) {
        BoardDto boardDto = boardService.getBoard(num);
        model.addAttribute("board", boardDto);
        return "board/edit";
    }

    /*
     * 게시글 수정 post
     * @param id 게시글 ID
     * @param boardDto 게시글 수정 정보
     * @return 게시글 디테일 페이지
     */
    @PutMapping("/list/edit/{id}")
    public String edit(@PathVariable("id") int num, @ModelAttribute BoardDto boardDto) {
        boardDto.setNum(num);
        boardService.savePost(boardDto);
        return "redirect:/list/{id}";
    }

    /*
     * 게시글 삭제
     * @param id 게시글 ID
     * @return
     */
    @DeleteMapping("/list/remove/{id}")
    public String delete(@PathVariable("id") int num) {
        // 로그인한 유저의 id와 작성자의 id가 일치해야 삭제 가능하도록 로직 추가하기
        boardService.deletePost(num);
        return "redirect:/list";
    }
}