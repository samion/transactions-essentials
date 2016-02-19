/**
 * Copyright (C) 2000-2016 Atomikos <info@atomikos.com>
 *
 * This code ("Atomikos TransactionsEssentials"), by itself,
 * is being distributed under the
 * Apache License, Version 2.0 ("License"), a copy of which may be found at
 * http://www.atomikos.com/licenses/apache-license-2.0.txt .
 * You may not use this file except in compliance with the License.
 *
 * While the License grants certain patent license rights,
 * those patent license rights only extend to the use of
 * Atomikos TransactionsEssentials by itself.
 *
 * This code (Atomikos TransactionsEssentials) contains certain interfaces
 * in package (namespace) com.atomikos.icatch
 * (including com.atomikos.icatch.Participant) which, if implemented, may
 * infringe one or more patents held by Atomikos.
 * It should be appreciated that you may NOT implement such interfaces;
 * licensing to implement these interfaces must be obtained separately from Atomikos.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package com.atomikos.jdbc.nonxa;

import java.sql.SQLException;

import junit.framework.TestCase;

import com.atomikos.jdbc.AtomikosSQLException;

public class AtomikosNonXADataSourceBeanTestJUnit extends TestCase 
{
	private AtomikosNonXADataSourceBean ds;
	
	protected void setUp() {
		ds = new AtomikosNonXADataSourceBean();
	}
	
	protected void tearDown() {
		if ( ds != null ) ds.close();
	}
	
	public void testDriverClassName() 
	{
		assertNull ( ds.getDriverClassName() );
		String name = "driver";
		ds.setDriverClassName ( name );
		assertEquals ( name , ds.getDriverClassName() );
	}
	
	public void testPassword()
	{
		assertNull ( ds.getPassword() );
		String pw = "secret";
		ds.setPassword( pw );
		assertEquals ( pw , ds.getPassword() );
	}
	
	public void testUrl()
	{
		assertNull ( ds.getUrl() );
		String url = "url";
		ds.setUrl(url);
		assertEquals ( url , ds.getUrl() );
	}
	
	public void testUser()
	{
		assertNull ( ds.getUser() );
		String usr = "user";
		ds.setUser ( usr );
		assertEquals ( usr , ds.getUser() );
		
	}
	
	public void testBorrowConnectionTimeout()
	{
		assertEquals ( 30 , ds.getBorrowConnectionTimeout() );
		ds.setBorrowConnectionTimeout( 14 );
		assertEquals ( 14 , ds.getBorrowConnectionTimeout() );
	}
	
	public void testLoginTimeout () throws SQLException
	{
		assertEquals ( 0 , ds.getLoginTimeout() );
		ds.setLoginTimeout( 5 );
		assertEquals ( 5 , ds.getLoginTimeout() );
	}
	
	public void testMaintenanceInterval()
	{
		assertEquals ( 60 , ds.getMaintenanceInterval() );
		ds.setMaintenanceInterval( 13 );
		assertEquals ( 13 , ds.getMaintenanceInterval() );
	}
	
	public void testMaxIdleTime()
	{
		assertEquals ( 60 , ds.getMaxIdleTime() );
		ds.setMaxIdleTime( 11 );
		assertEquals ( 11 , ds.getMaxIdleTime() );
	}
	
	public void testMaxPoolSize() 
	{
		assertEquals ( 1 , ds.getMaxPoolSize() );
		ds.setMaxPoolSize( 3 );
		assertEquals ( 3 , ds.getMaxPoolSize() );
	}
	
	public void testMinPoolSize() 
	{
		assertEquals ( 1 , ds.getMinPoolSize() );
		ds.setMinPoolSize( 4 );
		assertEquals ( 4 , ds.getMinPoolSize() );
	}
	
	public void testPoolSize()
	{
		assertEquals ( 1 , ds.getMinPoolSize() );
		assertEquals ( 1 , ds.getMaxPoolSize() );
		ds.setPoolSize ( 3 );
		assertEquals ( 3 , ds.getMinPoolSize() );
		assertEquals ( 3 , ds.getMaxPoolSize() );
		
	}
	
	public void testReapTimeout()
	{
		assertEquals ( 0 , ds.getReapTimeout() );
		ds.setReapTimeout( 33 );
		assertEquals ( 33 , ds.getReapTimeout() );
	}
	
	public void testTestQuery()
	{
		assertNull ( ds.getTestQuery() );
		String query = "haha";
		ds.setTestQuery ( query );
		assertEquals ( query , ds.getTestQuery() );
	}
	
	public void testUniqueResourceName() 
	{
		assertNull ( ds.getUniqueResourceName() );
		String name = "resource";
		ds.setUniqueResourceName( name );
		assertEquals ( name , ds.getUniqueResourceName()  );
	}
	
	public void testInitWithDriverClassNotFoundThrowsMeaningfulException () throws SQLException
	{
		ds.setUniqueResourceName( "test" );
		ds.setDriverClassName ( "com.example.NonExistingClass" );
		try {
			ds.getConnection();
			fail ( "getConnection works without existing driver class" );
		} catch ( AtomikosSQLException ok ) {
			ok.printStackTrace();
			String expectedMsg = "Driver class not found: 'com.example.NonExistingClass' - please make sure the spelling is correct.";
            assertEquals ( expectedMsg , ok.getMessage() );
		}		
	}
	
	public void testInitWithInvalidDriverClassThrowsMeaningfulException () throws SQLException
	{
		ds.setUniqueResourceName( "test" );
		ds.setDriverClassName ( "java.lang.String" );
		try {
			ds.getConnection();
			fail ( "getConnection works with invalid driver class" );
		} catch ( AtomikosSQLException ok ) {
			ok.printStackTrace();
			String expectedMsg = "Driver class 'java.lang.String' does not seem to be a valid JDBC driver - please check the spelling and verify your JDBC vendor's documentation";
            assertEquals ( expectedMsg , ok.getMessage() );
		}		
	}
	
	public void testReadOnly() throws Exception 
	{
		assertFalse ( ds.getReadOnly() );
		ds.setReadOnly ( true );
		assertTrue ( ds.getReadOnly() );
		ds.setReadOnly ( false );
		assertFalse ( ds.getReadOnly() );
	}
	
	public void testDefaultIsolationLevel() throws Exception 
	{	
		assertEquals ( -1 , ds.getDefaultIsolationLevel() );
		ds.setDefaultIsolationLevel( 0 );
		assertEquals ( 0 , ds.getDefaultIsolationLevel() );
	}

}
