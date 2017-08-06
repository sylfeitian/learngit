package cloud.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import feign.Feign;
import feign.Logger;
import feign.Request;

/**
 * 启动服务
 * @author Administrator
 *在spring boot中，已经不推荐使用jsp，所以你如果使用jsp来实现webui端，将会很麻烦。
 *这可能跟现在的开发主流偏重移动端有关，跟微服务有关，跟整个时代当前的技术需求有关。
 *单纯以html来作为客户端，有很多好处，比如更利于使用高速缓存；
 *使后台服务无状态话，更利于处理高并发；更利于页面作为服务，小服务组合成大服务等。
 *
 *
 * 这个应用包括前端html页面，还包括一个后台controller浅层。
 * 这是一个前端应用，为什么要包括controller浅层？
 * 这是因为这个controller浅层是用来做服务组合的，因为一个页面可能涉及到调用多个服务，而这些服务又有可能涉及事务和并发问题，
 * 所以还是需要靠java来完成。当然，也可以单独编写一个api gateway来实现这一层，可以使用go、node.js语言等，
 * 也可以使用java/netty（主流还是java，因为开发部署效率高）。
 *     
 *     Controller层的WebApplication就是这个应用的入口
 *     
 *     
 *      @SpringBootApplication相当于@Configuration、@EnableAutoConfiguration、@ComponentScan三个注解合用。
 *　		@EnableEurekaClient配置本应用将使用服务注册和服务发现，注意：注册和发现用这一个注解。
　　		@EnableHystrix表示启用断路器，断路器依赖于服务注册和发现。
 *     
 *     
 *     
 *     
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class WebApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	
	
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}

	@Bean
	public Logger.Level feignLogger() {
		return Logger.Level.FULL;
	}

	private static final int FIVE_SECONDS = 5000;

	@Bean
	public Request.Options options() {
		return new Request.Options(FIVE_SECONDS, FIVE_SECONDS);
	}

}
