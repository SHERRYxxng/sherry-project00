package sherry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description: 这是启用Spring Security的java配置类,这个名字可以随便取
 * @Author: SHERRY
 * @email: SherryTh743779@gmail.com
 * @Date: 2023/5/28 14:10
 **/

@Configuration//这个注解的作用是声明当前类是一个配置类,声明了也需要在spring-mvc中扫描当前类
@EnableWebSecurity//开启Spring Security的自动配置,会给我们生成一个登录页面
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启Controller中方法的权限控制
public class SherrySecurityConfig extends WebSecurityConfigurerAdapter {
   /**
    * @Description 内存分配用户名和密码,这是测试用的
    * @Date 2023/5/28 14:30
    * @Param [auth]
    * @return void
    * @Author SHERRY
    **/
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        /*
//         * @Description 单项,随机言,60位,加密每次得到的密码都不一样
//         * @Date  2023/5/28 14:32
//         * @Param [auth]
//         * @return void
//         * @Author SHERRY
//         **/
//        auth.inMemoryAuthentication()
//                .withUser("sherry")
//                .password(new BCryptPasswordEncoder().encode("123456"))
//                .roles("");
//    }
    /*
     * @Description 必须指定加密方式，上下加密方式要一致,这里都用的BCryptPasswordEncoder
     * @Date  2023/5/28 15:21
     * @Param []
     * @return org.springframework.security.crypto.password.PasswordEncoder
     * @Author SHERRY
     **/
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //spring Security自带的认证授权
//    /*
//     * @Description 使用spring Security的默认页面和方法
//     * @Date  2023/5/28 15:21
//     * @Param [http]
//     * @return void
//     * @Author SHERRY
//     **/
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //必须调用父类的方法，否则认证过程将失效
//        super.configure(http);
//        //允许iframe嵌套显示
//        //http.headers().frameOptions().disable();
//        //允许iframe显示 两者都可以,上面可以iframe中再有iframe下面不行,这里我们用的是同一个域
//        http.headers().frameOptions().sameOrigin();
//    }
    /*
     * @Description 配置认证授权,这里面是设置响头,允许一个页面在另一个页面中显示
     * @Date  2023/5/29 11:21
     * @Param [http]
     * @return void
     * @Author SHERRY
     **/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                //允许iframe嵌套显示
        //http.headers().frameOptions().disable();
        // 允许iframe显示 两者都可以,上面可以iframe中再有iframe下面不行,这里我们用的是同一个域
       http.headers().frameOptions().sameOrigin();
        /**
         * @Description 配置可以匿名访问的资源,不需要登录认证就可以访问
         * @http.authorizeRequests() 表示开始对请求进行授权设置。
         * @.antMatchers("/static/**","/login").permitAll() 表示 /static/** 和 /login 这两个请求不需要进行身份验证即可访问。
         * @.anyRequest().authenticated() 表示除了上面两个请求之外的所有请求都需要身份验证才能访问。
         * @Date  2023/5/29 11:26
         * @Param [http]
         * @return void
         * @Author SHERRY
         **/
       //配置可以匿名访问的资源,->不需要认证登录就可以访问
       http.authorizeRequests()
               .antMatchers("/static/**","/login").permitAll()
               .anyRequest().authenticated();
        /**
         * @Description 配置自定义的登录界面
         * @Date 2023/5/29 11:29
         * @Param [http]
         * @return void
         * @Author SHERRY
         **/
       http.formLogin().loginPage("/login")//配置去自定义页面访问的路径
               .defaultSuccessUrl("/");//配置登录成功之后去往的地址
        /**
         * @Description 配置登出地址
         * @Date 2023/5/29 18:49
         * @Param [http]
         * @return void
         * @Author SHERRY
         **/
        //配置登出地址以及登出承购之后去往的地址
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
        //关闭跨域请求伪造
        http.csrf().disable();
    }
}
