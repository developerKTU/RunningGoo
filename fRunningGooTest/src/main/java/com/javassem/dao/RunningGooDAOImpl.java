package com.javassem.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javassem.domain.MemberVO;
import com.javassem.domain.RunningGooRoomNumberVO;
import com.javassem.domain.RunningGooVO;

@Repository
public class RunningGooDAOImpl implements RunningGooDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 런닝구 방 생성
	@Override
	public void createRngRoom(RunningGooVO vo) {
		System.out.println("런닝구 방 생성");
		// 룸넘버 생성
		mybatis.insert("runningGooDAOMapper.createRunningGooRoomNumber");
		System.out.println("checkpoint1");
		// 룸넘버 select 변수저장
		int roomNumber = mybatis.selectOne("runningGooDAOMapper.getRunningGooRoomNumber");
		vo.setRoomNumber(roomNumber);
		System.out.println("checkpoint2");
		//런닝구 방 생성
		mybatis.insert("runningGooDAOMapper.createRunningGooRoom", vo);
		System.out.println("checkpoint3");

	}
	
	// 런닝구 방 리스트 
	@Override
	public List<RunningGooVO> getRNRoomList(RunningGooVO vo) {
		System.out.println("런닝구 방 리스트 불러오는 함수 호출");
		return mybatis.selectList("runningGooDAOMapper.getRunningGooList", vo);
	}

	@Override
	public int getRunningGooRoomCount(RunningGooVO vo) {
		System.out.println("런닝구 방 갯수를 불러오는 함수 호출");
		return mybatis.selectOne("runningGooDAOMapper.getRunningGooRoomCount", vo);
	}
	
	@Override
	public int getRngMemberPoints(MemberVO vo) {
		System.out.println("런닝구 방 생성시 멤버의 보유 포인트를 가져오는 함수 호출");
		return mybatis.selectOne("runningGooDAOMapper.getMemberJoinRngList",vo);
	}

	@Override
	public RunningGooVO bringBasicRngRoomInfo(RunningGooVO vo) {
		// TODO Auto-generated method stub
		System.out.println("DoJoin 클릭시 INSERT되는 함수");
		return mybatis.selectOne("runningGooDAOMapper.bringBasicRngRoomInfo", vo);
	}

	@Override
	public void CreateRunningGooMemberInsert(RunningGooVO vo) {
		// TODO Auto-generated method stub
		mybatis.insert("runningGooDAOMapper.CreateRunningGooMemberInsert", vo);
	}
	
	
	
}
