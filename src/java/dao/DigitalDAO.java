/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.DigitalNews;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class DigitalDAO extends DBContext {

    public List<DigitalNews> getTop(int number) throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            List<DigitalNews> list = new ArrayList<>();
            if (number != 1) {
                number++;
            }
            String query = "select top(?) * from digital order by TimePost desc";
            conn = getConnection();
            //put the sql statement into the sql server
            ps = conn.prepareStatement(query);
            ps.setInt(1, number);
            //run the sql command and get the results
            rs = ps.executeQuery();
            if (number != 1) {
                rs.next();
            }
            while (rs.next()) {
                DigitalNews d = new DigitalNews(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                //add value to list
                list.add(d);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(conn);

        }
    }

//    public DigitalNews getTop1() throws Exception {
//   
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String sql = "select top 1 * from digital\n"
//                + "order by timePost desc";
//        try {
//
//             conn = getConnection();
//             ps = conn.prepareStatement(sql);
//             rs = ps.executeQuery();
//            while (rs.next()) {
//                DigitalNews d = new DigitalNews(rs.getInt("ID"),
//                        rs.getString("title"),
//                        rs.getString("description"),
//                        rs.getString("image"),
//                        rs.getString("author"),
//                        rs.getDate("timePost"),
//                        rs.getString("shortDes"));
//                return d;
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//             closeConnection(conn);
//            closePreparedStatement(ps);
//            closeResultSet(rs);
//        }
//        return null;
//    }
    public DigitalNews getOne(int id) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from digital where id = ?";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                DigitalNews d = new DigitalNews(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                return d;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(conn);

        }
        return null;
    }

//    public List<DigitalNews> getTop5() throws Exception {
//         DBContext db = new DBContext();
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        
//        
//        String sql = "select top 5 * from digital\n"
//                + "where timePost not in(\n"
//                + "select max(timePost) from digital\n"
//                + ")\n"
//                + "order by timePost desc";
//        try {
//            List<DigitalNews> list = new ArrayList<>();
//             conn = db.getConnection();
//           ps = conn.prepareStatement(sql);
//           rs = ps.executeQuery();
//            while (rs.next()) {
//                DigitalNews d = new DigitalNews(rs.getInt("id"),
//                        rs.getString("title"),
//                        rs.getString("description"),
//                        rs.getString("image"),
//                        rs.getString("author"),
//                        rs.getDate("timePost"),
//                        rs.getString("shortDes"));
//                list.add(d);
//            }
//            return list;
//        } catch (Exception e) {
//            throw e;
//        }
//        finally {
//             closeConnection(conn);
//            closePreparedStatement(ps);
//            closeResultSet(rs);
//        }
//    }
    public List<DigitalNews> search(String txt, int pageIndex, int pageSize) throws Exception {
        List<DigitalNews> list = new ArrayList<>();
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select *from("
                + "select ROW_NUMBER() over (order by ID ASC) as rn, *\n"
                + "from digital where title \n"
                + "like ?"
                + ")as x\n"
                + "where rn between ?*?-2"
                + "and ?*?";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            txt = txt.trim();
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, pageIndex);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageIndex);
            ps.setInt(5, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                DigitalNews d = new DigitalNews(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(conn);

        }
    }

    public int count(String txt) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select count(*) from digital \n"
                + "where title like ?";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(conn);

        }
    }
}
