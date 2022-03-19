package daoIMP;
import bean.Student;
import dao.StudentDAO;
import java.sql.*;


import connection.DataBaseConnection;

public class StudentDAOIMP implements StudentDAO{
	//添加操作
	    public void insert(Student s){
	    	String sql = " INSERT INTO student (id, name) values (?,?)";
	        PreparedStatement pstmt = null;
	        DataBaseConnection conn = null;
	        //针对数据库的具体操作
	        try{
	            conn = new DataBaseConnection();
	        
	            pstmt = conn.getConnection().prepareStatement(sql);
	            pstmt.setLong(1,s.getID());
	            pstmt.setString(2, (String) s.getName());

	            pstmt.executeUpdate();
	            pstmt.close();
	            conn.close();
	            }
	        catch(Exception e){  }
	    }

	    public void update(Student s){

		}

		public void delete(String iD){
			String sql = " delete from Student where id='iD'";
			PreparedStatement pstmt;
			DataBaseConnection conn = null;
			//针对数据库的具体操作
			try{
				conn = new DataBaseConnection();
				pstmt = conn.getConnection().prepareStatement(sql);

				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}
			catch(Exception e){  }

	    }
	    
	    public void findAll(){


		}

		public void findByID(long iD){
			DataBaseConnection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","root");  //  获取数据库连接

			QueryRunner qr = new QueryRunner();

			String sql = "select * from student where id=iD";  // 给出sql模板

			Object[] params = {3};// 给出参数，上面模板虽然是一个参数，但这里用的是数组方式

			// 接下来执行query()方法，需要给出结果集处理器，即ResultSetHandler的实现类对象
			// 我们用的是org.apache.commons.dbutils.handlers.BeanHandler;它实现了ResultSetHandler；
			// 它需要一个类型，然后它会把rs中的数据封装到指定类型的一个javabean对象中，然后返回。
			Student stu= qr.query(sql, new BeanHandler<Student>(Student.class), params);
			System.out.println(stu);
			conn.close();//别忘了关连接


			String sql = " select * from Student where id=iD";
			PreparedStatement pstmt = null;
			DataBaseConnection conn = null;
			//针对数据库的具体操作
			try{
				conn = new DataBaseConnection();
				pstmt = conn.getConnection().prepareStatement(sql);

				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}
			catch(Exception e){  }
		}
}
