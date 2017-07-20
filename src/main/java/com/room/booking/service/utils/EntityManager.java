package com.room.booking.service.utils;

import com.room.booking.exceptions.TransactionException;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by Alexey on 19.07.2017.
 */
public class EntityManager{

    private static EntityManager entityManager = new EntityManager();
    private Jdbc3PoolingDataSource dataSource = new Jdbc3PoolingDataSource();
    private ThreadLocal<ConnectionProxy> threadLocal = new ThreadLocal<>();

    private EntityManager(){
        dataSource.setServerName("127.0.0.1");
        dataSource.setDatabaseName("roombooking");
        dataSource.setUser("postgres");
        dataSource.setPassword("root");
        dataSource.setMaxConnections(10);
    }

    public ConnectionProxy getConnection(){
        //take connection from threadLocal
        ConnectionProxy connectionProxy = threadLocal.get();
        //if it is empty => create connection
        if(Objects.isNull(connectionProxy)){
            try {
                connectionProxy = new ConnectionProxy(dataSource.getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connectionProxy;
    }

    public void beginTransaction(){
        Connection connection;
        try {
            ///take connection from pool
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            ConnectionProxy connectionProxy = new ConnectionProxy(connection);
            ///mark connection as in transaction
            connectionProxy.setTransactional(true);
            threadLocal.set(connectionProxy);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit(){
        //take connection which was added in getConnection()
        ConnectionProxy connectionProxy = threadLocal.get();
        if(Objects.isNull(connectionProxy)){
            throw new TransactionException();
        }
        connectionProxy.commit();
        //remove used connection from threadLocal
        threadLocal.remove();
        //mark as not in transaction
        connectionProxy.setTransactional(false);
        connectionProxy.close();
    }

    public void rollback(){
        ConnectionProxy connectionProxy = threadLocal.get();
        if(Objects.isNull(connectionProxy)){
            throw new TransactionException();
        }
        connectionProxy.rollback();
        threadLocal.remove();
        connectionProxy.setTransactional(false);
        connectionProxy.close();
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

}
