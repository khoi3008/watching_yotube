package com.poly.dao.impl;

import java.util.List;

import com.poly.common.QueryParameter;
import com.poly.dao.IUserDAO;
import com.poly.dtos.ShareUserDto;
import com.poly.dtos.FavoriteUserDto;
import com.poly.entity.User;
import com.poly.utils.JpaUtils;

public class UserDAO implements IUserDAO {

	@Override
	public User insert(User entity) {
		return JpaUtils.persist(User.class, entity);
	}
	

	@Override
	public User update(User entity) {
		return JpaUtils.merge(User.class, entity);
	}
	@Override
	public void updateInformation(User entity) {
		String jpql ="UPDATE User o SET o.fullname=:fullname, o.email=:email WHERE o.id=:uid";
		List<QueryParameter> queryParameters = List.of(
				new QueryParameter("fullname", entity.getFullname()),
				new QueryParameter("email", entity.getEmail()),
				new QueryParameter("uid", entity.getId())
				);
		JpaUtils.excuteUpdate(jpql, queryParameters);
	}

	@Override
	public User findById(String id) {
		return JpaUtils.findById(User.class, id);
	}

	@Override
	public void delete(String[] ids) {
		String query = "DELETE FROM User o WHERE o.id IN :ids";
		List<QueryParameter> queryParameters = List.of(new QueryParameter("ids", List.of(ids)));
		JpaUtils.excuteUpdate(query, queryParameters);
	}

	@Override
	public List<User> findAllWithPagination(int offset, int skip) {
		return JpaUtils.findWithPagination("User.findAll",null, offset, skip);
	}

	@Override
	public int count() {
		return JpaUtils.count("User");
	}

	@Override
	public boolean isLikedVideo(String userid, String vid) {
		List<QueryParameter> queryParameters = List.of(new QueryParameter("vid", vid),
				new QueryParameter("uid", userid));
		List<User> users = JpaUtils.excuteQuery("User.findUserLikedVideo", queryParameters, JpaUtils.NAME_QUERY);
		return !users.isEmpty();
	}

	@Override
	public List<FavoriteUserDto> findAllFavoriteUser(String videoId) {
		List<QueryParameter> queryParameters = List.of(new QueryParameter("vid", videoId));
		return JpaUtils.excuteQuery("User.findAllFavoriteUser", queryParameters, JpaUtils.NAME_QUERY);
	}

	@Override
	public List<ShareUserDto> findAllShareUser(String videoId) {
		List<QueryParameter> queryParameters = List.of(new QueryParameter("vid", videoId));
		return JpaUtils.excuteQuery("User.findAllShareUser", queryParameters, JpaUtils.NAME_QUERY);
	}

	@Override
	public User findByEmail(String email) {
		List<QueryParameter> queryParameters = List.of(new QueryParameter("email", email));
		List<User> users = JpaUtils.excuteQuery("User.findByEmail", queryParameters, JpaUtils.NAME_QUERY);
		return users.size() == 0 ? null : users.get(0);
	}

}
