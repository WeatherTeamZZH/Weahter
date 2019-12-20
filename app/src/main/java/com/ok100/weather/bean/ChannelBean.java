package com.ok100.weather.bean;

import com.qq.e.ads.nativ.NativeExpressADView;

import java.util.List;

/**
 * @Description: This is DefultBean
 * @Author: ZHANGZH
 * @Time: 2019/11/29 13:53
 * @Email: qq.com
 * @org: OK100
 */
public class ChannelBean {


    /**
     * code : 200
     * message : success
     * status : 1
     * data : [{"id":"52","catid":"25","title":"柳传志要退休了？其名下有36家公司 女儿加盟滴滴","thumb":"http://www.yovxru.top/uploadfile/2019/1217/20191217113352300.jpeg","description":"（原标题：柳传志要退休?名下36家公司，女儿加盟滴滴，父亲曾发力香港）作者丨市界 冯晨晨柳传志终于要退休了？据晚点LatePost12月16日报   ","inputtime":"1576553507","style_type":"3361","image_type":[{"url":"http://www.yovxru.top/uploadfile/2019/1217/20191217113326438.jpeg","alt":"2019_12_17_285d00253b314c2089587b25005c1cf6"}],"url":"http://store.yovxru.top/Channels/aulist/id/52.html"}]
     * date : 2019-12-17 16:02:18
     */

    private String code;
    private String message;
    private int status;
    private String date;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 52
         * catid : 25
         * title : 柳传志要退休了？其名下有36家公司 女儿加盟滴滴
         * thumb : http://www.yovxru.top/uploadfile/2019/1217/20191217113352300.jpeg
         * description : （原标题：柳传志要退休?名下36家公司，女儿加盟滴滴，父亲曾发力香港）作者丨市界 冯晨晨柳传志终于要退休了？据晚点LatePost12月16日报
         * inputtime : 1576553507
         * style_type : 3361
         * image_type : [{"url":"http://www.yovxru.top/uploadfile/2019/1217/20191217113326438.jpeg","alt":"2019_12_17_285d00253b314c2089587b25005c1cf6"}]
         * url : http://store.yovxru.top/Channels/aulist/id/52.html
         */

        private String id;
        private String catid;
        private String title;
        private String thumb;
        private String description;
        private String inputtime;
        private String copyfrom;
        private String style_type;
        private String url;
        private List<ImageTypeBean> image_type;
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCopyfrom() {
            return copyfrom;
        }

        public void setCopyfrom(String copyfrom) {
            this.copyfrom = copyfrom;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getInputtime() {
            return inputtime;
        }

        public void setInputtime(String inputtime) {
            this.inputtime = inputtime;
        }

        public String getStyle_type() {
            return style_type;
        }

        public void setStyle_type(String style_type) {
            this.style_type = style_type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<ImageTypeBean> getImage_type() {
            return image_type;
        }

        public void setImage_type(List<ImageTypeBean> image_type) {
            this.image_type = image_type;
        }

        public static class ImageTypeBean {
            /**
             * url : http://www.yovxru.top/uploadfile/2019/1217/20191217113326438.jpeg
             * alt : 2019_12_17_285d00253b314c2089587b25005c1cf6
             */

            private String url;
            private String alt;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }
        }
    }
}
