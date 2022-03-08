package com.lc.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.net.URLPermission;

/*
指定是否@Bean方法应以执行bean的生命周期行为，例如，即使在直接的情况下返回共享单bean实例获得代理@Bean用户代码的方法调用。 该功能要求的方法的拦截，通过它配有限制的运行时生成的CGLIB子类实现如配置类及其方法不被允许声明final 。
默认为true ，允许通过配置类内部以及外部调用该配置的直接方法调用“bean间引用” @Bean方法，例如从另一个配置类。 如果这不是因为每个的这种特定配置的需要@Bean方法是自包含的并且设计为用于容器使用一个普通的工厂的方法，切换该标志来false以免CGLIB子类的处理。
关闭bean方法拦截有效地处理@Bean方法来单独像非申报时@Configuration类，又名“@Bean精简版模式”（见@Bean's javadoc ）。 因此，它是行为上等同于除去@Configuration铅板。
注解的意思是proxyBeanMethods配置类是用来指定@Bean注解标注的方法是否使用代理，
————————————————
版权声明：本文为CSDN博主「2Tree」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/u010675669/article/details/109010042*/
@Configuration(proxyBeanMethods = false)
public class WebConfig implements WebMvcConfigurer
{
    public HiddenHttpMethodFilter hiddenHttpMethodFilter()
    {
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();
        methodFilter.setMethodParamName("_m");
        return methodFilter;

    }
    //开启springboot矩阵变量
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer)
    {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        //设置路径帮助器，使其不起作用，从而不移除分号后面的内容，从而使矩阵变量生效
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }
}
