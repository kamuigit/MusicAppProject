package com.auth.UserTrack.proxy;



import com.auth.UserTrack.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name ="UserAuthentication",url ="http://localhost:4500")
public interface UserProxy {
    @PostMapping("/api/user/register")
    public ResponseEntity<?> registerAccount(@RequestBody UserDto userDto);
}
