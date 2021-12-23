package com.javassem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javassem.domain.MemberVO;
import com.javassem.domain.RunningGooRoomNumberVO;
import com.javassem.domain.RunningGooVO;
import com.javassem.service.RunningGooService;

@Controller
public class RunningGooController {
	@Autowired
	RunningGooService runningGooService;
	
	// 런닝구 방 생성 및 정보 입력하기
	@RequestMapping("rngInsert.do")
	public String CreateRngRoom(RunningGooVO vo, HttpSession session) {
		session.setAttribute("username", "hong");
		vo.setMemberID(session.getAttribute("username").toString());
		runningGooService.insertRNRoomInfo(vo);
		System.out.println(vo.getRoomNumber());
		return "redirect:runninggooList.do";
	}
	
	// 런닝구 방 리스트 얻어오기
	@RequestMapping("runninggooList.do")
	public String runningGooList(RunningGooVO vo, Model m, HttpSession session) {
		session.setAttribute("username", "hong");
		System.out.println(session.getAttribute("username").toString());
		List<RunningGooVO> result = runningGooService.getRNRoomList(vo);
		int listCount = runningGooService.getRNRoomCount(vo);
		System.out.println(listCount);
		m.addAttribute("RunningGooList", result);
		m.addAttribute("rnRoomCNT", listCount);
		System.out.println("Model 객체를 통해 전달완료!");
		return "runningGooList";
	}
	
	// 런닝구 방 생성시 보유 포인트 조회하기
	@RequestMapping("viewMemberPoints.do")
	@ResponseBody
	public Integer viewMembersPoints(MemberVO vo) {
		System.out.println("보유포인트 컨트롤러 테스트!");
		int rngMemberPoints = runningGooService.getMemberJoinRunningGoo(vo);
		System.out.println(rngMemberPoints);
		return rngMemberPoints;
	}

}
