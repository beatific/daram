package org.beatific.daram.web.config;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.beatific.daram.web.common.DaoRepository;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;

@Configuration
@MapperScan(value="org.beatific.daram.web", annotationClass=DaoRepository.class)
@Import(DatabaseConfig.class)
public class MybatisConfig {

	@Inject DatabaseConfig dataConfig;
	
	@Value("classpath*:META-INF/mapper/oracle/**/*.xml")
	Resource[] mapperLocation;
	
	@Value("classpath:META-INF/mybatis/mybatis-config.xml")
	Resource configLocation;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataConfig.dataSource());
		sessionFactory.setConfigLocation(configLocation);
		sessionFactory.setMapperLocations(mapperLocation);
		try {
            return (SqlSessionFactory) sessionFactory.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	@Bean
    public SqlSession sqlSessionTemplate() {
        return new SqlSessionTemplate(sqlSessionFactory());
    }  
}
