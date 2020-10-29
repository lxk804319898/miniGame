package commonUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * @author masgak
 * JDBC工具类
 * @Description: 游戏排行榜功能还需要完善，登录界面与主界面对接
 */
public class Jdbc {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public Jdbc(){
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            String url = "jdbc:mysql://172.18.150.253:3306/fishing?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
            String name = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, name, password);
            statement = connection.createStatement();
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    //获取排行榜
    public List<Ranking> queryRanking(String game){
        List<Ranking> list = new ArrayList<>();
        if (game == null || game.trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "游戏名为空！");
            return null;
        }
        String sql = "select * from ranking where game =\"" + game + "\" order by scores desc limit 10";
        try {
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData md = resultSet.getMetaData();
            int columnCount = md.getColumnCount();
            Ranking ranking = null;
            while (resultSet.next()) {
                ranking = new Ranking();
                ranking.setId(resultSet.getLong(1));
                ranking.setGameName(resultSet.getString(2));
                ranking.setName(resultSet.getString(4));
                ranking.setScores(resultSet.getInt(3));
                list.add(ranking);
            }
            resultSet.close();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //获取全部游戏排行
    public List<Ranking> queryTotalRanking(){
        List<Ranking> list = new ArrayList<>();
        String sql = "select * from ranking a where (select count(id) from ranking b where a.game = b.game and b.scores > a.scores) < 10 order by scores desc";
        try {
            resultSet = statement.executeQuery(sql);
            Ranking ranking = null;
            while (resultSet.next()) {
                ranking = new Ranking();
                ranking.setId(resultSet.getLong(1));
                ranking.setGameName(resultSet.getString(2));
                ranking.setName(resultSet.getString(4));
                ranking.setScores(resultSet.getInt(3));
                list.add(ranking);
            }
            resultSet.close();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //更新分数
    public void insertScore(String game, Integer scores) {
        if (game == null || game.trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "游戏名为空！");
            return;
        }
        //现取消用ID当外键，先保留这段代码
        String sql1 = "select ID from user where name =\"" + User.name + "\"";
        try {
            resultSet = statement.executeQuery(sql1);
            if (resultSet.next()) {
                long ID = resultSet.getLong(1);
                SnowFlake snowFlake = new SnowFlake(2,3);
                String sql = "insert into ranking(id,game, scores, user_name) values(\"" + snowFlake.nextId() + "\",\"" + game + "\",\"" + scores + "\",\"" + User.name + "\")";
                int a = statement.executeUpdate(sql);
            } else {
                JOptionPane.showMessageDialog(null, "用户不存在！");
            }
            resultSet.close();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新用户密码
     * @param name
     * @param password
     * @param newPassword
     * @return
     */
    public boolean update(String name, String password, String newPassword) {
        boolean judge = false;
        boolean s = compare(name, password);
        if (s) {
            String sql = "update user set password=\"" + newPassword + "\"where name=\"" + name + "\"";
            try {
                int a = statement.executeUpdate(sql);
                if (a == 1) {
                    JOptionPane.showMessageDialog(null, "密码修改成功！");
                    judge = true;
                }
                connection.close();
                statement.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "用户不存在！");
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "修改失败");
        }
        return judge;
    }

    //对比用户名和密码是不匹配
    public boolean compare(String name, String password) {
        boolean m = false;
        String sql = "select password from user where name =\"" + name + "\"";
        try {
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String pa = resultSet.getString(1);
                if (pa.equals(password)) {
                    m = true;
                } else {
                    JOptionPane.showMessageDialog(null, "密码错误！");
                }
            } else {
                JOptionPane.showMessageDialog(null, "用户名不存在！");
            }
            resultSet.close();
            connection.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    //注册
    public void insert(String name, String password) {
        if (name == null || name.trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "注册账号为空，请重新输入！");
            return;
        }
        String checkUserSql = "select id from user where name =\"" + name + "\"";
        try{
            resultSet = statement.executeQuery(checkUserSql);
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "该水B已存在，请重新输入！");
                connection.close();
                statement.close();
                return;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        SnowFlake snowFlake = new SnowFlake(2,3);
        Long ID = snowFlake.nextId();
        String sql = "insert into user(ID, name,password) values(\"" + ID + "\",\"" + name + "\",\"" + password + "\")";
        try {
            int a = statement.executeUpdate(sql);
            connection.close();
            statement.close();
            if (a == 1) {
                JOptionPane.showMessageDialog(null, "注册成功！");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "对不起该用户名已经有了！");
            e.printStackTrace();
        }
    }
}
