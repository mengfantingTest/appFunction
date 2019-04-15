package com.uiautomator.babyfs;

import android.support.test.uiautomator.UiObjectNotFoundException;

import static com.uiautomator.babyfs.GenerateReport.caseName;
import static com.uiautomator.babyfs.KeyWord.assertId;
import static com.uiautomator.babyfs.KeyWord.assertText;
import static com.uiautomator.babyfs.KeyWord.assertTextNull;
import static com.uiautomator.babyfs.KeyWord.back;
import static com.uiautomator.babyfs.KeyWord.clickId;
import static com.uiautomator.babyfs.KeyWord.clickText;
import static com.uiautomator.babyfs.KeyWord.closeApp;
import static com.uiautomator.babyfs.KeyWord.inputId;
import static com.uiautomator.babyfs.KeyWord.judgment_id;
import static com.uiautomator.babyfs.KeyWord.judgment_text;
import static com.uiautomator.babyfs.KeyWord.monitorId;
import static com.uiautomator.babyfs.KeyWord.monitorText;
import static com.uiautomator.babyfs.KeyWord.openApp;
import static com.uiautomator.babyfs.KeyWord.removeMonitor;
import static com.uiautomator.babyfs.KeyWord.selectedId;
import static com.uiautomator.babyfs.KeyWord.selectedIdFalse;
import static com.uiautomator.babyfs.KeyWord.slideFindId;
import static com.uiautomator.babyfs.KeyWord.swipeDown;
import static java.lang.Thread.sleep;

class Case {

    //注册监听器
    static void Monitor() {
        monitorText("去授权", "去授权");
        monitorText("继续安装", "继续安装");
        monitorText("安装", "安装");
        monitorText("确认安装", "确认安装");
        monitorText("确定", "确定");
        monitorText("确认", "确认");
        monitorText("允许", "允许");
        monitorText("同意", "同意");
        monitorText("总是允许", "总是允许");
        monitorText("始终允许", "始终允许");
        monitorText("开启启蒙之旅", "开启启蒙之旅");
        monitorText("取消", "取消");
        monitorId("跳过", "cn.babyfs.android:id/tv_count_down");
    }

    //移除监听器
    static void logOutMonitor() {
        removeMonitor("去授权");
        removeMonitor("继续安装");
        removeMonitor("安装");
        removeMonitor("确认安装");
        removeMonitor("确定");
        removeMonitor("确认");
        removeMonitor("允许");
        removeMonitor("同意");
        removeMonitor("总是允许");
        removeMonitor("始终允许");
        removeMonitor("开启启蒙之旅");
        removeMonitor("取消");
    }

    //注册监听器2
    private static void Monitor2() {
        monitorText("确定", "确定");
        monitorText("确认", "确认");
        monitorText("允许", "允许");
        monitorText("同意", "同意");
        monitorText("总是允许", "总是允许");
        monitorText("始终允许", "始终允许");
    }

    //移除监听器2
    private static void logOutMonitor2() {
        removeMonitor("确定");
        removeMonitor("确认");
        removeMonitor("允许");
        removeMonitor("同意");
        removeMonitor("总是允许");
        removeMonitor("始终允许");
    }


    //onBoarding
    static void onBoarding() throws UiObjectNotFoundException {
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        if (judgment_text("6岁以上")) {
            caseName("onBoarding");
            clickText("6岁以上");
            assertId("cn.babyfs.android:id/ivHome");//前往首页
            clickId("cn.babyfs.android:id/ivHome");
            assertText("启蒙");
            closeApp("cn.babyfs.android");
        } else {
            closeApp("cn.babyfs.android");
        }

    }

    //手机号注册
    static void registered() throws UiObjectNotFoundException {
        caseName("手机号注册");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("登录/注册");
        clickText("使用手机号注册");
        inputId("cn.babyfs.android:id/bw_et_phone", Constants.registeredPhone);
        assertText("输入验证码");
        clickId("cn.babyfs.android:id/iv_close");
        closeApp("cn.babyfs.android");
    }

