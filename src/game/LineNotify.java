package game;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Pattern;

public class LineNotify {

    private static final String strEndpoint = "https://notify-api.line.me/api/notify" ; // สร้างตัวแปร ที่เป็น PRIVATE STRING เพื่อเก็บ URL API LINE_NOTIFY

    public boolean callEvent(String message) {
        String token = "0il1lvz9EJ5IbRjwMnOmUSqyHZQ33VxPyOFUFGnNZiF"; //token เพื่อกาารเข้าถึงในแชทของ Line Notify
        boolean result = false;
        try {
            message = replaceProcess(message);
            message = URLEncoder.encode(message, "UTF-8" ); //Encode แปลงค่า message ให้เป็น  UTF8  ตัวหนังสือจะไม่เป็นต่างดาว
            String strUrl = strEndpoint;
            URL url = new URL( strUrl ); //New object URL และส่งค่า URL API เข้าไปเพื่อแปลงค่าเป็น URL
            HttpURLConnection connection = (HttpURLConnection)url.openConnection(); //new HttpURLConnection เพื่อสร้าง Object connection และใช้ url.OpenConection  เพื่อเปิดการทำงาน
            connection.addRequestProperty("Authorization" , "Bearer " + token); //set requestProperty และส่ง token ไปยืนยันตัวตน
            connection.setRequestMethod( "POST" ); //กำนหด requestMethod เป็น POST
            connection.addRequestProperty( "Content-Type" , "application/x-www-form-urlencoded" );
            connection.setDoOutput( true );
            String parameterString = new String("message=" + message);
            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.print(parameterString);
            printWriter.close();
            connection.connect();
            int statusCode = connection.getResponseCode(); // check response status form LINE
            if ( statusCode == 200 ) {
                result = true;
            } else {
                throw new Exception( "Error:(StatusCode)" + statusCode + " , " + connection.getResponseMessage() );
            }
            connection.disconnect();
        } catch (Exception e) {
            System.out.println("Warning : "+e.getMessage());
        }
        return result;
    }

    /**
     * replaceProcess
     * @param txt
     * @return txt
     */
    private String replaceProcess(String txt){
        txt = replaceAllRegex(txt, "\\\\" , "￥" ); // \
        return txt;
    }

    /**
     *
     * @param value
     * @param regex
     * @param replacement
     * @return Pattern
     */
    private String replaceAllRegex(String value, String regex, String replacement) {
        if ( value == null || value.length() == 0 || regex == null || regex.length() == 0 || replacement == null )
            return "" ;
        return Pattern.compile(regex).matcher(value).replaceAll(replacement);
    }
}
