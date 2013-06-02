package br.com.linuxgames.model.dao.core;
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;



public class LinuxGamesDataSource {

	private static Logger logger = Logger.getLogger(LinuxGamesDataSource.class);
	private static DataSource dataSource;

    static  {
        logger.info("Configurando data source.");
       	dataSource = setupDataSource();
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
        	logger.info("Criando conexao");
            conn = dataSource.getConnection();
        } catch(SQLException e) {
        	logger.error("Erro ao criar a conexao",e);
        } finally {
        	printDataSourceStats();
        }

    	return conn;
    }

    private static DataSource setupDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("boaglio_linuxgam");
        ds.setPassword("boaglio_linuxgam");
        ds.setUrl("jdbc:mysql://localhost/linuxgames");
        return ds;
    }

    private static void printDataSourceStats() {
        BasicDataSource bds = (BasicDataSource) dataSource;
        logger.info("NumActive: " + bds.getNumActive() + " NumIdle: " + bds.getNumIdle());
    }

    public static void shutdownDataSource(DataSource ds) throws SQLException {
        BasicDataSource bds = (BasicDataSource) ds;
        bds.close();
    }

	public static DataSource getDataSource() {
		return dataSource;
	}

}