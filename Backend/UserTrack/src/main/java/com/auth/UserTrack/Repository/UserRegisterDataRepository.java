package com.auth.UserTrack.Repository;

import com.auth.UserTrack.domain.UserRegister;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegisterDataRepository extends MongoRepository<UserRegister,String> {
}
  //user userid prim
//playist userid  prima playlist name songname
//song  --songname
//addsong  -- playist songname