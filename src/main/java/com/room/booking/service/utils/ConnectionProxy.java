package com.room.booking.service.utils;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Alexey on 19.07.2017.
 */
//proxy for connection object
public class ConnectionProxy implements Closeable {

    private Connection connection;
    //if connection is transaction =>true else => false
    private boolean isTransactional;

    public ConnectionProxy(Connection connection) {
        this.connection = connection;
    }

    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    public PreparedStatement createPreparedStatement(String statement) throws SQLException {
        return connection.prepareStatement(statement);
    }

    public void commit(){
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        //if connection is not in transaction => close it
        if(!isTransactional) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setTransactional(boolean transactional) {
        isTransactional = transactional;
    }
}
