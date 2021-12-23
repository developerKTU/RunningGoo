package com.javassem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javassem.dao.RunningGooDAO;
import com.javassem.domain.MemberVO;
import com.javassem.domain.RunningGooRoomNumberVO;
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
	
}
