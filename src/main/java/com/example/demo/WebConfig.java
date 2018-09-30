package com.example.demo;

import java.nio.charset.Charset;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class WebConfig{
	
	@Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteClassName,//输出类名字
                SerializerFeature.WriteMapNullValue
        );
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        converter.setFastJsonConfig(fastJsonConfig); 
        return converter;
    }
	
	/**
	 * 跨域全局配置
	 */
	@Bean    
	public WebMvcConfigurer corsConfigurer() {        
		return new WebMvcConfigurerAdapter() {          
			@Override          
			public void addCorsMappings(CorsRegistry registry) {              
				registry.addMapping("/fastjson/**")                      
				.allowedOrigins("http://localhost:8088")// 允许 8088 端口访问
				.allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
				.allowCredentials(false)
				.maxAge(3600);
				}        
		};
	}

}
