package view.inc.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
public class Connection {

    private JdbcTemplate connection;

    public Connection() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/inkView");
        dataSource.setUsername("root");
        dataSource.setPassword("@eduufreire");

        connection = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConnection(){
        return  connection;
    }

}
