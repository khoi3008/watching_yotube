package com.poly.dao.impl;

import com.poly.common.QueryParameter;
import com.poly.dao.IFavoriteDAO;
import com.poly.entity.Favorite;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.utils.JpaUtils;

import java.util.List;

public class FavoriteDAO implements IFavoriteDAO {

    @Override
    public Favorite save(Favorite entity) {
        return JpaUtils.persist(Favorite.class, entity);
    }

    @Override
    public void deleteByVideoId(String videoId) {
        String query = "DELETE FROM Favorite f WHERE f.video.id = :vid";
        List<QueryParameter> queryParameters = List.of(new QueryParameter("vid", videoId));
        JpaUtils.excuteUpdate(query, queryParameters);
    }

    @Override
    public void deleteByUserId(String userId) {
        String query = "DELETE FROM Favorite f WHERE f.user.id = :uid";
        List<QueryParameter> queryParameters = List.of(new QueryParameter("uid", userId));
        JpaUtils.excuteUpdate(query, queryParameters);
    }


    @Override
    public void deleteByUserAndVideo(User user, Video video) {
        String query = "DELETE FROM Favorite f WHERE f.user.id = :uid AND f.video.id = :vid";
        List<QueryParameter> queryParameters
                = List.of(new QueryParameter("uid", user.getId()),
                new QueryParameter("vid", video.getId()));
        JpaUtils.excuteUpdate(query, queryParameters);
    }

    @Override
    public int countFavoriteVideoByUser(String userId) {
        List<QueryParameter> queryParameters = List.of(new QueryParameter("id", userId));
        List<Favorite> list = JpaUtils.excuteQuery("Favorite.countFavoriteVideoByUser",
                queryParameters, JpaUtils.NAME_QUERY);
        return list.size();
    }

}
