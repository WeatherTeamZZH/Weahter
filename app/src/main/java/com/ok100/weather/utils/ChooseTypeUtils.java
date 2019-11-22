package com.ok100.weather.utils;

import com.ok100.weather.R;

/**
 * @Description: This is ChooseTypeUtils
 * @Author: QianDongDong
 * @Time: 2019/11/22 19:47
 * @Email: qdd2977@sina.com
 * @org: www.vanlian.cn 万科链家（北京）装饰有限公司
 */
public class ChooseTypeUtils {
    public static String getNewType(String type){
        String string = "";
        if(type.equals("社会")){
            string = "shehui";
        }else if(type.equals("国内")){
            string = "guonei";
        }else if(type.equals("国际")){
            string = "guoji";
        } else if(type.equals("娱乐")){
            string = "yule";
        } else if(type.equals("体育")){
            string = "tiyu";
        } else if(type.equals("军事")){
            string = "junshi";
        }else if(type.equals("科技")){
            string = "keji";
        }else if(type.equals("财经")){
            string = "caijing";
        }else if(type.equals("时尚")){
            string = "shishang";
        }

        return string;
    }

    public static int getWeatherImgge(String type){
        int string  = R.mipmap.icon_qing;
        if(type.contains("晴")){
            string = R.mipmap.icon_qing;
        }else if(type.contains("云")){
            string = R.mipmap.icon_duoyun;
        }else if(type.contains("霾")){
            string = R.mipmap.icon_wu;
        } else if(type.contains("雨")){
            string = R.mipmap.icon_xiaoxue;
        } else if(type.contains("雪")){
            string = R.mipmap.icon_xiaoxue;
        } else if(type.contains("雾")){
            string = R.mipmap.icon_wu;
        }else if(type.contains("阴")){
            string = R.mipmap.icon_yin;
        }else if(type.contains("晴")){
            string = R.mipmap.icon_qing;
        }else if(type.contains("晴")){
            string = R.mipmap.icon_qing;
        }
        return string;
    }
}
