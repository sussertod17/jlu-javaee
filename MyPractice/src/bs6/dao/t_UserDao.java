package bs6.dao;

import java.sql.*;

import bs6.entity.User;

public class t_UserDao extends BaseDao{
	
	public User findUserByName(String userName) throws SQLException{
		
		Connection conn = BaseDao.getconn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		
		
		String sql = "select * from user where USER_NAME = ?";
		
		Object[] params = {userName};
		
		
		rs = BaseDao.execute(conn, ps, sql, params);
		if(rs.next()){
			//System.out.print("0");
			user = new User();
			user.setUserId(rs.getInt("USER_ID"));
			user.setUserName(rs.getString("USER_NAME"));
			user.setUserPswd(rs.getString("USER_PSWD"));
			user.setUserStatus(rs.getInt("USER_STATUS"));
		}
		BaseDao.closeall(rs, ps, conn);
		return user;
	}
	
	
}
