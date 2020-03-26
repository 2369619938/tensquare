package com.tensquare.friend.dao;



import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NoFrientDao extends JpaRepository<NoFriend,String> {
    public NoFriend findByUseridAndfAndFriendid(String userid, String friendid);


}
