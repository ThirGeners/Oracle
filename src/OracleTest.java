import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class OracleTest {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",  "sys as sysdba",  "Admin123");
		PreparedStatement ps = conn.prepareStatement("select * from t_test");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("id"));
		}
		
		CallableStatement cs = conn.prepareCall("{call p_test}");
		cs.execute();
		
		CallableStatement cs1 = conn.prepareCall("{call p_test2}");
		cs1.execute();
		
		CallableStatement cs2 = conn.prepareCall("{call p_test3(?, ?, ?)}"); // ���ô��в����Ĵ洢���̣�������Ҫ����ĸ�����Ӧ�ĸ�������������
		cs2.setString(1, "1003");  // �������������ֵ
		cs2.setString(2, "1003");
		cs2.registerOutParameter(3, Types.VARCHAR);  // ע���������
		cs2.execute(); // ִ�д洢����
		System.out.println("callable: " + cs2.getString(3)); // �����������Ŷ�Ӧ�ڴ洢�����е�����
	}


}