    //手机号+密码：登录
    static void login() throws UiObjectNotFoundException {
        caseName("手机号+密码：登录");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("登录/注册");
        clickText("登录");
        clickText("使用手机号码登录");
        clickText("密码登录");
        inputId("cn.babyfs.android:id/bw_et_phone", Constants.loginPhone);
        inputId("cn.babyfs.android:id/et_pwd", Constants.loginPassword);
        if (!judgment_id("cn.babyfs.android:id/login")) {
            back();
        }
        clickId("cn.babyfs.android:id/login");
        assertText("id1043337");
        closeApp("cn.babyfs.android");
    }

    //系统消息
    static void systemMessage() throws UiObjectNotFoundException {
        caseName("系统消息");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickId("cn.babyfs.android:id/iv_system_message");
        assertText("系统通知");
        closeApp("cn.babyfs.android");
    }

    //扫一扫
    static void ivScan() throws UiObjectNotFoundException {
        Monitor2();
        caseName("扫一扫");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickId("cn.babyfs.android:id/iv_scan");
        assertText("扫一扫");
        assertText("相册");
        assertText("手电筒");
        closeApp("cn.babyfs.android");
        logOutMonitor2();
    }

    //分享有礼
    static void profit() throws UiObjectNotFoundException {
        caseName("分享有礼");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("分享有礼");
        assertText("分享有礼");
        closeApp("cn.babyfs.android");
    }

    //课程续费
    static void courseRenew() throws UiObjectNotFoundException {
        caseName("课程续费");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("课程续费");
        assertText("精品班学员续课专属");
        closeApp("cn.babyfs.android");
    }

    //积分兑换
    static void shoppingMall() throws UiObjectNotFoundException {
        caseName("积分兑换");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("积分兑换");
        assertText("成长兔商城");
        closeApp("cn.babyfs.android");
    }


    //常见问题与意见反馈
    static void feedBack() throws UiObjectNotFoundException {
        caseName("常见问题与意见反馈");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("常见问题与意见反馈");
        assertText("常见问题");
        clickId("cn.babyfs.android:id/toolbar_tv_right");
        assertText("我的反馈");
        assertText("自动化测试，自动化测试");
        clickText("写反馈");
        assertText("提交反馈");
        assertText("功能异常");
        assertText("APP使用");
        assertText("优化建议");
        assertText("学习疑问");
        clickId("cn.babyfs.android:id/fd_diagnose");
        assertText("诊断工具");
        assertText("以下功能在成长兔英语团队指导下使用：");
        assertText("上传日志");
        assertText("上传网络诊断");
        back();
        inputId("cn.babyfs.android:id/fd_desc", "自动化测试，自动化测试");
        clickText("提交");
        assertText("我的反馈");
        swipeDown("10");
        assertText("自动化测试，自动化测试");
        closeApp("cn.babyfs.android");
    }

    //设置-账号与安全,内部调用
    private static void accountAndSecurity() throws UiObjectNotFoundException {
        clickText("我的");
        clickText("设置");
        clickText("账号与安全");
    }

    //设置-家长信息
    static void setParent() throws UiObjectNotFoundException {
        caseName("设置-家长信息");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        accountAndSecurity();
        clickText("家长信息");
        assertText("个人信息");
        assertText("用户姓名");
        assertText("id1043337");
        assertText("所在城市");
        assertText("北京市 东城区");
        clickText("地址管理");
        assertText("成长兔商城");
        back();
        clickText("保存");
        assertText("家长信息");
        closeApp("cn.babyfs.android");
    }

    //设置-宝宝信息
    static void setBaby() throws UiObjectNotFoundException {
        caseName("设置-宝宝信息");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        accountAndSecurity();
        clickText("宝宝信息");
        assertText("宝宝信息");
        assertText("让我们了解更多宝宝信息吧～");
        assertText("男宝");
        assertText("女宝");
        assertText("备孕");
        assertText("第 1 个宝宝");
        clickText("完成");
        assertText("宝宝信息");
        closeApp("cn.babyfs.android");
    }

    //设置-账户安全-绑定微信
    static void accountWeChat() throws UiObjectNotFoundException {
        caseName("设置-账户安全-绑定微信");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        accountAndSecurity();
        clickText("绑定微信");
        assertText("验证手机号");
        assertText("为了您的账号安全，请验证当前手机号");
        assertText("当前绑定手机号");
        assertText("199****3646");
        clickText("获取验证码");
        assertText("输入验证码");
        assertText("已发送：199****3646");
        clickId("cn.babyfs.android:id/iv_close");
        closeApp("cn.babyfs.android");
    }

