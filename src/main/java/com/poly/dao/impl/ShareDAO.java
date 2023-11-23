package com.poly.dao.impl;

import com.poly.common.QueryParameter;
import com.poly.dao.IShareDAO;
import com.poly.entity.Share;
import com.poly.utils.JpaUtils;

import java.util.List;

public class ShareDAO implements IShareDAO {

    @Override
    public Share insert(Share entity) {
        return JpaUtils.persist(Share.class, entity);
    }

    @Override
    public Share update(Share entity) {
        return JpaUtils.merge(Share.class, entity);
    }

    @Override
    public Share findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long[] ids) {

    }

    @Override
    public List<Share> findAllWithPagination(int page, int pageSize) {
        return null;
    }

    @Override
    public void deleteByVideoId(String videoId) {
        String query = "DELETE FROM Share s WHERE s.video.id = :vid";
        List<QueryParameter> queryParameters = List.of(new QueryParameter("vid", videoId));
        JpaUtils.excuteUpdate(query, queryParameters);
    }

    @Override
    public void deleteByUserId(String userId) {
        String query = "DELETE FROM Share s WHERE s.user.id = :vid";
        List<QueryParameter> queryParameters = List.of(new QueryParameter("vid", userId));
        JpaUtils.excuteUpdate(query, queryParameters);
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public Share findByUserAndVideo(String userid, String videoId) {
        List<QueryParameter> queryParameters
                = List.of(new QueryParameter("vid", videoId),
                new QueryParameter("uid", userid));

        List<Share> shares = JpaUtils.excuteQuery("Share.findByUserAndVideo",
                queryParameters, JpaUtils.NAME_QUERY);

        return !shares.isEmpty() ? shares.get(0) : null;
    }
}
