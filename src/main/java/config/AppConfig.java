package config;
import java.util.Properties;

import entity.Goods;
import entity.OrderOne;
import entity.Orders;
import entity.PersonalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.hibernate.cfg.Environment.*;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {@ComponentScan("dao"), @ComponentScan("service")})
public class AppConfig {
    private Environment env;

    @Autowired
    public void setEnvironment(Environment env) {
        this.env = env;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        Properties properties = new Properties();
        properties.put(DRIVER, env.getProperty("jdbc.driverClassName"));
        properties.put(URL,env.getProperty("jdbc.url"));
        properties.put(USER, env.getProperty("jdbc.username"));
        properties.put(PASS, env.getProperty("jdbc.password"));
        properties.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
        properties.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put(DIALECT, env.getProperty("hibernate.dialect"));
        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(PersonalData.class, Goods.class, OrderOne.class, Orders.class);
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
