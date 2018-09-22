package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Config;
import util.DBUtil;

public class ConfigDAO {

	public int getTotal() {
		int total = 0;
		String sql = "select count(*) from config";
		try (Connection conn = DBUtil.getConn(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	public boolean add(Config config) {
		String sql = "insert into config(myKey,myValue) values(?,?)";
		boolean flag = false;
		try (Connection conn = DBUtil.getConn(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, config.getMyKey());
			ps.setString(2, config.getMyValue());
			flag = ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				config.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean delete(Config config) {
		String sql = "delete from config where id = ?";
		boolean flag = false;
		try (Connection conn = DBUtil.getConn(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, config.getId());
			flag = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean update(Config config) {
		boolean flag = false;
		String sql = "update config set myKey=?,myValue=? where id=?";
		Connection conn = DBUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, config.getMyKey());
			ps.setString(2, config.getMyValue());
			ps.setInt(3, config.getId());
			flag = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public Config get(int id) {
		Config config = null;
		String sql = "select * from config where id=?";

		try (Connection conn = DBUtil.getConn(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				config = new Config();
				config.setId(id);
				config.setMyKey(rs.getString("myKey"));
				config.setMyValue(rs.getString("myValue"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return config;
	}


	public List<Config> list(int start, int count) {
		List<Config> configs = new ArrayList<Config>();
		String sql = "select * from config order by id desc limit ?,?";

		try (Connection conn = DBUtil.getConn(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Config config = new Config();
				config.setId(rs.getInt("id"));
				config.setMyKey(rs.getString("myKey"));
				config.setMyValue(rs.getString("myValue"));

				configs.add(config);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return configs;
	}

	public List<Config> list() {
		return list(0,Short.MAX_VALUE);
	}

	public Config getByKey(String key) {
		Config config = null;
		String sql = "select * from config where myKey=?";

		try (Connection conn = DBUtil.getConn(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				config = new Config();
				config.setId(rs.getInt("id"));
				config.setMyKey(key);
				config.setMyValue(rs.getString("myValue"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return config;
	}

}
