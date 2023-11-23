package com.poly.service;

import com.poly.dao.impl.FavoriteDAO;
import com.poly.entity.Favorite;
import com.poly.entity.User;
import com.poly.entity.Video;

public class FavoriteService {
	FavoriteDAO favoriteDAO = new FavoriteDAO();

	public void unlike(User user, Video video) {
		favoriteDAO.deleteByUserAndVideo(user, video);
	}

	public void like(User us, Video video) {
		Favorite favorite = new Favorite();
		favorite.setUser(us);
		favorite.setVideo(video);
		favoriteDAO.save(favorite);
	}

	public int getTotalFavoriteVideo(String userid) {
		return favoriteDAO.countFavoriteVideoByUser(userid);
	}

}
