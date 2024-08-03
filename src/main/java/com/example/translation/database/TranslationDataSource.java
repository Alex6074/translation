package com.example.translation.database;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class TranslationDataSource implements DataSource {
    private static volatile TranslationDataSource instance;
    private final String driver;
    private final String url;
    private final String name;
    private final String password;

    private TranslationDataSource(String driver, String url, String password, String name) {
        this.driver = driver;
        this.url = url;
        this.name = name;
        this.password = password;
    }

    public static TranslationDataSource getInstance() {
        if (instance==null) {
            synchronized (TranslationDataSource.class) {
                if (instance==null) {
                    Properties properties = new Properties();
                    try {
                        properties.load(TranslationDataSource.class.getClassLoader().getResourceAsStream("application.properties"));
                        String driver = properties.getProperty("spring.datasource.driverClassName");
                        String url = properties.getProperty("spring.datasource.url");
                        String name = properties.getProperty("spring.datasource.username");
                        String password = properties.getProperty("spring.datasource.password");
                        instance = new TranslationDataSource(driver, url, password, name);
                        Class.forName(driver);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, name, password);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException("'getLogWriter' is not implemented");
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new UnsupportedOperationException("'setLogWriter' is not implemented");
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException("'setLoginTimeout' is not implemented");
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException("'getLoginTimeout' is not implemented");
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException("'getParentLogger' is not implemented");
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException("'unwrap' is not implemented");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException("'isWrapperFor' is not implemented");
    }
}
