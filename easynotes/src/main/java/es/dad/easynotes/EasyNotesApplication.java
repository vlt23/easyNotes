package es.dad.easynotes;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.HazelcastInstanceFactory;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

@SpringBootApplication
@EnableHazelcastHttpSession
@EnableCaching
public class EasyNotesApplication {

	private static final Log logger = LogFactory.getLog(EasyNotesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EasyNotesApplication.class, args);
	}

	@Value("${valor.hazelcast.ip}")
	private String hostIp;

	@Bean
	public Config config() {
		Config config = new Config();
		JoinConfig joinConfig = config.getNetworkConfig().getJoin();
		joinConfig.getMulticastConfig().setEnabled(true);
		return config;
	}

	// Spring Boot also has explicit caching support for Hazelcast.
	// If caching is enabled, the HazelcastInstance is automatically wrapped in a CacheManager implementation.
	// https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-hazelcast
	@Bean
	public HazelcastInstance hazelcastInstance() {
		return HazelcastInstanceFactory.newHazelcastInstance(config());
	}

	@Bean
	public CacheManager cacheManager() {
		logger.info("Activating hazelcast cache");
		return new HazelcastCacheManager(hazelcastInstance());
	}

}
