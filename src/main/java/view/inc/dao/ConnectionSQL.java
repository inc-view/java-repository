package view.inc.dao;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Properties;

public class ConnectionSQL {
    private static final String URL = "jdbc:sqlserver://ec2-18-232-37-243.compute-1.amazonaws.com:1433;connectRetryInterval=10;connectRetryCount=1;maxResultBuffer=-1;sendTemporalDataTypesAsStringForBulkCopy=true;delayLoadingLobs=true;useFmtOnly=false;useBulkCopyForBatchInsert=false;cancelQueryTimeout=-1;sslProtocol=TLS;jaasConfigurationName=SQLJDBCDriver;statementPoolingCacheSize=0;serverPreparedStatementDiscardThreshold=10;enablePrepareOnFirstPreparedStatementCall=false;fips=false;socketTimeout=0;authentication=NotSpecified;authenticationScheme=nativeAuthentication;xopenStates=false;datetimeParameterType=datetime2;sendTimeAsDatetime=true;replication=false;trustStoreType=JKS;trustServerCertificate=false;TransparentNetworkIPResolution=true;iPAddressPreference=IPv4First;serverNameAsACE=false;sendStringParametersAsUnicode=true;selectMethod=direct;responseBuffering=adaptive;queryTimeout=-1;packetSize=8000;multiSubnetFailover=false;loginTimeout=30;lockTimeout=-1;lastUpdateCount=true;prepareMethod=prepexec;encrypt=false;disableStatementPooling=true;databaseName=inkView;columnEncryptionSetting=Disabled;applicationName=Database Navigator - Main;applicationIntent=readwrite;";

    private static final String USER = "sa";
    private static final String PSSWD = "conexaoPI123";
    private Connection con;

    public ConnectionSQL() throws SQLException {
        con  = DriverManager.getConnection(URL, USER, PSSWD);
    }

    public Connection getCon() {
        return con;
    }
}
