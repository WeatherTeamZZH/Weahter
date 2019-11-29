package com.ok100.weather.bean;

import java.io.Serializable;

/**
 * @author: gh
 * @description:
 * @date: 2019-11-29.
 * @from:
 */
public class XingzuoBean implements Serializable {

    //{"name":"双鱼座","weekth":48,"date":"2019年11月24日-2019年11月30日","health":"","job":null,"love":"恋爱：本周你的桃花还是挺多的，自身还是比较有魅力的，但是你很容易让别人觉得不靠谱。 ","money":"财运：本周你会比较丢三落四哦，没头没脑的。 ","work":"工作：本周你的工作不怎么给力，特别是团队合作方面，你有可能会拖后腿。 ","resultcode":"200","error_code":0}

    public class Day implements Serializable {


        /**
         * name : 狮子座
         * datetime : 2014年06月27日
         * date : 20140627
         * all : 89%
         * color : 古铜色
         * health : 95%
         * love : 80%
         * money : 84%
         * number : 8
         * QFriend : 处女座
         * summary : 有些思考的小漩涡，可能让你忽然的放空，生活中许多的细节让你感触良多，五味杂陈， 常常有时候就慢动作定格，想法在某处冻结停留，陷入一阵自我对话的沉思之中，这个时候你不喜欢被打扰 或询问，也不想让某些想法曝光，个性变得有些隐晦。
         * work : 80%
         * error_code : 0
         */

        private String name;
        private String datetime;
        private String date;
        private String all;
        private String color;
        private String health;
        private String love;
        private String money;
        private String number;
        private String QFriend;
        private String summary;
        private String work;
        private int error_code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getHealth() {
            return health;
        }

        public void setHealth(String health) {
            this.health = health;
        }

        public String getLove() {
            return love;
        }

        public void setLove(String love) {
            this.love = love;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getQFriend() {
            return QFriend;
        }

        public void setQFriend(String QFriend) {
            this.QFriend = QFriend;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }

    }

    public class Week implements Serializable{


        /**
         * name : 白羊座
         * date : 2014年06月29日-2014年07月05日
         * weekth : 27
         * health : 健康：内心有焦躁，但身体拒绝过劳求舒适。容易有胃部不适。
         * job : 求职：虽有新想法，但心态求稳当，容易低就。但较有可能从家人处获得的机会。
         * love : 恋情：之前积累的想法和感受，本周选择说出来。沟通机会增多，亦有可能以争吵的方式出现。 单身的，在聚会闲谈中可望获得更多缘分。
         * money : 财运：虽有自己的理财想法，但总体受控于家人或家族的财务计划。受木星支撑，有机会得到 家人的支援。但是土逆仍然显示你有债务加大的风险。置业房产出现时机，较大可能是家人出首期，你来月 供。
         * work : 工作：水逆在本周结束，之前耽误、错过的出现弥补机会。职场进入休整状态，有调部门或岗位 的可能。
         * resultcode : 200
         * error_code : 0
         */

        private String name;
        private String date;
        private int weekth;
        private String health;
        private String job;
        private String love;
        private String money;
        private String work;
        private String resultcode;
        private int error_code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getWeekth() {
            return weekth;
        }

        public void setWeekth(int weekth) {
            this.weekth = weekth;
        }

        public String getHealth() {
            return health;
        }

        public void setHealth(String health) {
            this.health = health;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getLove() {
            return love;
        }

        public void setLove(String love) {
            this.love = love;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public String getResultcode() {
            return resultcode;
        }

        public void setResultcode(String resultcode) {
            this.resultcode = resultcode;
        }

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }
    }


    public class Month implements Serializable{


        /**
         * date : 2016年12月
         * name : 白羊座
         * all : 本月运势有两个重要的节点，一个是在上旬，水星进入事业宫，更加关注事业发展，目标性加 强；而金星随之离开事业宫，原先的经验不能再为你赢得加分，反而是人脉上。。。
         * happyMagic :
         * health : 上旬和中旬，运动能量高，适合开展锻炼计划，尤其是练习耐力的运动。下旬，水逆开启，出行要小心意外了。
         * love : 现实的比较太累，你更喜欢朋友式的轻松相处，如果和爱人之间做不到，你会更眷恋友人的陪 伴。因而本月“友情已达，恋人未满”的状况，会有更大的发生几率。
         * money : 人际生财，多往人气旺的地方是有利打听到财富资讯，广开财路的。虽然人际开销也会增多 ，但可以当做是投资。
         * month : 12
         * work : 本月的目标性和计划性都很强，两个阶段的区别在于行动力。上旬和中旬，行动力分散，下 旬，行动力足够，但受水逆影响，意外多。
         * resultcode : 200
         * error_code : 0
         */

        private String date;
        private String name;
        private String all;
        private String happyMagic;
        private String health;
        private String love;
        private String money;
        private int month;
        private String work;
        private String resultcode;
        private int error_code;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }

        public String getHappyMagic() {
            return happyMagic;
        }

        public void setHappyMagic(String happyMagic) {
            this.happyMagic = happyMagic;
        }

        public String getHealth() {
            return health;
        }

        public void setHealth(String health) {
            this.health = health;
        }

        public String getLove() {
            return love;
        }

        public void setLove(String love) {
            this.love = love;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public String getResultcode() {
            return resultcode;
        }

        public void setResultcode(String resultcode) {
            this.resultcode = resultcode;
        }

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }
    }

}
