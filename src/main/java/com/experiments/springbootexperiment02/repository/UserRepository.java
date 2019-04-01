package com.experiments.springbootexperiment02.repository;

import com.experiments.springbootexperiment02.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    /**
     * 添加用户，并返回包括数据库时间戳的用户对象
     *
     * @param user
     * @return
     */
    public User addUser(User user) {
        em.persist(user);
        return user;
    }

    /**
     * 添加地址，并指定地址对应的用户
     *
     * @param address
     * @param uid
     * @return
     */
    public Address addAddress(Address address, int uid) {
        User user1 = em.find(User.class, uid);
        address.setUser(user1);
        em.persist(address);
        return address;
    }

    /**
     * 更新指定ID用户的姓名
     *
     * @param uid
     * @param newName
     * @return
     */
    public User updateUser(int uid, String newName) {
        User user1 = em.find(User.class, uid);
        em.persist(user1);
        user1.setName(newName);
        return user1;
    }

    /**
     * 尝试使用merge()，以及find()2种方法分别实现
     * 更新指定地址为指定用户
     *
     * @param aid
     * @param uid
     * @return
     */
    public Address updateAddress(int aid, int uid) {
        /*
        *merge方法
        Address address1=new Address();
        address1.setId(aid);
        Address address2=em.merge(address1);
        em.refresh(address2);
        System.out.println(address2.getId());
        User user1=new User();
        user1.setId(uid);
        User user2=em.merge(user1);
        em.refresh(user2);
        address2.setUser(user2);
        return  address2;
        */
        Address address1 = em.find(Address.class, aid);
        User user1 = em.find(User.class, uid);
        address1.setUser(user1);
        return address1;
    }

    /**
     * 返回指定用户的全部地址，没有返回空集合，而非null
     *
     * @param uid
     * @return
     */
    public List<Address> listAddresses(int uid) {
        List<Address> list = em.find(User.class, uid).getAddresses();
        List.of(list);
        return list;
    }

    public void removeAddress(int aid) {
        Address address1 = em.find(Address.class, aid);
        em.remove(address1);
    }

    /**
     * 删除用户，设置级联操作或手动删除相关地址
     *
     * @param uid
     */
    public void removeUser(int uid) {
        User user1 = em.find(User.class, uid);
        em.remove(user1);
    }
}