    //设置-账户安全-绑定手机号
    static void accountPhone() throws UiObjectNotFoundException {
        caseName("设置-账户安全-绑定手机号");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        accountAndSecurity();
        clickText("绑定手机号");
        assertText("验证手机号");
        assertText("为了您的账号安全，请验证当前手机号");
        assertText("当前绑定手机号");
        assertText("199****3646");
        clickText("获取验证码");
        assertText("输入验证码");
        assertText("已发送：199****3646");
        clickId("cn.babyfs.android:id/iv_close");
        closeApp("cn.babyfs.android");
    }

    //设置-账户安全-修改密码
    static void accountPassword() throws UiObjectNotFoundException {
        caseName("设置-账户安全-修改密码");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        accountAndSecurity();
        clickText("修改密码");
        assertText("验证手机号");
        assertText("为了您的账号安全，请验证当前手机号");
        assertText("当前绑定手机号");
        assertText("199****3646");
        clickText("获取验证码");
        assertText("输入验证码");
        assertText("已发送：199****3646");
        clickId("cn.babyfs.android:id/iv_close");
        closeApp("cn.babyfs.android");
    }

    //设置-账户安全-设备管理
    static void accountDevices() throws UiObjectNotFoundException {
        caseName("设置-账户安全-设备管理");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        accountAndSecurity();
        clickText("设备管理");
        assertText("设备管理");
        assertText("您本月还剩");
        assertText("移除设备的权利，要珍惜呦～");
        assertText("当前设备");
        assertText("HMA-AL00");
        assertText("移除设备");
        assertText("还在使用设备");
        closeApp("cn.babyfs.android");
    }

    //设置-课程卡关联
    static void courseCard() throws UiObjectNotFoundException {
        caseName("设置-课程卡关联");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("设置");
        clickText("课程卡关联");
        assertText("课程卡关联");
        assertText("卡号");
        assertText("密码");
        assertText("使用如下图所示的实体卡关联在线课程");
        inputId("cn.babyfs.android:id/bw_et_code", "123456");
        inputId("cn.babyfs.android:id/bw_et_pwd", "a123456");
        clickText("关联");
        assertText("您输入的卡号有误");
        clickText("确认");
        closeApp("cn.babyfs.android");
    }

    //设置-收藏
    static void collectibles() throws UiObjectNotFoundException {
        caseName("设置-收藏");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("收藏");
        assertText("儿歌");
        assertText("单词");
        assertText("课程");
        assertText("动画片");
        assertText("文章");
        assertText("收藏");
        closeApp("cn.babyfs.android");
    }

    //设置-互动课录音自动开始
    static void tapeSwitch() throws UiObjectNotFoundException {
        caseName("设置-互动课录音自动开始");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("设置");
        assertText("互动课录音自动开始");
        slideFindId("cn.babyfs.android:id/iv_record_switch");
        selectedId("cn.babyfs.android:id/iv_record_switch");
        closeApp("cn.babyfs.android");
    }

    //设置-启动2G／3G／4G流量提醒
    static void flowSwitch() throws UiObjectNotFoundException {
        caseName("设置-启动2G／3G／4G流量提醒");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("设置");
        assertText("启动2G／3G／4G流量提醒");
        clickId("cn.babyfs.android:id/iv_flow_switch");
        assertText("2G/3G/4G网络下播放会消耗流量，请参考你的话费套餐情况进行设置。");
        clickText("确认");
        selectedIdFalse("cn.babyfs.android:id/iv_flow_switch");
        closeApp("cn.babyfs.android");
    }

    //设置-清除缓存
    static void cache() throws UiObjectNotFoundException {
        caseName("设置-清除缓存");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("设置");
        clickText("清除缓存");
        assertText("0bytes");
        closeApp("cn.babyfs.android");
    }

    //设置-检查更新
    static void updateCheck() throws UiObjectNotFoundException {
        caseName("设置-检查更新");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("设置");
        clickText("检查更新");
        if (judgment_text("应用权限申请")) {
            clickText("取消");
        } else {
            assertText("检查更新");
        }
        closeApp("cn.babyfs.android");
    }

