package com.poly.service;

import java.util.Date;
import java.util.List;

import com.poly.dao.impl.ShareDAO;
import com.poly.entity.Share;
import com.poly.utils.XEmail;

public class ShareService {
	ShareDAO shareDAO = new ShareDAO();
	public static final String url = "http://localhost:8080/asm/watch?id=";
	public static final String CHARACTER = ",";

	public boolean share(Share share) {
		String emails = share.getEmails();
		if (emails.isBlank()) {
			return false;
		}
		String[] receives = emails.split(CHARACTER);
		if (XEmail.isValidEmail(receives)) {
			Share exsistShare = shareDAO.findByUserAndVideo(share.getUser().getId(), share.getVideo().getId());
			if (exsistShare != null) {
				String existEmail = exsistShare.getEmails();
				for (int i = 0; i < receives.length; i++) {
					if(!existEmail.contains(receives[i])) {
						existEmail += "," + receives[i];
					}
				}
				share.setEmails(String.join(CHARACTER, existEmail));
				share.setShareDate(new Date());
				share.setId(exsistShare.getId());
				shareDAO.update(share);
			} else {
				share.setEmails(emails);
				share.setShareDate(new Date());
				shareDAO.insert(share);
			}
			try {
				XEmail.sendShareEmail(receives, share.getUser(), share.getVideo(), url);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}
}
