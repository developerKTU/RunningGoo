package com.javassem.dao;

import java.util.HashMap;
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
		// 참여자가 DoJoin을 눌렀을때 들어가는 멤버정보
		mybatis.insert("runningGooDAOMapper.CreateRunningGooMemberInsert", vo);
	}

	@Override
	public List<HashMap<String,Object>> viewJoinMemberList(int roomNum) {
		// 호스트에게 보여질 수락이 완료된 멤버들의 정보
		System.out.println("호스트에게 보여질 수락이 완료된 멤버들의 리스트 매퍼 호출");
		return mybatis.selectList("runningGooDAOMapper.viewJoinMemberList", roomNum);
	}

	@Override
	public List<HashMap<String,Object>> viewNotYetJoinMemberList(int roomNum) {
		// 호스트에게 보여질 수락을 기다리는 참여자들의 간단한 정보
		System.out.println("호스트에게 보여질 수락을 기다리는 멤버들의 리스트 매퍼 호출");
		int result = roomNum;
		System.out.println(result+"입니당!!");
		return mybatis.selectList("runningGooDAOMapper.viewNotYetJoinMemberList", roomNum);
	}

	@Override
	public void upateRngMemberInfo(HashMap<String,Object> map) {
		// 호스트가 수락을 눌렀을때 수정될 런닝구 멤버의 정보
		System.out.println("호스트 수락시 업데이트 되는 런닝구 멤버 정보");
		mybatis.update("runningGooDAOMapper.upadateRunningGooJoinMemberInfo",map);
	}
	
	
	
}
