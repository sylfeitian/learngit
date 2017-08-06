package cloud.simple.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cloud.simple.model.User;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
/**
 * service包封装了服务接口访问
 * @author Administrator
 * 这里使用了断路器，就是@HystrixCommand注解。断路器的基本作用就是@HystrixCommand注解的方法失败后，
 * 系统将自动切换到fallbackMethod方法执行。断路器Hystrix具备回退机制、请求缓存和请求打包以及监控和配置等功能，
 * 在这里我们只是使用了它的核心功能：回退机制，使用该功能允许你快速失败并迅速恢复或者回退并优雅降级。
　　这里使用restTemplate进行服务调用，因为使用了服务注册和发现，
	所以我们只需要传入服务名称SERVICE_NAME作为url的根路径，
	如此restTemplate就会去EurekaServer查找服务名称所代表的服务并调用。
	而这个服务名称就是在服务提供端cloud-simple-service中spring.application.name所配置的名字，
	这个名字在服务启动时连同它的IP和端口号都注册到了EurekaServer。
　　封装好服务调用后，你只需要在Controller类里面注入这个服务即可
 *
 */
@Service
public class UserService {

	@Autowired
	RestTemplate restTemplate;
	final String SERVICE_NAME = "cloud-simple-service";	
	
	@SuppressWarnings("unchecked")
	@HystrixCommand(fallbackMethod = "fallbackSearchAll")
	public List<User> searchAll() {
		return restTemplate.getForObject("http://"+SERVICE_NAME+"/user", List.class);
	}
	
	public List<User> fallbackSearchAll() {
		System.out.println("HystrixCommand fallbackMethod handle!");

        List<User> ls = new ArrayList<User>();

        User user = new User();

        user.setUsername("TestHystrix");

        ls.add(user);

        return ls;
	}
}
