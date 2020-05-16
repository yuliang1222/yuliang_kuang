/**
 * ClassName:HelloController
 * Author:机械革命
 * Date:2019/3/149:32
 * Description:TODO
 */
package com.example.demo.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.datasource.annotation.FangshaAno;
import com.example.demo.itheima.Strategy.StrategyContext;
import com.example.demo.itheima.observer.OrderObservable;
import com.example.demo.pojo.User;
import com.example.demo.servcice.UserService;
import com.example.demo.utils.JsonUtils;
import com.example.demo.utils.Result;
import com.example.demo.web.hashmap.MenuDO;
import com.example.demo.web.pageHelper.ExcelTool;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.hibernate.validator.constraints.Length;
import org.junit.rules.Stopwatch;
import org.redisson.api.RMapCache;
import org.redisson.api.RSetCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yuliang
 * @Date: 2019/3/14 9:32
 */
@RestController
@Slf4j
public class HelloController {
//    @Autowired
//    private DataSource dataSource;
@Autowired
private UserService userService;
    private  static  final  String folder = "E:\\AllCode\\springbootdemo\\src\\main\\java\\com\\example\\demo\\web";

    @Autowired
    private RedissonClient redissonClient;

    @PostMapping("/fang")
//    @FangshaAno(key = "中",cout = "10"  )
    public String  fang(HttpServletResponse response, String key) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("参数传递"+key);
       weixin.pdf2png2("D:\\Desktop\\txt", "text", "png", response);
       stopWatch.stop();
        System.out.println("总时间，"+stopWatch.getTime());
        return "suc";
    }
    @GetMapping("/fangqq")
//    @FangshaAno(key = "中",cout = "10"  )
    public String  fangqq(HttpServletResponse response) throws IOException {
        File file = new File("D:\\Desktop\\sad"+".png");
        String png_base64 = null;
      InputStream fileInputStream = new FileInputStream(file);

        response.setContentType("image/jpg");

                response.setHeader("content-disposition", "attachment;filename=" + new String("sadf.png"));
        ServletOutputStream outputStream = response.getOutputStream();
        int len = 0;
        byte[] by = new byte[1024*10];
        while ((len = fileInputStream.read(by))!=-1) {
            outputStream.write(by, 0, len);
            outputStream.flush();
        }
        fileInputStream.close();
        return null;


    }
    @ApiOperation(value = "用户查询服务")
    @GetMapping("/search")
    public List<User> hello() {
        log.info("查询启动");
        List<User> user = this.userService.queryById();
        return user;
    }
    @GetMapping("/searchM")
    public Result<List<User>> hello1() {
        log.info("查询启动");
        List<User> user = this.userService.queryById2();
        return Result.success(user);
    }
    @GetMapping("/searchM3")
    public Result hello2() {
        log.info("查询启动");
        List<User> user = this.userService.queryById3();
        return Result.success(user);
    }
    @PostMapping("/insertuser")
    public Result insertuser(@RequestBody @Valid RequestData mobilephone) {
        System.out.println(mobilephone);
//         Mobilephone data = mobilephone.getData();
//        String mobileNo =
//                data.getMobileNo();
//        userService.insertuser();
        return Result.success();
    }


    @Autowired
    private StrategyContext strategyContext;

    @GetMapping("calculatePrice")
    public BigDecimal calculatePrice(String memberLevel) {
        return strategyContext.createMember(memberLevel).calculatePrice();
    }


    @GetMapping("poi")
    public void test( HttpServletResponse response) throws IOException {

            String title = "abc";
            List<List<Object>> rowList = new ArrayList<List<Object>>();
            // 表头
            List<Object> head = new ArrayList<Object>();
            head.add("头像");
            head.add("姓名");
            head.add("年龄");
            head.add("备注");
            head.add("空间");
            rowList.add(head);
            // 表数据
            List<Object> row = new ArrayList<Object>();
        row.add("孙悟空");
        row.add("18");
        row.add("唐僧大徒弟");
        row.add("https://www.baidu.com");
            try {
                row.add(new URL("https://cdn-img.easyicon.net/png/11002/1100254.gif"));
                row.add(new URL("https://cdn-img.easyicon.net/png/11002/1100254.gif"));
                row.add(new URL("https://cdn-img.easyicon.net/png/11002/1100254.gif"));
            } catch (Exception e) {}

            rowList.add(row);
            List<Object> row2 = new ArrayList<Object>();
            try {
                row2.add(new URL("https://cdn-img.easyicon.net/png/5467/546720.gif"));
                row2.add(new URL("https://cdn-img.easyicon.net/png/5467/546720.gif"));
                row2.add(new URL("https://cdn-img.easyicon.net/png/5467/546720.gif"));
            } catch (Exception e) {}
            row2.add("猪八戒");
            row2.add("18");
            row2.add("唐僧儿徒弟");
            row2.add("https://www.tencent.com");
            rowList.add(row2);
            ExcelTool.export1(response,title, rowList);
            System.out.println("导出完成，请刷新工程，查看文件....");


    }
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("test")
    public String test() {
        OrderObservable order = new OrderObservable(this, "用户下单成功");
        applicationContext.publishEvent(order);
        return "success";
    }

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://d11.baidupcs.com/file/90aac3ed703b5b48e6d0598a58fc5e4d?bkt=en-00f3aa810d089f20956d7600823319ebc6bdc3f45fa4a307d13dfc81d307c18a426fe0c6be58250bca6e2a53493572e878e99d623550ad62456c7b54ee497a96&xcode=b26998aeb2e66acc3f2bc914e473b8b0d81850bd39c79b7d968ce728c4077b6c77be38ee2201b965afdc105f45d38071352405aaf620c402&fid=1905611309-250528-4692695377355&time=1577355910&sign=FDTAXGERLQBHSKf-DCb740ccc5511e5e8fedcff06b081203-PWYuC0S8aIpY9L0elXqkThrAhzg%3D&to=d11&size=820051&sta_dx=820051&sta_cs=6682&sta_ft=pdf&sta_ct=6&sta_mt=5&fm2=MH%2CYangquan%2CAnywhere%2C%2Cshanghai%2Cct&ctime=1556125842&mtime=1568698390&resv0=cdnback&resv1=0&resv2=rlim&resv3=5&resv4=820051&vuk=1905611309&iv=0&htype=&randtype=&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=en-a62581867b04be429653491255eb14b00963fb0f9c8ba6ec16243a57c4ec2408c44542674ea2ed446f453e0890c2c3f4d69d28166bee2b4d305a5e1275657320&sl=68616270&expires=8h&rt=sh&r=285101262&vbdid=2903686122&fin=vuejs.pdf&fn=vuejs.pdf&rtype=1&dp-logid=8344023449646964939&dp-callid=0.1&hps=1&tsl=200&csl=200&csign=5hLZ6L3bCjREVmfk3lBWOm%2Brz3Q%3D&so=0&ut=6&uter=4&serv=0&uc=569815613&ti=b788fa7610674275d7f7d99571a5dff14881e9a101073ff3&reqlabel=250528_f_3d341bcc03bf6c421c60ac31e92c4bc4_-1_05dee8b7678ff8e54690713f73762ec1&by=themis");
        URL url1l = new URL("https://dss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1343015815,2335192405&fm=26&gp=0.png");
        InputStream inputStream = url1l.openStream();
        String fileHeader = CheckFileType.getFileHeader(inputStream);
        System.out.println(inputStream);
    }



}
