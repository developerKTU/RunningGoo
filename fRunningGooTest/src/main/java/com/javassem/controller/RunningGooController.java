package com.javassem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javassem.domain.MemberVO;
import com.javassem.domain.RunningGooVO;
import com.javassem.service.RunningGooService;

@Controller
public class RunningGooController {
	@Autowired
	RunningGooService runningGooService;
	
	// 런닝구 방 생성 및 정보 입력하기 --> 참여자
	@RequestMapping("rngInsert.do")
	public String CreateRngRoom(RunningGooVO vo, HttpSession session) {
		// Dogether 본 서버에서는 session.setAttribute를 해줄 필요가 없음.
		vo.setMemberID(session.getAttribute("username").toString());
		runningGooService.insertRNRoomInfo(vo);
		return "redirect:runninggooList.do";
	}
	
	// 런닝구 방 리스트 얻어오기  --> 참여자 / 호스트
	@RequestMapping("runninggooList.do")
	public String runningGooList(RunningGooVO vo, Model m, HttpSession session) {
		// Dogether 본 서버에서는 session.setAttribute를 해줄 필요가 없음.
		List<RunningGooVO> result = runningGooService.getRNRoomList(vo);
		int listCount = runningGooService.getRNRoomCount(vo);
		m.addAttribute("RunningGooList", result);
		m.addAttribute("rnRoomCNT", listCount);
		return "runningGooList";
	}
	
	// 런닝구 방 생성시 보유 포인트 조회하기  --> 참여자
	@RequestMapping("viewMemberPoints.do")
	@ResponseBody
	public Integer viewMembersPoints(MemberVO vo) {
		System.out.println("보유포인트 컨트롤러 테스트!");
		int rngMemberPoints = runningGooService.getMemberJoinRunningGoo(vo);
		System.out.println(rngMemberPoints+"입니닫요");
		return rngMemberPoints;
	}
	
	// DoJoin 버튼 클릭 시 호스트에게 보여질 참여자의 정보 INSERT --> 참여자
	@RequestMapping("bringBasicRngRoomInfo.do")
	@ResponseBody
	public String showJoinMember(RunningGooVO vo, HttpSession session) {
		// 일단 방참여 정보는 똑같으니 vo를 불러와 각각의 vo에 담기	
		// Dogether 본 서버에서는 session.setAttribute를 해줄 필요가 없음.
		RunningGooVO svo = runningGooService.bringBasicRngRoomInfo(vo);
		System.out.println(session.getAttribute("username").toString());
		svo.setMemberID(session.getAttribute("username").toString());
		svo.setMemberPendingStatusYN("Y");
		svo.setHostYN("N");
		runningGooService.CreateRunningGooMemberInsert(svo);
		return "호스트에게 참여신청을 보냈습니다!";
	}
	
	
	// 호스트에게 보여질 수락된 참여자들의 간단한 정보 --> 호스트
	@RequestMapping("viewJoinMemberTotalInfo.do")
	public void viewJoinMemberInfo(RunningGooVO rVO,Model m, @RequestParam String roomNumber) {
		// Dogether 본 서버에서는 session.setAttribute를 해줄 필요가 없음.
		// 수락이 완료된 참여자들의 간단한 정보
		int roomNum = Integer.parseInt(roomNumber);
		List<HashMap<String,Object>> result1 = runningGooService.viewJoinMemberInfo(roomNum);
		m.addAttribute("joinCompleteMemberList", result1);
		
		
		// 호스트에게 보여질 수락을 기다리는 참여자들의 간단한 정보
		//int roomNum1 = rVO.getRoomNumber();
		System.out.println(roomNum+"이에요~~");
		List<HashMap<String,Object>> result2 = runningGooService.viewNotYetJoinMemberList(roomNum);
		m.addAttribute("joinNotYetCompleteMemberList", result2);
	}
	
	// 호스트가 수락을 눌렀을때 수정되는 참여자의 정보 --> 참여자
	@RequestMapping("updateJoinMemberInfo.do")
	@ResponseBody
	public String updateJoinMember(int roomNum, HttpSession session) {
		String memberID = session.getAttribute("username").toString();
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("memberid", memberID);
		map.put("roomnumber",roomNum);
		runningGooService.upateRngMemberInfo(map);
		return "Confirm!";
	}
	
	
}
