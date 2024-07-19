package com.kh.works.board.controller;

//import com.kh.works.security.EmpSessionVo;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.kh.works.board.service.BoardService;
import com.kh.works.board.vo.BoardVo;
import com.kh.works.board.vo.CommentVo;
import com.kh.works.board.vo.WishBoardVo;
import com.kh.works.employee.vo.EmployeeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

    private final BoardService service;
    private final AmazonS3 s3; //결합도를 낮추기위해 부모타입을 쓰겠다 A s3 client는 자식

    //내가 쓴 이름을 가져옴 baby-worksGeuna
    @Value("${aws.s3.bucket-name}")
    private String bucketName;


    //게시물 작성하기 화면
    @GetMapping("write")
    public String wirte() {
        return "board/write";
    }

    //게시물 작성하기 백엔드
    @PostMapping("write")
    @ResponseBody
    public String write(BoardVo vo, HttpSession session ){

        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String empNo = loginEmpVo.getNo();
        vo.setEmpNo(empNo);

        int result = service.write(vo);

        return "redirect:/board/list";
//        String no = service.getBoardByNo();
//        vo.setBoardNo(no);

//        String boardNo = vo.getBoardNo();
//        System.out.println(boardNo + "~~~~~~~~~~~~");
//
//        // 각 이미지를 파일로 저장하는 처리
//        if (imgs != null) {
//            for (MultipartFile img : imgs) {
//                if (!img.isEmpty()) {
//                    //실제 이미지 이름을 imgName 이라는 변수에 저장
//                    String imgName = img.getOriginalFilename();
//                    //이미지를 저장할 경로 아마도 이게 5050포트
//                    String savePath = "/src/main/resources/static/img/icon/"+ imgName; // 실제 서버 경로로 변경해야 함
//                    //파일이 저장될 최종 경로 임시적으로 저장될 경로
//                    String filePath = savePath + imgName;
//                    //파일 객체 생성해서 저장
//                    File fileAdd = new File(filePath);
//                    //그리고 넘기기
//                    img.transferTo(fileAdd);
//
//                    BoardImgVo imgVo = new BoardImgVo();
//                    imgVo.setBoardNo(boardNo);
//                    imgVo.setImgName(imgName);
//                    int imgResult = service.writeImg(imgVo);
//                }
//            }

//        }
    }

    @PostMapping("/upload")
    @ResponseBody
    public String uploadImg(@RequestParam("imgList") List<MultipartFile> imgList) throws Exception {

//        File targetImg = new File("D:\\dev\\final\\works\\src\\main\\resources\\static\\img\\board\\" + img.getOriginalFilename());
//        img.transferTo(targetImg);
//        return "http://192.168.40.109:5500/"+ img.getOriginalFilename();
        MultipartFile img = imgList.get(0);
        String urlText = "";
        if (!img.isEmpty()) {
            /*s3에 업로드하기
             * s3.putObject(버킷,파일이름,인풋스트림,파일상세정보); 이런식으로 쓰는데 인풋스트림을 넘긴다고?? 인풋스트림만 넘기면 아마존이 알아서 해준다.
             * 파일 상세정보는 객체를 만들어서 넣어줘야 해서 */
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(img.getContentType());
            metadata.setContentLength(img.getSize());
            s3.putObject(bucketName, img.getOriginalFilename(), img.getInputStream(), metadata); //인풋스트림을 넘긴다고?? 인풋스트림만 넘기면 아마존이 알아서 해준다.
            //내가 금방올린 파일 url 가져오기
            URL url = s3.getUrl(bucketName, img.getOriginalFilename());
            System.out.println(url + "urllllllllllllllllllllllllllllllllllllllllllllllll");
            urlText= "" +url;
        }
        System.out.println(urlText + "urllzzzzzzzzzzzzzzzzzzzzzzzzzzzzzllllllllllllllllllllllllllllllllllllllllllllll");
        return urlText;
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

        // 불린으로 판단
        boolean wishList = service.checkWishList(vo);

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
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");
        String loginNo = loginEmpVo.getNo();
        vo.setEmpNo(loginNo);
        int result = service.commentWrite(vo , boardNo);
        return result;
    }

    @GetMapping("api/comment")
    @ResponseBody
    public List<CommentVo> commentApi(@RequestParam("boardNo")String boardNo){
        List<CommentVo> voList = service.commentApi(boardNo);
        return voList;
    }

    @PostMapping("comment/del")
    @ResponseBody
    public int commentDel(@RequestParam("comtNo")String comtNo){
        int result = service.commentDel(comtNo);
        return result;
    }

}