package com.Board.Board.Controller;

import com.Board.Board.Domain.Entity.Board;
import com.Board.Board.Dto.BoardDto;
import com.Board.Board.Service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.access.AccessDeniedException;

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
        checkSession();
        return "board/created";
    }

    /*
     * 게시글 작성 post
     * @param boardDto 게시글 작성 정보
     * @return 게시글 목록 페이지
     */
    @PostMapping("/register")
    public String register(BoardDto boardDto){
        String userid = checkSession();

        boardService.savePost(boardDto, userid);
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
        String userid = checkSession();

        boardDto.setNum(num);
        boardService.savePost(boardDto, userid);
        return "redirect:/list/{id}";
    }

    /*
     * 게시글 삭제
     * @param id 게시글 ID
     * @return
     */
    @DeleteMapping("/list/remove/{id}")
    public String delete(@PathVariable("id") int num) {
        String loggedInUserId = checkSession();
        String postUserId = boardService.findUserIdByPostId(num);

        if (!loggedInUserId.equals(postUserId)) {
            throw new AccessDeniedException("삭제 권한이 없습니다.");
        }

        boardService.deletePost(num);
        return "redirect:/list";
    }

    private static String checkSession() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userid = "";

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;

            userid = userDetails.getUsername();
            String auth = userDetails.getAuthorities().toString();

            System.out.println("userid : " + userid);
            System.out.println("auth : " + auth);
        } else {
            System.out.println("인증되지 않은 사용자입니다.");
        }
        return userid;
    }
}