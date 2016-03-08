package bean;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.servlet.ServletException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TradingPlace {

    //private ArrayList orders;
    //private ArrayList trades;
    private ArrayList<Security> securities;
    private HashMap<Integer, User> users;
    private Integer userIdCount = 0;
    private HashMap<Security, ArrayList<Order>> orders;
    private HashMap<Security, ArrayList<Trade>> trades;
    private User eric;

    private BufferedWriter log;


    public TradingPlace() throws IOException, SQLException, NamingException {

        File file = new File("/Users/Nina/git/trading-webpage/logfile.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        log = new BufferedWriter(fw);
        log.write("starting\n");
        log.flush();

        orders = new HashMap<Security, ArrayList<Order>>();
        trades = new HashMap<Security, ArrayList<Trade>>();
        securities = new ArrayList();
        users = new HashMap<Integer, User>();

        // Create securities from the database
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("jdbc/ninaolo");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT id, name FROM securities";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String name = rs.getString("name");
            Security security = new Security();
            security.setName(name);
            security.setType("Stock");
            addSecurity(security, true);
        }

        // ??
        /*
        eric = new User();
        eric.setNickname("Eric");
        eric.setID(1337);
        users.put(1337, eric);*/

        //String sql = "SELECT id, name FROM users";
        //ResultSet rs = stmt.executeQuery(sql);
        //Enda User är Eric så länge...

        // Get orders & trades from the database and match with securities
        for (int i = 0; i < securities.size(); i++) {
            Security s = securities.get(i);
            sql = "SELECT name, type, price, amount, uid FROM orders WHERE name='" + s.getName() + "'";
            rs = stmt.executeQuery(sql);
            ArrayList<Order> temp = new ArrayList<Order>();
            Order tempOrder;

            while (rs.next()) {
                tempOrder = new Order();
                tempOrder.setSecurity(s);
                tempOrder.setQuantity(Integer.parseInt(rs.getString("amount")));
                tempOrder.setPrice(Integer.parseInt(rs.getString("price")));
                tempOrder.setType(rs.getString("type"));
                tempOrder.setUser(users.get(Integer.parseInt(rs.getString("uid"))));

                //logFile.println(s.getName() + ": user " + Integer.parseInt(rs.getString("uid")));

                temp.add(tempOrder);
            }
            orders.put(s, temp);

            ArrayList<Trade> tempTrades = new ArrayList<Trade>();
            Trade tempTrade;
            sql = "SELECT name, price, amount, dt, buyer, seller FROM trades WHERE name='" + s.getName() + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                tempTrade = new Trade();

                User buyer = users.get(Integer.parseInt(rs.getString("buyer")));
                User seller = users.get(Integer.parseInt(rs.getString("seller")));
                //Trade date?
                int price = Integer.parseInt(rs.getString("price"));
                int quantity = Integer.parseInt(rs.getString("amount"));

                tempTrade.setParameters(price, quantity, buyer, seller, s);
                tempTrades.add(tempTrade);
            }
            trades.put(s, tempTrades);

        }

        rs.close();
        stmt.close();
        conn.close();
    }


    public User getEric() {
        return eric;
    }


    public void addUser(User user) {
        userIdCount++; // Unique ID for everyone
        users.put(userIdCount, user);
        user.setID(userIdCount);
    }

    public void printUsers() throws IOException {
        log.write("\n\nALL USERS:\n");
        for (Integer uid : users.keySet()) {
            log.write("\n" + uid + ": NAME = " + users.get(uid).getNickname());
        }
        log.flush();
    }

    public void addSecurity(Security security, boolean noSql) {
        securities.add(security);
        orders.put(security, new ArrayList<Order>());
        trades.put(security, new ArrayList<Trade>());

    }

    public void addSecurity(Security security) throws SQLException, NamingException {
        securities.add(security);
        orders.put(security, new ArrayList<Order>());
        trades.put(security, new ArrayList<Trade>());
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("jdbc/ninaolo");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        String sql;
        String name = security.getName();
        //String text=order.getText();
        sql = "INSERT INTO securities (name) VALUES ('" + name + "')";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.close();
    }

    public void updateOrders(ArrayList<Order> array, Security security) throws SQLException, NamingException {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("jdbc/ninaolo");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();

        String sql = "DELETE FROM orders WHERE name='" + security.getName() + "'";
        stmt.executeUpdate(sql);

        Order order;
        for (int i = 0; i < array.size(); i++) {
            order = array.get(i);
            sql = "INSERT INTO orders (name, type, price, amount, uid) VALUES ('"
                    + order.getSecurity().getName() + "', '"
                    + order.getType() + "', "
                    + order.getPrice() + ", "
                    + order.getQuantity() + ", "
                    + order.getUser().getID() + ")";
            stmt.executeUpdate(sql);
        }
        stmt.close();
        conn.close();
    }

    public void updateTrades(ArrayList<Trade> array, Security security) throws SQLException, NamingException {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("jdbc/ninaolo");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();

        String sql = "DELETE FROM trades WHERE name='" + security.getName() + "'";
        stmt.executeUpdate(sql);
        Trade trade;
        for (int i = 0; i < array.size(); i++) {
            trade = array.get(i);
            sql = "INSERT INTO trades (name, price, amount, buyer, seller, dt) VALUES ('"
                    + trade.getSecurity().getName() + "', "
                    + trade.getPrice() + ", "
                    + trade.getQuantity() + ", "
                    + trade.getBuyer().getID() + ", "
                    + trade.getSeller().getID() + ", "
                    + trade.getDate() + ")";
            stmt.executeUpdate(sql);
        }
        stmt.close();
        conn.close();
    }


    public void addOrder(Order order) throws SQLException, NamingException, IOException {
        log.write("\nADDING ORDER: " + order.toString() + "\n");
        log.flush();
        Security security = order.getSecurity();
        makeTrades(security, order);
    }


    public void makeTrades(Security security, Order order) throws SQLException, NamingException {
        ArrayList<Order> security_orders = orders.get(security);
        ArrayList<Trade> security_trades = trades.get(security);
        String type = order.getType();
        int price = order.getPrice();
        int index = 0;
        int quantity;
        Order tempOrder;
        while (order.getQuantity() > 0 && index < security_orders.size()) {
            tempOrder = security_orders.get(index);
            if ((!tempOrder.getType().equals(type)) && tempOrder.getPrice() == price && tempOrder.getQuantity() > 0) {
                quantity = Math.min(tempOrder.getQuantity(), order.getQuantity());
                User buyer;
                User seller;
                if (type.equals("sell")) {
                    seller = order.getUser();
                    buyer = tempOrder.getUser();
                } else {
                    seller = tempOrder.getUser();
                    buyer = order.getUser();
                }
                Trade trade = new Trade();
                trade.setParameters(price, quantity, buyer, seller, security);
                security_trades.add(trade);

                order.setQuantity(order.getQuantity() - quantity);
                tempOrder.setQuantity(tempOrder.getQuantity() - quantity);
            }
            index++;
        }
        security_orders.add(order);
        trades.put(security, security_trades);

        for (int i = security_orders.size() - 1; i >= 0; i--) {
            tempOrder = security_orders.get(i);
            if (tempOrder.getQuantity() < 1) {
                security_orders.remove(i);
            }
        }
        orders.put(security, security_orders);
        updateOrders(security_orders,security);
        updateTrades(security_trades,security);

    }


    public Security getSecurity(String name) {
        for (int i = 0; i < securities.size(); i++) {
            Security s = securities.get(i);
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }


    public ArrayList getSecurities() {
        return securities;
    }


    public ArrayList<Order> getOrders(String str) {
        Security security = getSecurity(str);
        return orders.get(security);
    }

    public ArrayList<Trade> getTrades(String str) {
        Security security = getSecurity(str);
        return trades.get(security);

    }


}
