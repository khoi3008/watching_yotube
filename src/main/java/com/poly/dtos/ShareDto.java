package com.poly.dtos;

import com.poly.entity.Share;
import com.poly.entity.User;
import com.poly.entity.Video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareDto {
	private String videoId;
	private String emails;
	
	public static Share toShare(ShareDto dto, User us) {
		Share share = new Share();
		share.setEmails(dto.getEmails());
		share.setUser(us);
		Video video = new Video();
		video.setId(dto.getVideoId());
		share.setVideo(video);
		return share;
	}
}
