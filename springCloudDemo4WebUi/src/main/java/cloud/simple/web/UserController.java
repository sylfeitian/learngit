package cloud.simple.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cloud.simple.model.User;
import cloud.simple.service.UserService;

/**
 * 
 * @author Administrator
 *
 *这里使用restTemplate进行服务调用，因为使用了服务注册和发现，
 *所以我们只需要传入服务名称SERVICE_NAME作为url的根路径，如此restTemplate就会去EurekaServer查找服务名称所代表的服务并调用。而这个服务名称就是在服务提供端cloud-simple-service中spring.application.name所配置的名字，
 *这个名字在服务启动时连同它的IP和端口号都注册到了EurekaServer
 */
@RestController
public class UserController {

	@Autowired
	UserService userService;
	public ResponseEntity<List<User>> readUserInfo() {
		 List<User> users=userService.searchAll();         

         return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
}
