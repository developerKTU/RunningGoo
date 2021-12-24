package com.javassem.service;

import java.util.HashMap;
import java.util.List;

import com.javassem.domain.MemberVO;
import com.javassem.domain.RunningGooRoomNumberVO;
import com.javassem.domain.RunningGooVO;

public interface RunningGooService {
	// 런닝구 방 생성
	void insertRNRoomInfo(RunningGooVO vo);
	
	// 런닝구 리스트 가져오기
	List<RunningGooVO> getRNRoomList(RunningGooVO vo);
	
	// 런닝구 방 갯수 얻어오기
	int getRNRoomCount(RunningGooVO vo);
	
	// 멤버 포인트 조회
	int getMemberJoinRunningGoo(MemberVO vo);
	
	// doJoin눌렀을때 불러오는 기본 런닝구 방 정보
	RunningGooVO bringBasicRngRoomInfo(RunningGooVO vo);
	
	// 
	void CreateRunningGooMemberInsert(RunningGooVO vo);
	
}
