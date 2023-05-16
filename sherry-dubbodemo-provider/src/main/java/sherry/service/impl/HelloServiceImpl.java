package sherry.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import sherry.service.HelloService;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/18 14:45
 **/
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello8003 " + name;
    }
}
