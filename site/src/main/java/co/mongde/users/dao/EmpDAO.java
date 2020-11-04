package co.mongde.users.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.mongde.board.dao.DAO;

public class EmpDAO extends DAO {

	// 변수선언
	PreparedStatement psmt;
	ResultSet rs;
	private final String EMP_STAT = "SELECT DEPARTMENT_ID, COUNT(*)"
			+ " FROM HR.EMPLOYEES WHERE DEPARTMENT_ID IS NOT NULL"
			+ " GROUP BY DEPARTMENT_ID";
	
	// 사용자 목록 조회
	public List<Map<String, Object>> getEmpStat() {
		List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
		try {
			psmt = conn.prepareStatement(EMP_STAT);

			rs = psmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("department_id",rs.getString("DEPARTMENT_ID"));
				map.put("cnt", rs.getString(2));
				userList.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return userList;
	}

	public void close() {
    	if(conn != null) {
    		try {
    			conn.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
	}
}
