package bs6.dao;

import java.sql.*;

public class BaseDao {
    static {
        //加载驱动
    	
        try {
        	
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static Connection getconn(){
        //创建连接
        Connection conn = null;
       
        try {
        	
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/simplejpa","root","ygwhgsq");
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public static ResultSet execute(Connection conn, PreparedStatement ps, String sql, Object[] params) {
    
        
        //准备SQL
        ResultSet rs = null;

        try {
            //准备SQL
            ps = conn.prepareStatement(sql);

            for(int i=0; i<params.length; i++) {
                ps.setObject(i+1, params[i]);
            }
            rs = ps.executeQuery();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;
    }


    public static void closeall(ResultSet rs, PreparedStatement ps, Connection conn){
        try {
            if(rs!=null)
                rs.close();
            if(ps!=null)
                ps.close();
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
//   public static void main(String[] args) throws SQLException{
//    	
//	   	 Connection conn = BaseDao.getconn();
//    	 PreparedStatement ps = null;
//         ResultSet rs = null;
//         String sql = "select * from user where USER_ID = ?";
//        
//    	 Object[] params = {"admin"};
//    	 rs = BaseDao.execute(conn, ps, sql,params);
//    	 
//         if(rs.next())
//        	 System.out.print(rs.getString("USER_NAME"));
//
//   }
    
}
