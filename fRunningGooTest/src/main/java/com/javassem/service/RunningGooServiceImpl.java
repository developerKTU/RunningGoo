package com.javassem.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javassem.dao.RunningGooDAO;
import com.javassem.domain.MemberVO;
import com.javassem.domain.RunningGooVO;

@Service
public class RunningGooServiceImpl implements RunningGooService {
	
	@Autowired
	RunningGooDAO runningGooDAO;
	
	// 런닝구 방 생성
	@Override
	public void insertRNRoomInfo(RunningGooVO vo) {
		// TODO Auto-generated method stub
		runningGooDAO.createRngRoom(vo);
	}

	@Override
	public List<RunningGooVO> getRNRoomList(RunningGooVO vo) {
		// TODO Auto-generated method stub
		return runningGooDAO.getRNRoomList(vo);
	}

	@Override
	public int getRNRoomCount(RunningGooVO vo) {
		// 런닝구 방 갯수 얻어오기
		return runningGooDAO.getRunningGooRoomCount(vo);
	}
	
	@Override
	public int getMemberJoinRunningGoo(MemberVO vo){
	    return runningGooDAO.getRngMemberPoints(vo);
	}

	@Override
	public RunningGooVO bringBasicRngRoomInfo(RunningGooVO vo) {
		
		return runningGooDAO.bringBasicRngRoomInfo(vo);
	}

	@Override
	public void CreateRunningGooMemberInsert(RunningGooVO vo) {
		// 참여자가 DoJoin을 눌렀을때 들어가는 멤버정보
		runningGooDAO.CreateRunningGooMemberInsert(vo);
		
	}

	@Override
	public List<HashMap<String,Object>> viewJoinMemberInfo(int roomNum) {
		// 호스트에게 보여질 수락이 완료된 멤버들의 정보
		return runningGooDAO.viewJoinMemberList(roomNum);
	}

	@Override
	public List<HashMap<String,Object>> viewNotYetJoinMemberList(int roomNum) {
		// 호스트에게 보여질 수락을 기다리는 참여자들의 간단한 정보
		return runningGooDAO.viewNotYetJoinMemberList(roomNum);
	}

	@Override
	public void upateRngMemberInfo(HashMap<String,Object> map) {
		// 호스트가 수락을 눌렀을때 수정될 런닝구 멤버의 정보
		runningGooDAO.upateRngMemberInfo(map);
	}
	
}
