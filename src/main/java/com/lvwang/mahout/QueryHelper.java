package com.lvwang.mahout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class QueryHelper {

	private Connection con;
	
	public QueryHelper() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zhihu", "root", "123456");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public QueryHelper save(SingleNews news) {
		String sql = "insert into news values(?,?,?,?,?,?)";
		QueryRunner query = new QueryRunner();
		try {
			query.update(con, sql, new Object[]{
												news.getId(), 
												news.getNews_date(), 
												news.getBody(),
												news.getImage_source(),
												news.getTitle(),
												news.getImage()
												});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SingleNews get(int id) {
		String sql = "select * from news where id=?";
		QueryRunner query = new QueryRunner();
		try {
			return query.query(con, sql, new BeanHandler<SingleNews>(SingleNews.class), new Object[]{id});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new SingleNews();
	}
	
	public List<SingleNews> getAll() {
		String sql = "select * from news";
		QueryRunner query = new QueryRunner();
		try {
			return query.query(con, sql, new BeanListHandler<SingleNews>(SingleNews.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<SingleNews>();
	}
	
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
