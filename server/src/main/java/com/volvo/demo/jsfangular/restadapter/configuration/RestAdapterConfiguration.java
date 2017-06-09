package com.volvo.demo.jsfangular.restadapter.configuration;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.volvo.demo.jsfangular.restadapter.configuration.interceptor.RestAdapterInterceptor;
import com.volvo.demo.jsfangular.restadapter.configuration.interceptor.RestCacheInterceptor;
import com.volvo.demo.jsfangular.restadapter.configuration.resolver.FacesContextMethodArgumentResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.volvo.demo.jsfangular.restadapter.api")
public class RestAdapterConfiguration extends WebMvcConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(RestAdapterConfiguration.class);

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);

        argumentResolvers.add(new FacesContextMethodArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);

        registry.addInterceptor(new RestAdapterInterceptor());
        registry.addInterceptor(new RestCacheInterceptor());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);

        configureJsonMapper(converters);
    }

    private void configureJsonMapper(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter.getClass().isAssignableFrom(MappingJackson2HttpMessageConverter.class)) {
                LOG.debug("Turning on JSON pretty print!");
                MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
                jsonConverter.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            }
        }
    }

}
