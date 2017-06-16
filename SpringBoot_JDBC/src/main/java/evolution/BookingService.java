package evolution;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookingService {

    private final static Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Transactional// Roll back occurs once there is an exception.
    public void book(String... persons) {
        for (String person : persons) {
            logger.info("Booking " + person + " in a seat...");
            // Update Statement
            jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);
        }
    }

    // The following two methods have the same effects.
    public List<String> findAllBookings() {
        return jdbcTemplate.query("select FIRST_NAME from BOOKINGS",
                (rs, rowNum) -> rs.getString("FIRST_NAME"));
    }
    
    public List<String> findAllBookingsInTraditionalWay() {
    	return jdbcTemplate.query("select FIRST_NAME from BOOKINGS",
    			new RowMapper<String>() {
    		@Override
    		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
    			return rs.getString("FIRST_NAME");
    		}
    	});
    }

}
