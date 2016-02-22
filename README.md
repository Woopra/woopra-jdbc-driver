Woopra-JDBC-Driver

Enables connecting to the Woopra Data API through a JDBC interface

Only SELECT operations are allowed


## Usage

- Initialize the driver:  `Class.forName("com.woopra.jdbc.driver.WoopraDriver");`

- Create the connection:    `Connection connection = DriverManager.getConnection("jdbc:woopra://[woopra-project-name]", "[api-access-id]", "[api-access-secret]");`
			    `ex: Connection connection = DriverManager.getConnection("jdbc:woopra://my-website.com", "BLLISHYY1HBDUGS89CBT4BU4R288V1FL", "494aS6kMQDSKWPLBQ4Asu5FCdam5bShCBMX0pPI912dTaoT5VdbEWkgG4inFiYmY");`

Optionally the connection can be created with a properties parameter:
			`Properties p=new Properties();
			p.setProperty("user", "access-id");
			p.setProperty("password", "access-secret");
			#optional
			p.setProperty("api.version", "2.4");
			connection=DriverManager.getConnection("jdbcLwoopra://my-website.com", p)`

-Query the data: 
	`
	String query = "select visitors.company as Company, visitors() as Count from visitors group by visitors.company order by Count";

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(">>" + rs.getString("Company") + " :::: " + rs.getString(Count));
        }`
