package com.poly.utils;

import com.poly.entity.User;
import com.poly.entity.Video;
import org.apache.commons.text.StringSubstitutor;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class XEmail {

    public static final String FROM_EMAIL = "your email here";
    public static final String EMAIL_PASSWORD = "wryffjgvtuwqmjpg";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    public static Properties getProps() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
        props.put("mail.smtp.port", "587"); // TLS Port
        props.put("mail.smtp.auth", "true"); // enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return props;
    }

    public static boolean sendShareEmail(String[] recieves, User user, Video video, String baseURL)
            throws MessagingException, UnsupportedEncodingException {
        boolean isSuccess = false;
        String subject = "Bạn vừa nhận được thông báo chia sẻ Email";
        String body = getTemplate(user.getFullname() == null ? "Ai đó " : user.getFullname(), video.getId(), baseURL);

        Properties props = getProps();

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, EMAIL_PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);
        // set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");

        msg.setFrom(new InternetAddress(FROM_EMAIL, "Video Entertainment"));

        msg.setReplyTo(InternetAddress.parse(FROM_EMAIL, false));

        msg.setSubject(subject, StandardCharsets.UTF_8.name());

        msg.setContent(body, "text/html; charset=utf-8");

        msg.setSentDate(new Date());

        msg.setRecipients(Message.RecipientType.BCC, getInternetAddresses(recieves));
        Transport.send(msg);
        isSuccess = true;
        return isSuccess;
    }

    public static boolean sendPassword(String password, String receive)
            throws MessagingException, UnsupportedEncodingException {
        boolean isSuccess = false;
        String subject = "Cấp lại mật khẩu";
        String body = "Mật khẩu của bạn là: " + password;

        Properties props = getProps();

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, EMAIL_PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);
        // set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");

        msg.setFrom(new InternetAddress(FROM_EMAIL, "Video Entertainment"));

        msg.setReplyTo(InternetAddress.parse(EMAIL_PASSWORD, false));

        msg.setSubject(subject, "UTF-8");

        msg.setText(body, "UTF-8");

        msg.setSentDate(new Date());

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receive, false));
        Transport.send(msg);
        isSuccess = true;
        return isSuccess;
    }

    public static boolean isValidEmail(String[] emails) {
        for (int i = 0; i < emails.length; i++) {
            if (!VALID_EMAIL_ADDRESS_REGEX.matcher(emails[i]).matches()) {
                return false;
            }
        }
        return true;
    }

    public static String getTemplate(String fromUser, String videoId, String baseURL) {
        Map<String, String> params = new HashMap<>();
        params.put("from", fromUser);
        params.put("videoId", videoId);
        params.put("link", baseURL + videoId);
//
//        """
//
//                """
//
        String template = """
                <div style="width: 100%">
                    <h2>${from} đã chia sẻ video đến bạn</h2>
                    <a href="${link}">
                        <img style="width: 100%; margin: auto"
                            src="https://img.youtube.com/vi/${videoId}/hqdefault.jpg" alt="image" />
                    </a>
                </div>
                """;
        StringSubstitutor sub = new StringSubstitutor(params);
        return sub.replace(template);
    }

    public static InternetAddress[] getInternetAddresses(String[] emails) throws AddressException {
        InternetAddress[] result = new InternetAddress[emails.length];
        for (int i = 0; i < emails.length; i++) {
            result[i] = new InternetAddress(emails[i]);
        }
        return result;
    }

}
