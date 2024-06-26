package com.kh.works.board.controller;

import com.kh.works.board.service.BoardService;
import com.kh.works.board.vo.BoardVo;
import com.kh.works.security.EmpSessionVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

    private final BoardService service;

    //게시물 작성하기 화면
    @GetMapping("write")
    public String wirte(){
        return "board/write";
    }

    //게시물 작성하기 백엔드
    @PostMapping("write")
    public String write(BoardVo vo, @AuthenticationPrincipal EmpSessionVo loginEmployeeVo){
        String empNo = loginEmployeeVo.getNo();
        vo.setEmpNo(empNo);
        int result = service.write(vo);
        if (result != 1){
            return "common/error";
        }
        return "redirect:/board/list";
     }

     //게시물 리스트 화면
     @GetMapping("list")
    public String showBoardList(){
        return "board/list";
     }

     //게시물 데이터
    @GetMapping("api/list")
    @ResponseBody
    public List<BoardVo> getBoardList(){
        return service.getBoardList();
    }
    
    //내 게시물 화면
    @GetMapping("mylist")
    public String getMyList(){
        return "board/mylist";
    }

    //내 게시물 데이터
    @GetMapping("api/mylist")
    @ResponseBody
    public List<BoardVo> myBoardList( @AuthenticationPrincipal EmpSessionVo loginEmployeeVo){
        String empNo = loginEmployeeVo.getNo();
        return service.myBoardList(empNo);
    }

//    게시물 상세조회 화면
    @GetMapping("/detail")
    public String getdetailBoard(@RequestParam("boardNo")String boardNo ,Model model) {

        return "board/detail";
    }

    @GetMapping("api/detail")
    @ResponseBody
    public BoardVo apiDetailBoard(@RequestParam("boardNo")String boardNo ,Model model){
        BoardVo vo = service.getBoardDetail(boardNo);
        System.out.println("Controller VO" + vo);
        model.addAttribute("boardNo" , boardNo);
        model.addAttribute("board" , vo);
        return vo;
    }

}
