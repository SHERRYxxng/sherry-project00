package sherry.config;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import sherry.entity.Admin;
import sherry.service.AdminService;
import sherry.service.PermissionService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 我们如果要查询数据库就要自己写个类,
 * @Author: SHERRY
 * @email: SherryTh743779@gmail.com
 * @Date: 2023/5/29 16:16
 **/

@Component //创建这个类的对象实现UserDetailsService,让Spring Security调用这个类执行我们查询数据库的授权过程,而不是用自带的内存中的用户名和密码
public class SherryUserDetailService  implements UserDetailsService {
    @Reference
    AdminService adminService;

    @Reference
    private PermissionService permissionService;
    /**
     * @Description 登录时SpringSecurity会自动调用该方法,并将用户名传入到该方法中
     * @Date  2023/5/29 16:18
     * @Param [s]
     * @return org.springframework.security.core.userdetails.UserDetails
     * @Author SHERRY
     **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //调用AdminService中根据用户名查询Admin对象的方法
        Admin admin=adminService.getAdminByUsername(username);
        if(admin ==null){
            throw new UsernameNotFoundException("用户不存在!");
        }
        //调用PermissionService中获取当前用户权限码的方法
        List<String> permissionCodes = permissionService.getPermissionCodesByAdminId(admin.getId());

        //穿件一个用户授权的集合
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        //遍历得到每一个权限码
        for(String permissionCode : permissionCodes){
            //创建GrantedAuthority对象
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permissionCode);
            //将对象放到集合里面
            grantedAuthorities.add(simpleGrantedAuthority);
        }
        //给用户授权
        /*
        给用户授权有两种标识方式:
            1.通过角色的方式标识:例如:Role_ADMIN
            2.直接设置权限,例如:Delete,Query,Update
         */
//        return new User(username,admin.getPassword(),
//                AuthorityUtils.commaSeparatedStringToAuthorityList(""));
        return new User(username,admin.getPassword(),grantedAuthorities);
    }
}
