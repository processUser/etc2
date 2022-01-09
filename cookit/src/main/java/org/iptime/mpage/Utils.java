package org.iptime.mpage;

import org.iptime.mpage.user.model.UserDTO;

import javax.servlet.http.HttpServletRequest;
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
    public static void splitBirthday(UserDTO dto, String str) {
        String[] strAry = str.split("-");
        dto.setBirthdaymm(strAry[0]);
        dto.setBirthdaydd(strAry[1]);
    }

}
