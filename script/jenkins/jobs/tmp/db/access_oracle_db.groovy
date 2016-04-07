import groovy.sql.Sql

// Oracle DB Settings
def dbSchema = "schema"
def dbServer = "oracleserver"
def dbUser = "user"
def dbPassword = "password"
def dbDriver = "oracle.jdbc.driver.OracleDriver"
def dbUrl = 'jdbc:oracle:thin:@' + dbServer + ':' + dbSchema
sql = Sql.newInstance( dbUrl, dbUser, dbPassword, dbDriver )

// Read data
def row = sql.firstRow("SELECT * FROM application_configuration WHERE application_name = ${gitProjectName}")
println row