package com.poly.dao;

import com.poly.entity.Share;

public interface IShareDAO extends DAO<Share, Long> {
	void deleteByVideoId(String videoId);
	void deleteByUserId(String userId);
	Share findByUserAndVideo(String userid, String videoId);
}
