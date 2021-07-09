package tv.junnikym.kwitch;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/dataSource-context.xml" })
public class PostgresqlConnectionTest {
	@Inject
	private DataSource dataSource;

	@Test
	public void testConnection() {
		try (Connection conn = dataSource.getConnection()){
			System.out.println("PostgresSQL Connection : " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
