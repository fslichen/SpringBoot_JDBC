package evolution;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component// Scanned by @ComponentScan in Application.
class AppRunner implements CommandLineRunner {// Once CommandLineRunner is implemented, the following method will be run.
	// Import org.slf4j.Logger and org.slf4j.LoggerFactory
    private final static Logger logger = LoggerFactory.getLogger(AppRunner.class);

    @Autowired
    private BookingService bookingService;

    @Override
    public void run(String... args) throws Exception {
    	bookingService.book("Elsa", "Anna");
    	List<String> books = bookingService.findAllBookings();
    	logger.info(books.toString());
    	System.out.println(books);
    }

}
