package com.ex.ers.utilities;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Abstract Class used to set up a connection to a DB
 */
public abstract class ConnectionUtil {
    protected String dbUsername;
    protected String dbPassword;
    protected String dbEndPoint;
    public abstract Connection newConnection() throws SQLException;
}
