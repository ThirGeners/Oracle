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
		
		CallableStatement cs2 = conn.prepareCall("{call p_test3(?, ?, ?)}"); // 调用带有参数的存储过程，？，需要清楚哪个？对应哪个输入或输出参数
		cs2.setString(1, "1003");  // 给输入参数设置值
		cs2.setString(2, "1003");
		cs2.registerOutParameter(3, Types.VARCHAR);  // 注册输出参数
		cs2.execute(); // 执行存储过程
		System.out.println("callable: " + cs2.getString(3)); // 输出参数的序号对应于存储过程中的序列
	}


}
