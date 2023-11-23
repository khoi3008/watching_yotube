package com.poly.dao;

import java.util.List;

import com.poly.dtos.FavoriteUserDto;
import com.poly.dtos.ShareUserDto;
import com.poly.entity.User;

public interface IUserDAO extends DAO<User, String> {
	boolean isLikedVideo(String userid, String vid);
	List<FavoriteUserDto> findAllFavoriteUser(String videoId);
	List<ShareUserDto> findAllShareUser(String videoId);
	User findByEmail(String email);
	 void updateInformation(User entity);
}
