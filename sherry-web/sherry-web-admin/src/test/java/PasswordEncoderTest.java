import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SherryTh743779@gmail.com
 * @Date: 2023/5/29 9:49
 **/
public class PasswordEncoderTest {
    /*
     * @Description 测试New BCryptPasswordEncoder密码加密器
     * @Date  2023/5/29 9:50
     * @Param []
     * @return void
     * @Author SHERRY
     **/
    /**
     * @Description bCryptPasswordEncoder的加密格式
     * @$ 是分隔符
     * @2a 是bcrypt加密版本号
     * @& 第二个分隔符
     * @10 是cost的值
     * @& 第三个分隔符
     * @后面的前22位为salt值 salt值是随机生成的一组字符串，可以包括随机的大小写字母、数字、字符，位数可以根据要求而不一样。 用途：当用户首次提供密码时（通常是注册时），由系统自动添加随机生成的salt值，然后再散列。 而当用户登录时，系统为用户提供的代码撒上同样的加盐值，然后散列，再比较散列值，以确定密码是否正确。
     * @22位后为密码的密文 加了密的的文字->有固定格式的明文加密后字符串 31位
     * @Date  2023/5/29 9:58
     * @Param []
     * @return void
     * @Author SHERRY
     **/
    @Test
    public void testBCryptPasswordEncoder(){
        //创建加密器对象
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //对123456进行加密
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println("encode = " + encode);

        //第一次加密的结果
        //encode = $2a$10$uAuEM5fsXDlFWvx3D3hWoO27t0pq5ZcttNz.Lh8RBCl4r4hgwkYBi
        //第二次加密的结果
        //encode = $2a$10$6V/pNLqhclB6fQkro4PhGeImGX/Z6CulgvWr8ZKda32IrSa4ytbW2
        //第三次运行的结果
        //encode = $2a$10$H0KM0uWESdFoR90u5i7llutauma5UNV/PL9GdbvdXDAg1w4FLj6hi

        //进行密码匹配
        boolean matches = bCryptPasswordEncoder.matches("123456", "$2a$10$uAuEM5fsXDlFWvx3D3hWoO27t0pq5ZcttNz" +
                ".Lh8RBCl4r4hgwkYBi");
        System.out.println("matches = " + matches);
        boolean matches1 = bCryptPasswordEncoder.matches("123456", "$2a$10$6V/pNLqhclB6fQkro4PhGeImGX" +
                "/Z6CulgvWr8ZKda32IrSa4ytbW2");
        System.out.println("matches1 = " + matches1);
        boolean matches2 = bCryptPasswordEncoder.matches("123456", "$2a$10$H0KM0uWESdFoR90u5i7llutauma5UNV" +
                "/PL9GdbvdXDAg1w4FLj6hi");
        System.out.println("matches2 = " + matches2);

    }

    /**
     *Spring Security进阶使用的实现思路
     * @1. 使用指定的登录页面(login.html)
     * @2. 配置可匿名访问的资源(不需要登录权限和角色,就可以访问的资源,如:静态资源等)
     * @3. 从数据库查询用户信息
     * @4. 对密码进行加密
     * @5. 配置多种校验规则(对访问的页面做权限控制,按钮控制)
     * @6. 注解方式权限控制(对访问的Controller类中的方法做权限控制)
     **/
}
