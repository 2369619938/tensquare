package com.tensquare.friend.service;

import com.tensquare.friend.dao.FrientDao;
import com.tensquare.friend.dao.NoFrientDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FrientService {
    @Autowired
    private FrientDao frientDao;

    @Autowired
    private NoFrientDao noFrientDao;
    public int addFriend(String id, String friendid) {
        //判断是否重复添加好友 返回0
        Friend friend = frientDao.findByUseridAndfAndFriendid(id, friendid);
        if(friend!=null){
            return 0;
        }
        //直接添加Islike变成0  0单向喜欢 1双向
        friend =new Friend();
        friend.setUserid(id);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        frientDao.save(friend);
        //判断b到a是否已经添加过 双方的Islike都变成1
        if(frientDao.findByUseridAndfAndFriendid(friendid,id)!=null){
            frientDao.updateIslike("1",id,friendid);
            frientDao.updateIslike("1",friendid,id);
        }
        return 1;
    }

    public int addNoFriend(String id, String friendid) {
        //判断是否已经是非好友 返回0
        NoFriend nofriend = noFrientDao.findByUseridAndfAndFriendid(id, friendid);
        if(nofriend!=null){
            return 0;
        }
        nofriend=new NoFriend();
        nofriend.setUserid(id);
        nofriend.setFriendid(friendid);
        noFrientDao.save(nofriend);
        return 1;
    }

    public void deleteFriend(String id, String friendid) {
        //删除好友表
        frientDao.deletefried(id,friendid);
        //更新islike
        frientDao.updateIslike("0",friendid,id);
        //非好友表添加数据
        NoFriend nofriend=new NoFriend();
        nofriend.setUserid(id);
        nofriend.setFriendid(friendid);
        noFrientDao.save(nofriend);
    }
}
