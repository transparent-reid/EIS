package com.spm.eis.utils;

import com.spm.eis.data.AddressCode;
import com.spm.eis.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class Decoding {

    private HashMap<String, String> textInfo;

    @Autowired
    private AddressMapper addressMapper;

    private static Decoding decode;
    @PostConstruct
    public void init(){
        decode = this;
        decode.addressMapper = this.addressMapper;
    }

    public void decoding(String code){

//        code = "632626200206202105220204001010302001";

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

        AddressCode addressCode = decode.addressMapper.selectById(Long.parseLong(address));
        addressInfo = addressCode.getName().concat(addressInfo);
        while(addressCode.getPcode()!=0){
            addressCode = decode.addressMapper.selectById(addressCode.getPcode());
            addressInfo = addressCode.getName().concat(addressInfo);
        }



        String dateInfo = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8)
                +" "+date.substring(8,10)+":"+date.substring(10,12)+":"+date.substring(12);


        String disasterInfo = "";
        String disasterIndiInfo = "";
        String disasterIndi = disaster.substring(3);
        String disasterParent = disaster.substring(0,1);
        String disasterChildren = disaster.substring(1,3);

        switch (disasterParent){
            case "1":
                disasterInfo = disasterInfo.concat("??????-");
                if(disasterChildren.equals("01")) disasterInfo = disasterInfo.concat("????????????");
                else System.out.println("???????????????");

                switch (disasterIndi){
                    case "001":
                        disasterIndiInfo = disasterIndiInfo.concat("????????????");break;
                    case "002":
                        disasterIndiInfo = disasterIndiInfo.concat("??????");break;
                    case "003":
                        disasterIndiInfo = disasterIndiInfo.concat("??????");break;
                    case "004":
                        disasterIndiInfo = disasterIndiInfo.concat("????????????");break;
                    case "005":
                        disasterIndiInfo = disasterIndiInfo.concat("??????");break;
                    default:
                        System.out.println("???????????????");
                }
                break;
            case "2":
                disasterInfo = disasterInfo.concat("?????????????????????-");
                switch (disasterChildren){
                    case "01":
                        disasterInfo = disasterInfo.concat("??????");break;
                    case "02":
                        disasterInfo = disasterInfo.concat("??????");break;
                    case "03":
                        disasterInfo = disasterInfo.concat("??????");break;
                    default:
                        System.out.println("???????????????");
                }
                switch (disasterIndi){
                    case "001":
                        disasterIndiInfo = disasterIndiInfo.concat("????????????");break;
                    case "002":
                        disasterIndiInfo = disasterIndiInfo.concat("????????????");break;
                    default:
                        System.out.println("???????????????");
                }
                break;
            case "3":
                disasterInfo = disasterInfo.concat("????????????-");
                switch (disasterChildren){
                    case "01":
                        disasterInfo = disasterInfo.concat("??????");break;
                    case "02":
                        disasterInfo = disasterInfo.concat("??????");break;
                    case "03":
                        disasterInfo = disasterInfo.concat("??????");break;
                    case "04":
                        disasterInfo = disasterInfo.concat("??????");break;
                    case "05":
                        disasterInfo = disasterInfo.concat("??????");break;
                    default:
                        System.out.println("???????????????");
                }
                switch (disasterIndi){
                    case "001":
                        disasterIndiInfo = disasterIndiInfo.concat("??????????????????");break;
                    case "002":
                        disasterIndiInfo = disasterIndiInfo.concat("??????????????????");break;
                    case "003":
                        disasterIndiInfo = disasterIndiInfo.concat("????????????");break;
                    default:
                        System.out.println("???????????????");
                }
                break;
            case "4":
                disasterInfo = disasterInfo.concat("?????????????????????-");
                switch (disasterChildren){
                    case "01":
                        disasterInfo = disasterInfo.concat("??????");break;
                    case "02":
                        disasterInfo = disasterInfo.concat("??????");break;
                    case "03":
                        disasterInfo = disasterInfo.concat("??????");break;
                    case "04":
                        disasterInfo = disasterInfo.concat("??????");break;
                    case "05":
                        disasterInfo = disasterInfo.concat("??????");break;
                    case "06":
                        disasterInfo = disasterInfo.concat("??????");break;
                    case "07":
                        disasterInfo = disasterInfo.concat("??????");break;
                    default:
                        System.out.println("???????????????");
                }
                switch (disasterIndi){
                    case "001":
                        disasterIndiInfo = disasterIndiInfo.concat("???????????????");break;
                    case "002":
                        disasterIndiInfo = disasterIndiInfo.concat("????????????");break;
                    case "003":
                        disasterIndiInfo = disasterIndiInfo.concat("????????????");break;
                    default:
                        System.out.println("???????????????");
                }
                break;
            case "5":
                disasterInfo = disasterInfo.concat("????????????-");
                switch (disasterChildren){
                    case "01":
                        disasterInfo = disasterInfo.concat("??????");break;
                    case "02":
                        disasterInfo = disasterInfo.concat("??????");break;
                    case "03":
                        disasterInfo = disasterInfo.concat("?????????");break;
                    case "04":
                        disasterInfo = disasterInfo.concat("????????????");break;
                    case "05":
                        disasterInfo = disasterInfo.concat("?????????");break;
                    case "06":
                        disasterInfo = disasterInfo.concat("????????????");break;
                    case "07":
                        disasterInfo = disasterInfo.concat("??????");break;
                    default:
                        System.out.println("???????????????");
                }
                switch (disasterIndi){
                    case "001":
                        disasterIndiInfo = disasterIndiInfo.concat("????????????");break;
                    case "002":
                        disasterIndiInfo = disasterIndiInfo.concat("????????????");break;
                    case "003":
                        disasterIndiInfo = disasterIndiInfo.concat("????????????");break;
                    default:
                        System.out.println("???????????????");
                }
                break;
            default:
                System.out.println("???????????????");
        }


        switch (origin){
            case "100":
                textInfo.put("origin","???????????????????????????");break;
            case "101":
                textInfo.put("origin","???????????????????????????");break;
            case "120":
                textInfo.put("origin","????????????????????????");break;
            case "121":
                textInfo.put("origin","????????????????????????????????????");break;
            case "140":
                textInfo.put("origin","???????????????????????????");break;
            case "141":
                textInfo.put("origin","?????????????????????????????????");break;
            case "142":
                textInfo.put("origin","???????????????????????????????????????");break;
            case "180":
                textInfo.put("origin","????????????????????????????????????");break;
            case "181":
                textInfo.put("origin","???????????????????????????????????????????????????");break;
            case "199":
                textInfo.put("origin","????????????????????????");break;
            case "200":
                textInfo.put("origin","???????????????");break;
            case "201":
                textInfo.put("origin","???????????????");break;
            case "202":
                textInfo.put("origin","???????????????");break;
            case "203":
                textInfo.put("origin","??????????????????");break;
            case "204":
                textInfo.put("origin","??????????????????");break;
            case "205":
                textInfo.put("origin","????????????????????????");break;
            case "300":
                textInfo.put("origin","????????????");break;
            default:
                System.out.println("???????????????");
        }


        switch (type){
            case "0":
                textInfo.put("type","??????");break;
            case "1":
                textInfo.put("type","??????");break;
            case "2":
                textInfo.put("type","??????");break;
            case "3":
                textInfo.put("type","??????");break;
            case "4":
                textInfo.put("type","??????");break;
            default:
                System.out.println("???????????????");
        }

        textInfo.put("address",addressInfo);
        textInfo.put("date",dateInfo);
        textInfo.put("disaster",disasterInfo);
        textInfo.put("disasterIndi",disasterIndiInfo);
    }

    public HashMap<String, String> getTextInfo(){
        return textInfo;
    }

}
