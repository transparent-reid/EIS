package com.spm.eis.utils;

import com.spm.eis.data.AddressCode;
import com.spm.eis.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.HashMap;

public class Decoding {
    private String code;
    private HashMap<String, String> textInfo;

    @Autowired
    private AddressMapper addressMapper;


    public void decoding(String code){
        this.code = code;
        code = "632626200206202105220204001010302001";
        textInfo = new HashMap<>();
        textInfo.put("address",null);
        textInfo.put("date",null);
        textInfo.put("origin", null);
        textInfo.put("type",null);
        textInfo.put("disaster",null);
        textInfo.put("disasterIndi",null);

        String address = code.substring(0,12);
        String date = code.substring(12,26);
        String origin = code.substring(26,29);
        String type = code.substring(29,30);
        String disaster = code.substring(30);


        String addressInfo = "";
        AddressCode addressCode = addressMapper.selectById(Long.parseLong(address));
        addressInfo = addressInfo.concat(addressCode.getName());
        while(addressCode.getPcode()!=0){
            addressCode = addressMapper.selectById(addressCode.getPcode());
            addressInfo = addressInfo.concat(addressCode.getName());
        }
        textInfo.put("address",addressInfo);


        String dateInfo = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8)
                +" "+date.substring(8,10)+":"+date.substring(10,12)+":"+date.substring(12);
        textInfo.put("date",dateInfo);

        String disasterInfo = "";
        String disasterIndiInfo = "";
        String disasterIndi = disaster.substring(3);
        String disasterParent = disaster.substring(0,1);
        String disasterChildren = disaster.substring(1,3);

        switch (disasterParent){
            case "1":
                disasterInfo = disasterInfo.concat("震情-");
                if(disasterChildren.equals("01")) disasterInfo = disasterInfo.concat("震情信息");
                else System.out.println("灾情码错误");

                switch (disasterIndi){
                    case "001":
                        disasterIndiInfo = disasterIndiInfo.concat("地理位置");break;
                    case "002":
                        disasterIndiInfo = disasterIndiInfo.concat("时间");break;
                    case "003":
                        disasterIndiInfo = disasterIndiInfo.concat("震级");break;
                    case "004":
                        disasterIndiInfo = disasterIndiInfo.concat("震源深度");break;
                    case "005":
                        disasterIndiInfo = disasterIndiInfo.concat("烈度");break;
                    default:
                        System.out.println("灾情码错误");
                }
                break;
            case "2":
                disasterInfo = disasterInfo.concat("人员伤亡及失踪-");
                switch (disasterChildren){
                    case "01":
                        disasterInfo = disasterInfo.concat("死亡");break;
                    case "02":
                        disasterInfo = disasterInfo.concat("受伤");break;
                    case "03":
                        disasterInfo = disasterInfo.concat("失踪");break;
                    default:
                        System.out.println("灾情码错误");
                }
                switch (disasterIndi){
                    case "001":
                        disasterIndiInfo = disasterIndiInfo.concat("受灾人数");break;
                    case "002":
                        disasterIndiInfo = disasterIndiInfo.concat("受灾程度");break;
                    default:
                        System.out.println("灾情码错误");
                }
                break;
            case "3":
                disasterInfo = disasterInfo.concat("房屋破坏-");
                switch (disasterChildren){
                    case "01":
                        disasterInfo = disasterInfo.concat("土木");break;
                    case "02":
                        disasterInfo = disasterInfo.concat("砖木");break;
                    case "03":
                        disasterInfo = disasterInfo.concat("砖混");break;
                    case "04":
                        disasterInfo = disasterInfo.concat("框架");break;
                    case "05":
                        disasterInfo = disasterInfo.concat("其他");break;
                    default:
                        System.out.println("灾情码错误");
                }
                switch (disasterIndi){
                    case "001":
                        disasterIndiInfo = disasterIndiInfo.concat("一般损坏面积");break;
                    case "002":
                        disasterIndiInfo = disasterIndiInfo.concat("严重损坏面积");break;
                    case "003":
                        disasterIndiInfo = disasterIndiInfo.concat("受灾程度");break;
                    default:
                        System.out.println("灾情码错误");
                }
                break;
            case "4":
                disasterInfo = disasterInfo.concat("生命线工程灾情-");
                switch (disasterChildren){
                    case "01":
                        disasterInfo = disasterInfo.concat("交通");break;
                    case "02":
                        disasterInfo = disasterInfo.concat("供水");break;
                    case "03":
                        disasterInfo = disasterInfo.concat("输油");break;
                    case "04":
                        disasterInfo = disasterInfo.concat("燃气");break;
                    case "05":
                        disasterInfo = disasterInfo.concat("电力");break;
                    case "06":
                        disasterInfo = disasterInfo.concat("通信");break;
                    case "07":
                        disasterInfo = disasterInfo.concat("水利");break;
                    default:
                        System.out.println("灾情码错误");
                }
                switch (disasterIndi){
                    case "001":
                        disasterIndiInfo = disasterIndiInfo.concat("受灾施救数");break;
                    case "002":
                        disasterIndiInfo = disasterIndiInfo.concat("受灾范围");break;
                    case "003":
                        disasterIndiInfo = disasterIndiInfo.concat("受灾程度");break;
                    default:
                        System.out.println("灾情码错误");
                }
                break;
            case "5":
                disasterInfo = disasterInfo.concat("次生灾害-");
                switch (disasterChildren){
                    case "01":
                        disasterInfo = disasterInfo.concat("崩塌");break;
                    case "02":
                        disasterInfo = disasterInfo.concat("滑坡");break;
                    case "03":
                        disasterInfo = disasterInfo.concat("泥石流");break;
                    case "04":
                        disasterInfo = disasterInfo.concat("岩溶塌陷");break;
                    case "05":
                        disasterInfo = disasterInfo.concat("地裂缝");break;
                    case "06":
                        disasterInfo = disasterInfo.concat("地面沉降");break;
                    case "07":
                        disasterInfo = disasterInfo.concat("其他");break;
                    default:
                        System.out.println("灾情码错误");
                }
                switch (disasterIndi){
                    case "001":
                        disasterIndiInfo = disasterIndiInfo.concat("灾害损失");break;
                    case "002":
                        disasterIndiInfo = disasterIndiInfo.concat("灾害范围");break;
                    case "003":
                        disasterIndiInfo = disasterIndiInfo.concat("受灾程度");break;
                    default:
                        System.out.println("灾情码错误");
                }
                break;
            default:
                System.out.println("灾情码错误");
        }


