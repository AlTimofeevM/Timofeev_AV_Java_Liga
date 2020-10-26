package dev.altimofeevm;

import dev.altimofeevm.config.JpaConfig;
import dev.altimofeevm.dao.MessageDao;
import dev.altimofeevm.dao.UserDao;
import dev.altimofeevm.domain.Message;
import dev.altimofeevm.domain.User;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        User user1 = new User(null,"1", "2","3", Date.valueOf("2020-01-20"),"");
        User user2 = new User(null, "First", "Last", "Patro", Date.valueOf("1990-02-10"),"");
        Message message = new Message(null, "message", user1, user2, new Date(System.currentTimeMillis()));
        try {
           UserDao. save(user1);
           UserDao.save(user2);
           System.out.println(UserDao.get("1","2","3"));
           System.out.println(UserDao.get(2l));
            MessageDao.save(message);
            System.out.println(MessageDao.get(3l));
            user1.setId(1l);
            user2.setId(2l);
            System.out.println(UserDao.getDialogBody(user1, user2));
            System.out.println(UserDao.getDialogs(user2));
        } catch (Exception e) {
           e.printStackTrace();
       } finally {
            JpaConfig.getEntityManagerFactory().close();
        }
    }
}
