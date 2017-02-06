package ua.cv.tim.configuration;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by mmaksymtc on 25.01.2017.
 */
@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackages = {"ua.cv.tim"}, excludeFilters={
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value=HibernateConfiguration.class)
})
public class WebConfigurationTest extends WebMvcConfigurerAdapter{

    private static final Logger logger = LoggerFactory.getLogger(WebConfigurationTest.class);
    /*@Autowired
    private Environment environment;
*/


    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"ua.cv.tim.model"});
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/travianTest");
        dataSource.setUsername("postgres");
        dataSource.setPassword("root");
        return dataSource;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL92Dialect");
        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
}

