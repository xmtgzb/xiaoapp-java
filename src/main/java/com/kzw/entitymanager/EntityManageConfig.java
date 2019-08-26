//package com.kzw.entitymanager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManager;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "entityManageFactoryPrimary",
//        transactionManagerRef = "transactionManagerPrimary",
//        basePackages = {"com.kzw.entity"}
//)
//public class EntityManageConfig {
//
//    @Autowired
//    @Qualifier("myDataSource")
//    private DataSource myDataSource;
//
//    @Primary
//    @Bean(name = "entityManagerPrimary")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
//        return entityManageFactory(builder).getObject().createEntityManager();
//    }
//
//    @Primary
//    @Bean(name = "entityManageFactoryPrimary")
//    public LocalContainerEntityManagerFactoryBean entityManageFactory(EntityManagerFactoryBuilder builder){
//        LocalContainerEntityManagerFactoryBean entityManagerFactory =  builder.dataSource(myDataSource)
//                .packages("com.hope.monsterlan.entity").build();
//        Properties jpaProperties = new Properties();
//        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        jpaProperties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
//        jpaProperties.put("hibernate.connection.charSet", "utf-8");
//        jpaProperties.put("hibernate.show_sql", "false");
//        entityManagerFactory.setJpaProperties(jpaProperties);
//        return entityManagerFactory;
//    }
//
//    @Primary
//    @Bean(name = "transactionManagerPrimary")
//    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManageFactory(builder).getObject());
//    }
//
//}