    //设置-浏览器内核
    static void browserKernel() throws UiObjectNotFoundException {
        caseName("设置-浏览器内核");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("设置");
        clickText("浏览器内核");
        clickText("原生");
        assertText("原生");
        clickText("浏览器内核");
        clickText("企鹅");
        assertText("企鹅");
        closeApp("cn.babyfs.android");
    }

    //设置-鼓励一下
    static void encourage() throws UiObjectNotFoundException {
        caseName("设置-鼓励一下");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("设置");
        clickText("鼓励一下");
        assertText("书山有路勤为径，跪求鼓励行不行");
        clickText("残忍拒绝");
        clickText("鼓励一下");
        clickText("我要吐槽");
        assertText("提交反馈");
        clickId("cn.babyfs.android:id/fd_back");
        clickText("鼓励一下");
        clickText("鼓励下");
        assertTextNull("鼓励一下");
        back();
        closeApp("cn.babyfs.android");
    }

    //关于成长兔、诊断工具
    static void about() throws UiObjectNotFoundException, InterruptedException {
        caseName("关于成长兔英语");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("设置");
        clickText("关于成长兔英语");
        assertText("关于我们");
        assertText("轻松启蒙 玩转世界");
        assertText("《用户协议》");
        assertText("《隐私协议》");
        assertText("北京启萌教育科技 版权所有");
        caseName("诊断工具");
        clickId("cn.babyfs.android:id/toolbar_iv_right");
        assertId("cn.babyfs.android:id/bw_tv_version");
        assertText("诊断工具");
        assertText("以下功能在成长兔英语团队指导下使用：");
        assertText("上传日志");
        assertText("上传网络诊断");
        clickText("上传日志");
        assertText("上传中...");
        sleep(30000);
        assertText("日志上传成功");
        assertText("是否将信息分享到微信");
        assertText("算了");
        assertText("是的");
        closeApp("cn.babyfs.android");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("设置");
        clickText("关于成长兔英语");
        clickId("cn.babyfs.android:id/toolbar_iv_right");
        clickText("上传网络诊断");
        assertText("诊断中大约1分钟...");
        closeApp("cn.babyfs.android");
    }

    //学习_学习页面
    static void learningPage() throws UiObjectNotFoundException, InterruptedException {
        caseName("学习_学习页面");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("学习");
        sleep(4000);
        if (judgment_text("知道了")) {
            clickText("知道了");
            clickText("知道了");
            clickText("知道了");
            clickText("知道了");
        }
        assertId("cn.babyfs.android:id/course_level_title");
        assertId("cn.babyfs.android:id/item_courseset_unittab_unit");
        assertId("cn.babyfs.android:id/item_courseset_unittab_title");
        assertId("cn.babyfs.android:id/item_courseset_unit_title");
        assertId("cn.babyfs.android:id/ll_unit_progress");
        assertId("cn.babyfs.android:id/rl_lesson_layout");
        assertId("cn.babyfs.android:id/item_courseset_unit_title");
        assertId("cn.babyfs.android:id/item_courseset_unit_lesson");
        assertText("切换");
        assertText("合集");
        clickText("切换");
        assertText("Level 5-8 精品课");
        assertText("有效期至：2021-04-07");
        assertText("主课");
        closeApp("cn.babyfs.android");
    }

    //学习_课件
    static void courseWare() throws UiObjectNotFoundException, InterruptedException {
        caseName("学习_学习页面");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("学习");
        clickId("cn.babyfs.android:id/rl_lesson_layout");
        sleep(2000);
        if (judgment_text("知道了")) {
            clickText("知道了");
            clickText("知道了");
        }
        assertText("课件");
        assertText("互动");
        assertText("作业");
        assertText("全部播放");
        selectedId("cn.babyfs.android:id/iv_controller");
        clickId("cn.babyfs.android:id/cl_preview");
        sleep(6000);
        back();
        slideFindId("cn.babyfs.android:id/cl_audio_container");
        clickId("cn.babyfs.android:id/cl_audio_container");
        slideFindId("cn.babyfs.android:id/iv_video_image");
        clickId("cn.babyfs.android:id/iv_video_image");
        sleep(6000);
        back();
        closeApp("cn.babyfs.android");
    }


