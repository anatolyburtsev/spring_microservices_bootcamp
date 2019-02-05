package ru.java.mentor.feign_client;

import feign.Param;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("ConfigClient")
public interface ConfigClient {
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String getHello();

    @RequestMapping(method = RequestMethod.GET, value="/service-instances/{applicationName}")
    List<ServiceInstance> getInstanceInfo(@PathVariable("applicationName") String applicationName);
}
