package com.powerup.visitmicroservice.application.client.handler;

import com.powerup.visitmicroservice.application.client.dto.HouseInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "property-microservice")
public interface PropertyHandlerClient {
    
    @GetMapping("/api/v1/house/read/{houseId}")
    HouseInfoResponse getHouseInfoById(@PathVariable("houseId") Long houseId);
}
