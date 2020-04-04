package es.dad.easynotes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class EasyNotesApplication {

	private static final Log logger = LogFactory.getLog(EasyNotesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EasyNotesApplication.class, args);
	}

	@Bean
	public CacheManager cacheManager() {
		logger.info("Activating cache");
		return new ConcurrentMapCacheManager("apuntes");
	}

}
