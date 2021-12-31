package com.javassem.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javassem.domain.BoardVO;
import com.javassem.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
		// Service와 DAO까지 갈 필요없고, 요청을 받고 view화면만 출력해주는 요청들을 위해 만든다.
		// 예를들어 입력폼 같은 페이지들한테 쓰인다.
		
		  @RequestMapping("/{step}.do") public String viewPage(@PathVariable String
		  step) { return step; }
		 
	
		// 글 목록 검색
		@RequestMapping("/getBoardList.do")
		public void getBoardList(BoardVO vo, Model model) {
			List<BoardVO> result = boardService.getBoardList(vo);
			model.addAttribute("boardList", result);
			
			
		}
	
		// 글 등록
		@RequestMapping(value = "/saveBoard.do")
		public String insertBoard(BoardVO vo) throws IOException {
			boardService.insertBoard(vo);
			
			// jsp를 부르는 것이 아닌 요청을 다시 부름
			return "redirect:getBoardList.do";
		}

		// 글 수정
		@RequestMapping("/updateBoard.do")
		public String updateBoard(BoardVO vo) {
			boardService.updateBoard(vo);
			return "redirect:getBoard.do?b_id="+vo.getB_id();
		}

		// 글 삭제
		@RequestMapping("/deleteBoard.do")
		public String deleteBoard(BoardVO vo) {
			System.out.println(vo.getB_id());
			boardService.deleteBoard(vo);
			return "redirect:getBoardList.do";
		}

		// 글 상세 조회
		@RequestMapping("/getBoard.do")
		public void getBoard(BoardVO vo, Model m) {
			BoardVO result = boardService.getBoard(vo);
			m.addAttribute("board", result);
		}
		
		// 이미지들만 리스트로 출력
		@RequestMapping("/imageList.do")
		public void printImage(BoardVO vo, Model m) {
			List<BoardVO> bList = boardService.getBoardList(vo);
			m.addAttribute("boardList", bList);
		}
		

	}
