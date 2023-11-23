package com.poly.service;

import java.util.List;

import com.poly.constatnt.Constant;
import com.poly.dao.impl.FavoriteDAO;
import com.poly.dao.impl.ShareDAO;
import com.poly.dao.impl.UserDAO;
import com.poly.dtos.FavoriteUserDto;
import com.poly.dtos.ShareUserDto;
import com.poly.dtos.UserDto;
import com.poly.entity.User;
import com.poly.utils.XEmail;
import com.poly.utils.XScope;

public class UserService {
	UserDAO userDAO = new UserDAO();
	ShareDAO shareDAO = new ShareDAO();
	FavoriteDAO favoriteDAO = new FavoriteDAO();

	public boolean isLikedVideo(String userid, String vid) {
		return userDAO.isLikedVideo(userid, vid);
	}

	public User findById(String id) {
		return userDAO.findById(id);
	}

	public void insert(User user) {
		userDAO.insert(user);
	}

	public String updateUser(User user, User loginUser) {
		User us = userDAO.findByEmail(user.getEmail());
		boolean isEmailExsist = us!=null && !us.getId().equals(user.getId());
		if(isEmailExsist) {
			return "Email is used by other account!";
		}else {
			if (loginUser.isAdmin()) {
				userDAO.updateInformation(user);
			} else {
				if(!user.getId().equals(loginUser.getId())) {
					return "You are not permistion";
				}else {					
					userDAO.updateInformation(us);
				}
			}
		}
		return "";
	}

	public List<FavoriteUserDto> findAllFavoriteUser(String videoid) {
		return userDAO.findAllFavoriteUser(videoid);
	}

	public List<ShareUserDto> findAllShareUser(String videoid) {
		return userDAO.findAllShareUser(videoid);
	}

	public List<User> findWithPagination(int page, int pageSize) {
		return userDAO.findAllWithPagination((page-1)*pageSize, pageSize);
	}

	public int getTotalUser() {
		return userDAO.count();
	}

	public boolean delete(String id) {
		boolean isExist = userDAO.findById(id) != null;
		if (isExist) {
			String[] ids = { id };
			shareDAO.deleteByUserId(id);
			favoriteDAO.deleteByUserId(id);
			userDAO.delete(ids);
			return true;
		}
		return false;
	}

	public String changePassword(UserDto dto, User loginUser) {
		loginUser = userDAO.findById(loginUser.getId());
		if (dto.getPassword().equals(loginUser.getPassword())) {
			loginUser.setPassword(dto.getNewPassword());
			return userDAO.update(loginUser) == null ? "Faile" : "";
		} else {
			return "Password incorrect!";
		}
	}

	public String forgetPassword(User us) {
		String message = "";
		User user = userDAO.findById(us.getId());
		if (user != null && !us.getEmail().trim().equals(user.getEmail().trim())) {
			message = "Email is incorrect";
		} else if(user == null) {
			message = "Username not found!";
		}
		if (message.length() == 0) {
			try {
				XEmail.sendPassword(user.getPassword(), user.getEmail());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				message = "Something went wrong. Please try again";
			}
		}
		return message;
	}

	public String singIn(UserDto userDto) {
		User user = userDAO.findById(userDto.getId());
		if (user == null) {
			return "User is not register!";
		} else {
			if (user.getPassword().equals(userDto.getPassword())) {
				XScope.setSession(Constant.USER, user);
				return "";
			} else {
				return "Password is not correct!";
			}
		}
	}

	public String doSignup(User user) {
		User us = userDAO.findById(user.getId());
		if(us != null) {
			return "Username is exsist!";
		}else {
			userDAO.insert(user);
			return "";
		}
		
	}

}
