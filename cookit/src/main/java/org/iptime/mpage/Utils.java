package org.iptime.mpage;

import org.iptime.mpage.model.user.UserDTO;
import org.iptime.mpage.model.user.UserVo;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {
    // json 받기
    public static String getJson(HttpServletRequest request)  throws IOException {
        String reqStr = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        reqStr = stringBuilder.toString();
        return reqStr;
    }

    //생일값 월일 분리
    public static void splitBirthday(UserDTO dto, String birthday) {
        String[] strAry = birthday.split("-");
        dto.setBirthdaymm(strAry[0]);
        dto.setBirthdaydd(strAry[1]);
    }

    //비밀번호 암호화
    public static void hashPw(UserDTO dto, String pw){
        dto.setPw(BCrypt.hashpw(pw, BCrypt.gensalt()));
    }

    //성별 정수
    public static void strTogender(UserDTO dto, String gender) {
        switch (gender){
            case "M":
                dto.setGender(1);
                break;
            case "F":
                dto.setGender(2);
                break;
            default:
                dto.setGender(0);
                break;
        }
    }

    //세션에 값 담기
    public static int setSession(HttpServletRequest req, UserVo vo){
        vo.setPw("");
        HttpSession session = req.getSession();
        session.setAttribute("loginUser", vo);
        return 1;
    }

    public static void disForward(HttpServletRequest req, HttpServletResponse res, String jsp) throws ServletException, IOException{
        req.getRequestDispatcher("/WEB-INF/view/"+jsp+".html").forward(req, res);
    }
}
