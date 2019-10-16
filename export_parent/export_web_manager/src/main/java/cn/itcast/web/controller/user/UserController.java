package cn.itcast.web.controller.user;

import cn.itcast.domain.Dept;
import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import cn.itcast.service.DeptService;
import cn.itcast.service.RoleService;
import cn.itcast.service.UserService;
import cn.itcast.utils.Encrypt;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.controller.BaseController;
import cn.itcast.web.utils.JmsTemplateUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/system/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JmsTemplateUtils jmsTemplateUtils;

    @RequestMapping(value = "/list",name = "用户查询")
    public String findPageAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){
        PageInfo page = userService.findPageAll(pageNum, pageSize, companyId);
        request.setAttribute("page",page);
        //到展示页面

        return "system/user/user-list";
    }

    @RequestMapping(value = "/toAdd",name = "用户新建页面")
    public String toAdd(){
        List<Dept> deptList = deptService.findAllDept(companyId);
        request.setAttribute("deptList",deptList);
        //带着所有部门到页面

        return "system/user/user-add";
    }

    @RequestMapping(value = "/edit",name = "用户新增")
    public String edit(User user){
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        if(UtilFuns.isEmpty(user.getId())){
            // 先获取发邮件需要的信息
            String email=user.getEmail();
            String password=user.getPassword();
            String msg="欢迎加入saas大家庭,你的登录账号是："+email+",密码是："+password+"";

            user.setId(UUID.randomUUID().toString().replace("-",""));
            user.setPassword(Encrypt.md5(user.getPassword(),user.getEmail()));
            // 保存
            userService.save(user);
            //消息中间件
            jmsTemplateUtils.send(email,password,msg);

            /*try {
                // 发邮件
                MailUtils.sendMail(email,msg);
            } catch (MessagingException e) {
                e.printStackTrace();
            }*/

        }else{
            userService.update(user);

        }

        //到查询
        return "redirect:/system/user/list.do";
    }


    @RequestMapping(value = "/toUpdate",name = "用户修改页面")
    public String toUpdate(String id){
        //根据id查用户
        User user=userService.findById(id);
        //查询所有用户
        request.setAttribute("user",user);
        List<Dept> deptList = deptService.findAllDept(companyId);
        request.setAttribute("deptList",deptList);
        //到查询
        return "system/user/user-update";
    }

    //根据id删除用户
    @RequestMapping(value = "/delete",name = "删除用户")
    public String delete(String id){

        userService.delete(id);
        //到查询
        return "redirect:/system/user/list.do";
    }


    // 用户分配角色页面展示
    @RequestMapping(value = "/roleList",name = "用户分配角色页面展示")
    public String roleList(String id){

        //1 根据id查询用户
        User user = userService.findById(id);
        //2 根据企业id查询所有企业角色
        List<Role> roleList=roleService.findAllRole(companyId);
        //3 根据用户获取该用户所具备的角色
        List<Role> userRoleList=roleService.findByUserRole(user.getId());
        //4 将查询的用户角色的id进行字符串拼接
        String roleStr="";
        for (Role role : userRoleList) {
            roleStr+=role.getId()+",";
        }
        System.out.println(roleStr);
        request.setAttribute("user",user);
        request.setAttribute("roleList",roleList);
        request.setAttribute("userRoleStr",roleStr);
        //到查询
        return "system/user/user-role";
    }

    //更新用户角色

    @RequestMapping(value = "/changeRole",name = "用户分配角色页面展示")
    public String changeRole(String userid,String[] roleIds){

        userService.changeRole(userid,roleIds);
        //到查询
        return "redirect:/system/user/list.do";
    }
}
