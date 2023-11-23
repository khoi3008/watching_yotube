package com.poly.dao;

import com.poly.entity.Favorite;
import com.poly.entity.User;
import com.poly.entity.Video;

public interface IFavoriteDAO  {
	Favorite save(Favorite entity);
	void deleteByVideoId(String videoId);
	void deleteByUserId(String userId);
	void deleteByUserAndVideo(User user, Video video);
	int countFavoriteVideoByUser(String userId);
}
