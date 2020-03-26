package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "tensquare-user")
public interface UserClient {
    @RequestMapping(value = "/{userid}/{friendid}/{x}" , method = RequestMethod.PUT)
    public void  updaefanscountandfollowcount(@PathVariable("userid") String userid, @PathVariable("friendid") String friendid, @PathVariable("x") int x);
}
