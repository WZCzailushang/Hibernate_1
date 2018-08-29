package com.wzc.hello;

import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.SessionFactoryServiceInitiator;
import org.hibernate.service.spi.SessionFactoryServiceRegistryBuilder;
import org.junit.Test;

public class HibernateTest {

	@Test
	public void test() {

        //1、创建一个sessionFactory对象
        SessionFactory sessionFactory = null;

        //1)、创建Configuration  对象；对应Hibernate 的基本配置信息和对象关系映射信息
        Configuration configuration = new Configuration().configure();

        //4.0之前这样创建
        sessionFactory = configuration.buildSessionFactory();
        
        /**
         * 按照下面這樣创建sessionFactory有错误 不知道为什么
         */

        //2)、创建一个ServiceRegister  对象；hibernate 4.x  新添加的对象
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
//        		applySettings(configuration.getProperties()).build();
//        
//        
//        sessionFactory = configuration.buildSessionFactory(serviceRegistry);


        //2、创建一个session对象
        Session session = sessionFactory.openSession();

        //3、开启事务
        //所有操作都应该在事务下进行，否则在数据库中不会执行
        Transaction transaction = session.beginTransaction();

        //4、执行保存操作

        /**
         * 这是插入操作
         */
        News news = new News("Java", "WZC", new Date());
        session.save(news);
        
        /**
         * 这是获取操作,这里Hibernate底层用的反射机制，所以News必须要有空参构造
         */
        News news2 = (News)session.get(News.class, 1);
        System.out.println(news2.getAuthor());
    

        //5、提交事务
        //如果要使事务生效，则必须要提交事务
        transaction.commit();


        //6、关闭session
        session.close();

        //7、关闭SessionFactory 对象

        sessionFactory.close();

	}

}
