//package ua.cv.tim.configuration;
//
///**
// * Created by Oleg on 04.01.2017.
// */
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import ua.cv.tim.model.Player;
//import ua.cv.tim.model.User;
//import ua.cv.tim.model.Village;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//
///**
// * Created by vyach on 28.12.2016.
// */
//
//@Configuration
//@EnableTransactionManagement
//@ComponentScan({"ua.cv.tim.service,ua.cv.tim.dao,ua.cv.tim.model,ua.cv.tim.exception"})
//@PropertySource(value = { "classpath:hibernateTest.properties" })
//public class HibernateTestConfig {
//
//    @Autowired
//    private Environment environment;
//
//
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan(new String[] {"ua.cv.tim" });
//        sessionFactory.setHibernateProperties(getHibernateProperties());
//        return sessionFactory;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
//        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
//        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
//        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
//        return dataSource;
//    }
//
//    @Bean
//    public Class<Player> playerClass(){
//	    return Player.class;
//    }
//
//    @Bean
//    public Class<User> userClass(){
//        return User.class;
//    }
//
//    @Bean
//    public Class<Village> villageClass(){
//        return Village.class;
//    }
//
//    private Properties getHibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
//        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
////		properties.put("hibernate.classloading.use_current_tccl_as_parent",environment.getRequiredProperty("hibernate.classloading.use_current_tccl_as_parent"));
//        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
////		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
//        return properties;
//    }
//
//    @Bean
//    @Autowired
//    public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager txManager = new HibernateTransactionManager();
//        txManager.setSessionFactory(sessionFactory);
//        return txManager;
//    }
//}
