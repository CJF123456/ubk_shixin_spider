package com.unbank.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.unbank.mybatis.client.BhiProMapper;
import com.unbank.mybatis.factory.DynamicConnectionFactory;
import com.unbank.mybatis.vo.BhiProExample;
import com.unbank.mybatis.vo.BhiProWithBLOBs;
import com.unbank.tools.SimpleTools;

public class BhiProReader {

	public List<BhiProWithBLOBs> readBhiProReader(String startTime,
			String endTime, String area) {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		List<BhiProWithBLOBs> bhiPros = new ArrayList<BhiProWithBLOBs>();
		try {
			BhiProMapper bhiProMapper = sqlSession
					.getMapper(BhiProMapper.class);
			BhiProExample bhiProExample = new BhiProExample();
			if (area.isEmpty()) {
				bhiProExample
						.or()
						.andProTimeGreaterThanOrEqualTo(
								SimpleTools.stringToDate(startTime))
						.andProTimeLessThanOrEqualTo(
								SimpleTools.stringToDate(endTime));
			} else {
				bhiProExample
						.or()
						.andProTimeGreaterThanOrEqualTo(
								SimpleTools.stringToDate(startTime))
						.andProTimeLessThanOrEqualTo(
								SimpleTools.stringToDate(endTime))
						.andProAreaEqualTo(area);
			}
			bhiPros = bhiProMapper.selectByExampleWithBLOBs(bhiProExample);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		return bhiPros;

	}

	public List<BhiProWithBLOBs> readBhiProReader(String startTime,
			String endTime) {
		return readBhiProReader(startTime, endTime, "");
	}

}
