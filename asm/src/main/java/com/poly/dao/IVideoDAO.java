package com.poly.dao;

import java.util.List;

import com.poly.entity.Video;

public interface IVideoDAO extends DAO<Video, String> {
	List<Video> randoms(int random);
	List<Video> findAll();
	List<Video> findFavoriteVideoByUser(String userId, int offset, int size);
	List<Video> getJustWatchVideo(String[] ids);
	int countFavorites(String videoid);
}
