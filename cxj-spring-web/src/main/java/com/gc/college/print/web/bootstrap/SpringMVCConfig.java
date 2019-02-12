package com.gc.college.print.web.bootstrap;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

/**
 * Created by chenxiaojie on 18/8/06.
 */
@Slf4j
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@ComponentScan("com.gc.college.print")
public class SpringMVCConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html; charset=UTF-8");
        viewResolver.setCache(true);
        viewResolver.setRedirectHttp10Compatible(false);
        return viewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/");
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        Properties properties = new Properties();
        properties.setProperty("template_update_delay", "0");
        properties.setProperty("locale", "zh_CN");
        properties.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
        properties.setProperty("date_format", "yyyy-MM-dd");
        properties.setProperty("number_format", "#.##");
        freeMarkerConfigurer.setFreemarkerSettings(properties);
        return freeMarkerConfigurer;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxInMemorySize(5242880);
        multipartResolver.setMaxUploadSize(20971520);
        return multipartResolver;
    }

    @Bean
    public HttpMessageConverter httpMessageConverter() {
        FastJsonHttpMessageConverter messageConverter = new FastJsonHttpMessageConverter();
        messageConverter.setDefaultCharset(Charset.forName("UTF-8"));
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        List<MediaType> mediaTypes = Lists.newArrayListWithExpectedSize(1);
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);//设定json格式且编码为UTF-8
        messageConverter.setSupportedMediaTypes(mediaTypes);
        messageConverter.setFastJsonConfig(fastJsonConfig);
//        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        objectMapper.setTimeZone(TimeZone.getDefault());
//        objectMapper.disable(MapperFeature.INFER_CREATOR_FROM_CONSTRUCTOR_PROPERTIES);
//        messageConverter.setObjectMapper(objectMapper);
        return messageConverter;
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, httpMessageConverter());
    }

    //    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        boolean isAddJackson = false;
//        for (int i = 0; i < converters.size(); i++) {
//            if (converters.get(i) instanceof MappingJackson2HttpMessageConverter) {
//                converters.set(i, httpMessageConverter());
//                isAddJackson = true;
//            }
//        }
//
//        if (!isAddJackson) {
//            converters.add(0, httpMessageConverter());
//        }
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

}
