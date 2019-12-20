package com.puyue.www.qiaoge.model;

import java.util.List;

/**
 * Created by ${王文博} on 2019/8/8
 */
public class CityChangeModel {


    /**
     * code : 1
     * message : 成功
     * data : [{"provinceName":"北京市","cityNames":[{"cityName":"北京市","areaNames":["东城区","西城区","朝阳区","丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","怀柔区","平谷区","密云县","延庆县"]}]},{"provinceName":"天津市","cityNames":[{"cityName":"天津市","areaNames":["和平区","河东区","河西区","南开区","河北区","红桥区","东丽区","西青区","津南区","北辰区","武清区","宝坻区","滨海新区","宁河县","静海县","蓟县"]}]},{"provinceName":"河北省","cityNames":[{"cityName":"唐山市","areaNames":["路南区","路北区","古冶区","开平区","丰南区","丰润区","滦县","滦南县","乐亭县","迁西县","玉田县","曹妃甸区","遵化市","迁安市"]},{"cityName":"石家庄市","areaNames":["长安区","桥东区","桥西区","新华区","井陉矿区","裕华区","井陉县","正定县","栾城县","行唐县","灵寿县","高邑县","深泽县","赞皇县","无极县","平山县","元氏县","赵县","辛集市","藁城市","晋州市","新乐市","鹿泉市"]}]},{"provinceName":"山西省","cityNames":[{"cityName":"大同市","areaNames":["城区","矿区","南郊区","新荣区","阳高县","天镇县","广灵县","灵丘县","浑源县","左云县","大同县"]}]},{"provinceName":"辽宁省","cityNames":[{"cityName":"沈阳市","areaNames":["和平区","沈河区","大东区","皇姑区","铁西区","苏家屯区","东陵区","于洪区","辽中县","康平县","法库县","新民市","沈北新区"]}]},{"provinceName":"浙江省","cityNames":[{"cityName":"宁波市","areaNames":["海曙区","江东区","江北区","北仑区","镇海区","鄞州区","象山县","宁海县","余姚市","慈溪市","奉化市"]},{"cityName":"温州市","areaNames":["鹿城区","龙湾区","瓯海区","洞头县","永嘉县","平阳县","苍南县","文成县","泰顺县","瑞安市","乐清市"]},{"cityName":"嘉兴市","areaNames":["南湖区","秀洲区","嘉善县","海盐县","海宁市","平湖市","桐乡市"]},{"cityName":"丽水市","areaNames":["莲都区","青田县","缙云县","遂昌县","松阳县","云和县","庆元县","景宁畲族自治县","龙泉市"]},{"cityName":"台州市","areaNames":["椒江区","黄岩区","路桥区","玉环县","三门县","天台县","仙居县","温岭市","临海市"]},{"cityName":"杭州市","areaNames":["上城区","下城区","江干区","拱墅区","西湖区","滨江区","萧山区","余杭区","桐庐县","淳安县","建德市","富阳市","临安市"]}]},{"provinceName":"广东省","cityNames":[{"cityName":"深圳市","areaNames":["罗湖区","福田区","南山区","宝安区","龙岗区","盐田区","光明新区","坪山新区","大鹏新区","龙华新区"]}]},{"provinceName":"四川省","cityNames":[{"cityName":"南充市","areaNames":["顺庆区","高坪区","嘉陵区","南部县","营山县","蓬安县","仪陇县","西充县","阆中市"]}]},{"provinceName":"陕西省","cityNames":[{"cityName":"西安市","areaNames":["高陵县","新城区","碑林区"]}]}]
     * error : false
     * success : true
     */

    private int code;
    private String message;
    private boolean error;
    private boolean success;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * provinceName : 北京市
         * cityNames : [{"cityName":"北京市","areaNames":["东城区","西城区","朝阳区","丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","怀柔区","平谷区","密云县","延庆县"]}]
         */

        private String provinceName;
        private List<CityNamesBean> cityNames;

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public List<CityNamesBean> getCityNames() {
            return cityNames;
        }

        public void setCityNames(List<CityNamesBean> cityNames) {
            this.cityNames = cityNames;
        }

        public static class CityNamesBean {
            /**
             * cityName : 北京市
             * areaNames : ["东城区","西城区","朝阳区","丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","怀柔区","平谷区","密云县","延庆县"]
             */

            private String cityName;
            private List<String> areaNames;

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public List<String> getAreaNames() {
                return areaNames;
            }

            public void setAreaNames(List<String> areaNames) {
                this.areaNames = areaNames;
            }
        }
    }
}
