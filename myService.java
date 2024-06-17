import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//2. MyService 클래스 (JDBC 사용)
public class MyService {

    public List<CodeNamePair> getCodeNameList() {
        List<CodeNamePair> codeNamePairs = new ArrayList<>();

        try {
            // 데이터베이스 연결 설정
            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String user = "myuser";
            String password = "mypassword";

            // 데이터베이스 연결
            Connection conn = DriverManager.getConnection(url, user, password);

            // SQL 쿼리 실행
            String query = "SELECT code, name FROM mytable";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // 결과를 리스트에 추가
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                codeNamePairs.add(new CodeNamePair(code, name));
            }

            // 리소스 정리
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return codeNamePairs;
    }
}
