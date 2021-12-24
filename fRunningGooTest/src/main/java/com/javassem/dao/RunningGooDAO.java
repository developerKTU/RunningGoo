package com.javassem.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javassem.domain.MemberVO;
import com.javassem.domain.RunningGooRoomNumberVO;
import com.javassem.domain.RunningGooVO;

@Repository
public interface RunningGooDAO {
	// 런닝구 방 정보 입력
	void createRngRoom(RunningGooVO vo);
	
	// 런닝구 방 리스트 불러오기
	public List<RunningGooVO> getRNRoomList(RunningGooVO vo);
	
	// 런닝구 방 갯수 얻어오기
	int getRunningGooRoomCount(RunningGooVO vo);
	
	// 런닝구 방 생성 시 멤버 보유 포인트 얻어오기
	public int getRngMemberPoints(MemberVO vo);
	
	// doJoin 눌렀을때 insert되는 멤버 정보
	RunningGooVO bringBasicRngRoomInfo(RunningGooVO vo);
	
	//
	void CreateRunningGooMemberInsert(RunningGooVO vo);

}