    //学习_去打卡
    static void punch() throws UiObjectNotFoundException, InterruptedException {
        caseName("学习_去打卡");
        monitorText("知道了", "知道了");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("学习");
        clickId("cn.babyfs.android:id/rl_lesson_layout");
        sleep(2000);
        clickId("cn.babyfs.android:id/iv_punch");
        assertText("打卡领胡萝卜");
        assertText("课件");
        assertText("互动");
        assertText("作业");
        assertText("领取规则");
        assertId("cn.babyfs.android:id/corse_clock_in_todo");
        clickText("领取规则");
        assertText("➊   学习后打卡分享可获得胡萝卜");
        assertText("学完1个环节    可获得2-3个胡萝卜");
        closeApp("cn.babyfs.android");
        removeMonitor("知道了");
    }

    //学习_课件更多功能
    static void menuMore() throws UiObjectNotFoundException, InterruptedException {
        caseName("学习_课件更多功能");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("学习");
        clickId("cn.babyfs.android:id/rl_lesson_layout");
        sleep(2000);
        clickId("cn.babyfs.android:id/menu_more");
        clickText("分享");
        assertText("微信好友");
        assertText("朋友圈");
        back();
        clickId("cn.babyfs.android:id/menu_more");
        selectedId("cn.babyfs.android:id/lesson_collect");
        clickText("向老师提问");
        assertText("提交反馈");
        assertText("功能异常");
        clickId("cn.babyfs.android:id/fd_back");
        clickId("cn.babyfs.android:id/menu_more");
        assertText("成长足迹");
        closeApp("cn.babyfs.android");
    }

    //学习_合集
    static void courseLevelSet() throws UiObjectNotFoundException {
        caseName("学习_合集");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("学习");
        clickId("cn.babyfs.android:id/course_level_set");
        assertText("课程合集");
        clickText("视频合集");
        assertText("视频合集");
        assertText("全部视频");
        assertText("课程视频");
        assertText("练习册视频");
        back();
        clickId("cn.babyfs.android:id/course_level_set");
        clickText("课程随心听");
        assertText("课程随心听");
        assertText("全部播放");
        assertText("自定义歌单");
        back();
        clickId("cn.babyfs.android:id/course_level_set");
        if (judgment_text("教具合集")) {
            clickText("教具合集");
            assertText("教具合集");
        }
        closeApp("cn.babyfs.android");
    }

    // 学习圈首页
    static void groupHome() throws UiObjectNotFoundException {
        caseName("学习圈首页");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("学习圈");
        assertText("学习圈");
        assertId("cn.babyfs.android:id/iv_right");
        assertId("cn.babyfs.android:id/ivCover");
        assertText("推荐");
        assertText("我的");
        assertId("cn.babyfs.android:id/tvTag");
        assertId("cn.babyfs.android:id/ivMore");
        assertId("cn.babyfs.android:id/tvContent");
        assertId("cn.babyfs.android:id/ivFace");
        assertId("cn.babyfs.android:id/tvUserName");
        assertId("cn.babyfs.android:id/tvBabyAge");
        assertId("cn.babyfs.android:id/flShare");
        assertId("cn.babyfs.android:id/flComment");
        assertId("cn.babyfs.android:id/llLike");
        closeApp("cn.babyfs.android");
    }


    // 学习圈_发布笔记
    static void groupSend() throws UiObjectNotFoundException {
        caseName("学习圈_发布笔记");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("学习圈");
        clickId("cn.babyfs.android:id/iv_right");
        inputId("cn.babyfs.android:id/note_text", "成长兔英语，成长兔英语");
        clickText("笔记主题");
        assertText("绘本");
        assertText("我娃我秀");
        clickText("学习笔记");
        clickText("发布");
        clickText("我的");
        swipeDown("10");
        swipeDown("10");
        swipeDown("10");
        assertText("成长兔英语，成长兔英语");
        clickId("cn.babyfs.android:id/flShare");
        assertText("微信");
        assertText("朋友圈");
        clickText("关闭");
        clickId("cn.babyfs.android:id/flComment");
        inputId("cn.babyfs.android:id/editText", "厉害");
        clickText("发布");
        assertText("厉害");
        clickId("cn.babyfs.android:id/ivMore");
        assertText("微信");
        assertText("朋友圈");
        assertText("删除");
        clickText("删除");
        clickText("删除");
        assertTextNull("成长兔英语，成长兔英语");
        closeApp("cn.babyfs.android");
    }


}
