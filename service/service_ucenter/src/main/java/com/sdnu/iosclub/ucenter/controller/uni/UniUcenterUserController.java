package com.sdnu.iosclub.ucenter.controller.uni;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.serviceutil.JwtUtils;
import com.sdnu.iosclub.serviceutil.MD5;
import com.sdnu.iosclub.serviceutil.R;
import com.sdnu.iosclub.ucenter.entity.UcenterStudent;
import com.sdnu.iosclub.ucenter.entity.UcenterTeacher;
import com.sdnu.iosclub.ucenter.entity.uni.LoginVo;
import com.sdnu.iosclub.ucenter.entity.vo.UserVo;
import com.sdnu.iosclub.ucenter.service.UcenterStudentService;
import com.sdnu.iosclub.ucenter.service.UcenterTeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: zyl
 * @Date: 2021/10/03/14:15
 * @Description:
 */
@RestController
@RequestMapping("/ucenter/uni/user")
public class UniUcenterUserController {

    @Autowired
    private UcenterStudentService studentService;

    @Autowired
    private UcenterTeacherService teacherService;


    @ApiOperation("通过账号密码登录，返回token字符串和用户基本信息")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo){

        String number = loginVo.getNumber();
        String password = loginVo.getPassword();

        //校验参数，前端已经判断过是否为空，这里可以不加判断，但最好加上
        if(StringUtils.isEmpty(number)){
            return R.error().message("请输入账号");
        }
        if(StringUtils.isEmpty(password)){
            return R.error().message("请输入密码");
        }

        //查询用户是否存在
        UcenterStudent student = studentService.getOne(new QueryWrapper<UcenterStudent>().eq("number", number));
        if(student==null){
            UcenterTeacher teacher = teacherService.getOne(new QueryWrapper<UcenterTeacher>().eq("number", number));
            if(teacher==null){
                return R.error().message("当前用户不存在");
            }else{
                //校验密码
                if(!MD5.encrypt(password).equals(teacher.getPassword())){
                    return R.error().message("密码错误");
                }
                //检查是否被禁用
                if(teacher.getDisabled()==1){
                    return R.error().message("当前用户已被禁用！");
                }

                //校验完毕，生成token字符串返回
                String token = JwtUtils.getJwtToken(teacher.getId(),teacher.getName());
                //获取用户的基本信息
                UserVo userVo = teacherService.getUserVo(teacher.getId());
                return R.ok().data("token",token).data("userVo",userVo);
            }
        }else{

            //同样的操作
            //校验密码
            if(!MD5.encrypt(password).equals(student.getPassword())){
                return R.error().message("密码错误");
            }
            //检查是否被禁用
            if(student.getDisabled()==1){
                return R.error().message("当前用户已被禁用！");
            }

            //校验完毕，生成token字符串返回
            String token = JwtUtils.getJwtToken(student.getId(),student.getName());
            UserVo userVo = studentService.getUserVo(student.getId());
            return R.ok().data("token",token).data("userVo",userVo);

        }


    }


    @ApiOperation("修改密码")
    @PostMapping("changePassword")
    public R changePassword(@RequestBody Map<String,String> params){
        String userId = params.get("userId");
        String newPassword = params.get("newPassword");

        if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(newPassword)){
            return R.error().message("参数不能为空");
        }

        UcenterStudent student = studentService.getById(userId);
        if(student!=null){
            String password = MD5.encrypt(newPassword);
            UcenterStudent ucenterStudent = new UcenterStudent();
            ucenterStudent.setId(userId)
                    .setPassword(password);
            studentService.updateById(ucenterStudent);
        }else{
            UcenterTeacher teacher = teacherService.getById(userId);
            if(teacher==null){
                return R.error().message("未查询到用户");
            }else{
                String password = MD5.encrypt(newPassword);
                UcenterTeacher ucenterTeacher = new UcenterTeacher();
                ucenterTeacher.setId(userId)
                        .setPassword(password);

                teacherService.updateById(ucenterTeacher);
            }
        }

        return R.ok();
    }
}
