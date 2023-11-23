package com.poly.service;

import java.util.List;

import com.poly.dao.impl.FavoriteDAO;
import com.poly.dao.impl.ShareDAO;
import com.poly.dao.impl.VideoDAO;
import com.poly.entity.Video;
import com.poly.utils.JpaUtils;

public class VideoService {
	
	private VideoDAO videoDAO = new VideoDAO();
	private ShareDAO shareDAO = new ShareDAO();
	private FavoriteDAO favoriteDAO = new FavoriteDAO();
	
	public Video insert(Video video) {
		return videoDAO.insert(video);
	}
	
	public Video update(Video video) {
		return videoDAO.update(video);
	}
	
	public void delete(String id) {
		shareDAO.deleteByVideoId(id);
		favoriteDAO.deleteByVideoId(id);
		JpaUtils.deleteById(Video.class, id);
	}
	
	public List<Video> findWithPagination(int page, int pageSize){
		return videoDAO.findAllWithPagination((page-1)*pageSize, pageSize);
	}
	
	public int getTotalVideo() {
		return videoDAO.count();
	}
	
	public Video findById(String videoid) {
		return videoDAO.findById(videoid);
	}
	
	public List<Video> randoms(int random){
		return videoDAO.randoms(random);
	}
	
	public List<Video> findAll(){
		return videoDAO.findAll();
	}
	
	public List<Video> findFavoriteVideoByUser(String userId, int page, int size){
		return videoDAO.findFavoriteVideoByUser(userId, (page-1)*size, size);
	}

	public List<Video> getJustWatchVideo(String[] ids) {
		return videoDAO.getJustWatchVideo(ids);
	}
	
	public int countFavorites(String videoId) {
		return videoDAO.countFavorites(videoId);
	}
}
