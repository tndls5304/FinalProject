package com.kh.works.board.controller;

//import com.kh.works.security.EmpSessionVo;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.kh.works.board.service.BoardService;
import com.kh.works.board.vo.BoardVo;
import com.kh.works.board.vo.CommentVo;
import com.kh.works.board.vo.WishBoardVo;
import com.kh.works.employee.vo.EmployeeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

    private final BoardService service;

    //게시물 작성하기 화면
    @GetMapping("write")
    public String wirte() {
        return "board/write";
    }

    //게시물 작성하기 백엔드
    @PostMapping("write")
    public String write(BoardVo vo, HttpSession session) {

        //추가했어요 !!!!
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");

        //추가했어요!!!!
        String empNo = loginEmpVo.getNo();
        //String empNo = loginEmployeeVo.getNo();
        vo.setEmpNo(empNo);
        int result = service.write(vo);
        if (result != 1) {
            return "common/error";
        }
        return "redirect:/board/list";
    }

    //게시물 리스트 화면
    @GetMapping("list")
    public String showBoardList() {
        return "board/list";
    }

    //게시물 데이터
    @GetMapping("api/list")
    @ResponseBody
    public List<BoardVo> getBoardList() {
        return service.getBoardList();
    }

    //내 게시물 화면
    @GetMapping("myList")
    public String getMyList() {
        return "board/mylist";
    }

    //내 게시물 데이터
    @GetMapping("api/mylist")
    @ResponseBody
    public List<BoardVo> myBoardList(HttpSession session) {
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String empNo = loginEmpVo.getNo();
        return service.myBoardList(empNo);
    }

    //내게시물 상세 화면
    @GetMapping("myList/detail")
    public String getMyListDetail(){
        return "board/myDetail";
    }

    //내게시물 상세 데이터
    @GetMapping("api/myList/detail")
    @ResponseBody
    public BoardVo myListDetail(@RequestParam("boardNo") String boardNo ,HttpSession session){
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String empNo = loginEmpVo.getNo();
        BoardVo voList = service.myListDetail(boardNo ,empNo);
        return voList;
    }

    //게시물 상세조회 화면
    @GetMapping("/detail")
    public String getdetailBoard(@RequestParam("boardNo") String boardNo, Model model, HttpSession session) {
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String loginNo = loginEmpVo.getNo();
        model.addAttribute("empNo", loginNo);
        return "board/detail";
    }

    @GetMapping("api/detail")
    @ResponseBody
    public BoardVo apiDetailBoard(@RequestParam("boardNo") String boardNo, HttpSession session) {
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String loginNo = loginEmpVo.getNo();
        BoardVo vo = service.getBoardDetail(boardNo ,loginNo);
        return vo;
    }

    //수정하기 화면 보여주기
    @GetMapping("edit")
    public String editView() {
        return "board/edit";
    }

    //수정하기
    @PostMapping("edit")
    public String editBoard(BoardVo vo, @RequestParam("boardNo") String boardNo) {
        System.out.println(vo + boardNo);
        int result = service.editBoard(vo, boardNo);
        if (result != 1) {
            return "common/error";
        }
        return "redirect:/board/list";
    }

    //삭제하기
    @GetMapping("delete")
    public String deleteBoard(@RequestParam("boardNo") String boardNo) {

        int result = service.deleteBoard(boardNo);

        if (result != 1) {
            return "common/error";
        }
        return "redirect:/board/list";
    }

    @PostMapping("/search")
    @ResponseBody
    public List<BoardVo> searchTitleOrEmpName(@RequestParam(value = "title", required = false) String title,
                                              @RequestParam(value = "empName", required = false) String empName) {
        List<BoardVo> voList = null;
        if (title != null) {
            voList = service.searchTitle(title);
        } else if (empName != null) {
            voList = service.searchEmpName(empName);
        }
        return voList;
    }


    @GetMapping("checkWishList")
    public ResponseEntity<Map<String, Boolean>> checkWishList(WishBoardVo vo, HttpSession session , @RequestParam("boardNo") String boardNo){
        // 로그인 멤버 형변환해서 담아주기
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String loginNo = loginEmpVo.getNo();
        int empNo = Integer.parseInt(loginNo);

        int board = Integer.parseInt(boardNo);
        vo.setBoardWishNo(board);
        vo.setEmpNo(empNo);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~`empNo는 =" + empNo);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~`boardNo =" + vo.getBoardWishNo());
        // 불린으로 판단
        boolean wishList = service.checkWishList(vo);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~`vo 를 갔다고오는 empNo는 =" + empNo);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~`vo 를 갔다고오는  boardNo =" + vo.getBoardWishNo());
        Map<String, Boolean> response = new HashMap<>();
        response.put("wishList", wishList);
        return ResponseEntity.ok(response);
    }


    //좋아요 누르면 디비에 저장되게하기
    @PostMapping("wishList")
    @ResponseBody
    public int wishBoard(WishBoardVo vo , HttpSession session){
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String loginNo = loginEmpVo.getNo();
        int empNo = Integer.parseInt(loginNo);
        vo.setEmpNo(empNo);
        int result = service.wishBoard(vo);
        return result;
    }

    @PostMapping("wishList/cancle")
    @ResponseBody
    public int wishCancleBoard(WishBoardVo vo , HttpSession session){
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String loginNo = loginEmpVo.getNo();
        int empNo = Integer.parseInt(loginNo);
        vo.setEmpNo(empNo);
        int result = service.wishCancleBoard(vo);
        return  result;
    }

    //좋아요 목록 보여주기
    @GetMapping("api/wishList/mylist")
    @ResponseBody
    public List<WishBoardVo> myWishList(HttpSession session){

        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String loginNo = loginEmpVo.getNo();
        int empNo = Integer.parseInt(loginNo);
        WishBoardVo vo = new WishBoardVo();
        vo.setEmpNo(empNo);
        List<WishBoardVo> voList = service.myWishList(vo);
        return voList;
    }

    @GetMapping("wishList/mylist")
    public String myWishListView(){
        return "board/wishList";
    }

    @PostMapping("comment")
    @ResponseBody
    public int commentWrite(CommentVo vo , HttpSession session , @RequestParam("boardNo") String boardNo){
        System.out.println("테스트");
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String loginNo = loginEmpVo.getNo();
        vo.setEmpNo(loginNo);
        System.out.println("vo~~~~~~~~~~~~~~~~~~~~~~~~~~:" + vo);
        System.out.println("vo~~~~~~~~~~~~~~~~~~~~~~~~~~:" + boardNo);
        System.out.println("vo~~~~~~~~~~~~~~~~~~~~~~~~~~:" + loginNo);
         int result = service.commentWrite(vo , boardNo);
        System.out.println("return~~~~~~~~~~~~~~~~~~~~~~~~~~:" + vo);
         return result;
    }
    
}