        switch (origin){
            case "100":
                textInfo.put("origin","前方地震应急指挥部");break;
            case "101":
                textInfo.put("origin","后方地震应急指挥部");break;
            case "120":
                textInfo.put("origin","应急指挥技术系统");break;
            case "121":
                textInfo.put("origin","社会服务工程应急救援系统");break;
            case "140":
                textInfo.put("origin","危险区预评估工作组");break;
            case "141":
                textInfo.put("origin","地震应急指挥技术协调组");break;
            case "142":
                textInfo.put("origin","震后政府信息支持工作项目组");break;
            case "180":
                textInfo.put("origin","灾情快速上报接收处理系统");break;
            case "181":
                textInfo.put("origin","地方地震局应急信息服务相关技术系统");break;
            case "199":
                textInfo.put("origin","其他业务报送数据");break;
            case "200":
                textInfo.put("origin","互联网感知");break;
            case "201":
                textInfo.put("origin","通信网感知");break;
            case "202":
                textInfo.put("origin","舆情网感知");break;
            case "203":
                textInfo.put("origin","电力系统感知");break;
            case "204":
                textInfo.put("origin","交通系统感知");break;
            case "205":
                textInfo.put("origin","其他泛在感知数据");break;
            case "300":
                textInfo.put("origin","其他数据");break;
            default:
                System.out.println("来源码错误");
        }


        switch (type){
            case "0":
                textInfo.put("type","文字");break;
            case "1":
                textInfo.put("type","图像");break;
            case "2":
                textInfo.put("type","音频");break;
            case "3":
                textInfo.put("type","视频");break;
            case "4":
                textInfo.put("type","其他");break;
            default:
                System.out.println("载体码错误");
        }

        textInfo.put("disaster",disasterInfo);
        textInfo.put("disasterIndi",disasterIndiInfo);
        System.out.println(address+" "+date+" "+origin+" "+type+" "+disaster);
        System.out.println("日期:    "+textInfo.get("date"));
        System.out.println("来源:    "+textInfo.get("origin"));
        System.out.println("载体:    "+textInfo.get("type"));
        System.out.println("灾情信息: "+textInfo.get("disaster"));
        System.out.println("灾情指标: "+textInfo.get("disasterIndi"));
    }

    public HashMap<String, String> getTextInfo(){
        return textInfo;
    }

}
