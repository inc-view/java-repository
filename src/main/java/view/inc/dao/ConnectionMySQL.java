package view.inc.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
public class ConnectionMySQL {

        private JdbcTemplate connection;

        public ConnectionMySQL(){
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/inkView");
            dataSource.setUsername("aluno");
            dataSource.setPassword("sptech");

            connection = new JdbcTemplate(dataSource);
        }

        public JdbcTemplate getConnection(){
            return  connection;
        }

    }

