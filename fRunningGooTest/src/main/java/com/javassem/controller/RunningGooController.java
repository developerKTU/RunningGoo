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
		// Dogether 본 서버에서는 session.setAttribute를 해줄 필요가 없음.
		session.setAttribute("username", "hong");
		vo.setMemberID(session.getAttribute("username").toString());
		runningGooService.insertRNRoomInfo(vo);
		return "redirect:runninggooList.do";
	}
	
	// 런닝구 방 리스트 얻어오기
	@RequestMapping("runninggooList.do")
	public String runningGooList(RunningGooVO vo, Model m, HttpSession session) {
		session.setAttribute("username", "hong");
		System.out.println(session.getAttribute("username").toString());
		List<RunningGooVO> result = runningGooService.getRNRoomList(vo);
		int listCount = runningGooService.getRNRoomCount(vo);
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
		return rngMemberPoints;
	}
	
	// DoJoin 버튼 클릭 시 호스트에게 보여질 참여자의 정보 select
	@RequestMapping("bringBasicRngRoomInfo.do")
	@ResponseBody
	public String showJoinMember(RunningGooVO vo, HttpSession session) {
		// 일단 방참여 정보는 똑같으니 vo를 불러와 각각의 vo에 담기
		System.out.println(vo.getRoomNumber());
		session.setAttribute("username", "99con");
		
		RunningGooVO svo = runningGooService.bringBasicRngRoomInfo(vo);
		svo.setMemberID(session.getAttribute("username").toString());
		svo.setMemberPendingStatusYN("Y");
		svo.setHostYN("N");
		System.out.println(svo.getMemberID());
		runningGooService.CreateRunningGooMemberInsert(svo);
		return "호스트에게 참여신청을 보냈습니다!";
	}
	
}
