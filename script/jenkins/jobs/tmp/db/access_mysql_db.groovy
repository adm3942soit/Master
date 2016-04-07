import groovy.sql.Sql

// MySQL DB Settings
def dbSchema = "schema"
def dbServer = "mysqlserver"
def dbUser = "user"
def dbPassword = "password"
def dbDriver = 'com.mysql.jdbc.Driver'
def dbUrl = 'jdbc:mysql:@' + dbServer + ':' + dbSchema
sql = Sql.newInstance( dbUrl, dbUser, dbPassword, dbDriver )

// Write data
sql.execute "INSERT INTO table_name (column1,column2,column3,...) VALUES (value1,value2,value3,...)"
