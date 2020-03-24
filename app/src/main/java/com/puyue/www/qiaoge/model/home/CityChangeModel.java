package com.puyue.www.qiaoge.model.home;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

/**
 * Created by ${王文博} on 2019/8/8
 */
public class CityChangeModel extends SectionEntity<CityChangeModel.DataBean>{


    /**
     * code : 1
     * message : 成功
     * data : [{"provinceName":"北京市","provinceCode":"110100","cityNames":[{"cityName":"北京市","cityCode":"110110","areaNames":[{"areaName":"东城区","areaCode":"110101"},{"areaName":"西城区","areaCode":"110102"},{"areaName":"朝阳区","areaCode":"110105"},{"areaName":"丰台区","areaCode":"110106"},{"areaName":"石景山区","areaCode":"110107"},{"areaName":"海淀区","areaCode":"110108"},{"areaName":"门头沟区","areaCode":"110109"},{"areaName":"房山区","areaCode":"110111"},{"areaName":"通州区","areaCode":"110112"},{"areaName":"顺义区","areaCode":"110113"},{"areaName":"昌平区","areaCode":"110114"},{"areaName":"大兴区","areaCode":"110115"},{"areaName":"怀柔区","areaCode":"110116"},{"areaName":"平谷区","areaCode":"110117"},{"areaName":"密云县","areaCode":"110228"},{"areaName":"延庆县","areaCode":"110229"}]}]},{"provinceName":"天津市","provinceCode":"120000","cityNames":[{"cityName":"天津市","cityCode":"120100","areaNames":[{"areaName":"和平区","areaCode":"120101"},{"areaName":"河东区","areaCode":"120102"},{"areaName":"河西区","areaCode":"120103"},{"areaName":"南开区","areaCode":"120104"},{"areaName":"河北区","areaCode":"120105"},{"areaName":"红桥区","areaCode":"120106"},{"areaName":"东丽区","areaCode":"120110"},{"areaName":"西青区","areaCode":"120111"},{"areaName":"津南区","areaCode":"120112"},{"areaName":"北辰区","areaCode":"120113"},{"areaName":"武清区","areaCode":"120114"},{"areaName":"宝坻区","areaCode":"120115"},{"areaName":"滨海新区","areaCode":"120116"},{"areaName":"宁河县","areaCode":"120221"},{"areaName":"静海县","areaCode":"120223"},{"areaName":"蓟县","areaCode":"120225"}]}]},{"provinceName":"河北省","provinceCode":"130000","cityNames":[{"cityName":"唐山市","cityCode":"130200","areaNames":[{"areaName":"路南区","areaCode":"130202"},{"areaName":"路北区","areaCode":"130203"},{"areaName":"古冶区","areaCode":"130204"},{"areaName":"开平区","areaCode":"130205"},{"areaName":"丰南区","areaCode":"130207"},{"areaName":"丰润区","areaCode":"130208"},{"areaName":"滦县","areaCode":"130223"},{"areaName":"滦南县","areaCode":"130224"},{"areaName":"乐亭县","areaCode":"130225"},{"areaName":"迁西县","areaCode":"130227"},{"areaName":"玉田县","areaCode":"130229"},{"areaName":"曹妃甸区","areaCode":"130230"},{"areaName":"遵化市","areaCode":"130281"},{"areaName":"迁安市","areaCode":"130283"}]},{"cityName":"石家庄市","cityCode":"130100","areaNames":[{"areaName":"长安区","areaCode":"130102"},{"areaName":"桥东区","areaCode":"130103"},{"areaName":"桥西区","areaCode":"130104"},{"areaName":"新华区","areaCode":"130105"},{"areaName":"井陉矿区","areaCode":"130107"},{"areaName":"裕华区","areaCode":"130108"},{"areaName":"井陉县","areaCode":"130121"},{"areaName":"正定县","areaCode":"130123"},{"areaName":"栾城县","areaCode":"130124"},{"areaName":"行唐县","areaCode":"130125"},{"areaName":"灵寿县","areaCode":"130126"},{"areaName":"高邑县","areaCode":"130127"},{"areaName":"深泽县","areaCode":"130128"},{"areaName":"赞皇县","areaCode":"130129"},{"areaName":"无极县","areaCode":"130130"},{"areaName":"平山县","areaCode":"130131"},{"areaName":"元氏县","areaCode":"130132"},{"areaName":"赵县","areaCode":"130133"},{"areaName":"辛集市","areaCode":"130181"},{"areaName":"藁城市","areaCode":"130182"},{"areaName":"晋州市","areaCode":"130183"},{"areaName":"新乐市","areaCode":"130184"},{"areaName":"鹿泉市","areaCode":"130185"}]}]},{"provinceName":"山西省","provinceCode":"140000","cityNames":[{"cityName":"大同市","cityCode":"140200","areaNames":[{"areaName":"城区","areaCode":"140202"},{"areaName":"矿区","areaCode":"140203"},{"areaName":"南郊区","areaCode":"140211"},{"areaName":"新荣区","areaCode":"140212"},{"areaName":"阳高县","areaCode":"140221"},{"areaName":"天镇县","areaCode":"140222"},{"areaName":"广灵县","areaCode":"140223"},{"areaName":"灵丘县","areaCode":"140224"},{"areaName":"浑源县","areaCode":"140225"},{"areaName":"左云县","areaCode":"140226"},{"areaName":"大同县","areaCode":"140227"}]}]},{"provinceName":"辽宁省","provinceCode":"210000","cityNames":[{"cityName":"沈阳市","cityCode":"210100","areaNames":[{"areaName":"和平区","areaCode":"210102"},{"areaName":"沈河区","areaCode":"210103"},{"areaName":"大东区","areaCode":"210104"},{"areaName":"皇姑区","areaCode":"210105"},{"areaName":"铁西区","areaCode":"210106"},{"areaName":"苏家屯区","areaCode":"210111"},{"areaName":"东陵区","areaCode":"210112"},{"areaName":"于洪区","areaCode":"210114"},{"areaName":"辽中县","areaCode":"210122"},{"areaName":"康平县","areaCode":"210123"},{"areaName":"法库县","areaCode":"210124"},{"areaName":"新民市","areaCode":"210181"},{"areaName":"沈北新区","areaCode":"210184"}]}]},{"provinceName":"浙江省","provinceCode":"330000","cityNames":[{"cityName":"宁波市","cityCode":"330200","areaNames":[{"areaName":"海曙区","areaCode":"330203"},{"areaName":"江东区","areaCode":"330204"},{"areaName":"江北区","areaCode":"330205"},{"areaName":"北仑区","areaCode":"330206"},{"areaName":"镇海区","areaCode":"330211"},{"areaName":"鄞州区","areaCode":"330212"},{"areaName":"象山县","areaCode":"330225"},{"areaName":"宁海县","areaCode":"330226"},{"areaName":"余姚市","areaCode":"330281"},{"areaName":"慈溪市","areaCode":"330282"},{"areaName":"奉化市","areaCode":"330283"}]},{"cityName":"温州市","cityCode":"330300","areaNames":[{"areaName":"鹿城区","areaCode":"330302"},{"areaName":"龙湾区","areaCode":"330303"},{"areaName":"瓯海区","areaCode":"330304"},{"areaName":"洞头县","areaCode":"330322"},{"areaName":"永嘉县","areaCode":"330324"},{"areaName":"平阳县","areaCode":"330326"},{"areaName":"苍南县","areaCode":"330327"},{"areaName":"文成县","areaCode":"330328"},{"areaName":"泰顺县","areaCode":"330329"},{"areaName":"瑞安市","areaCode":"330381"},{"areaName":"乐清市","areaCode":"330382"}]},{"cityName":"嘉兴市","cityCode":"330400","areaNames":[{"areaName":"南湖区","areaCode":"330402"},{"areaName":"秀洲区","areaCode":"330411"},{"areaName":"嘉善县","areaCode":"330421"},{"areaName":"海盐县","areaCode":"330424"},{"areaName":"海宁市","areaCode":"330481"},{"areaName":"平湖市","areaCode":"330482"},{"areaName":"桐乡市","areaCode":"330483"}]},{"cityName":"丽水市","cityCode":"331100","areaNames":[{"areaName":"莲都区","areaCode":"331102"},{"areaName":"青田县","areaCode":"331121"},{"areaName":"缙云县","areaCode":"331122"},{"areaName":"遂昌县","areaCode":"331123"},{"areaName":"松阳县","areaCode":"331124"},{"areaName":"云和县","areaCode":"331125"},{"areaName":"庆元县","areaCode":"331126"},{"areaName":"景宁畲族自治县","areaCode":"331127"},{"areaName":"龙泉市","areaCode":"331181"}]},{"cityName":"台州市","cityCode":"331000","areaNames":[{"areaName":"椒江区","areaCode":"331002"},{"areaName":"黄岩区","areaCode":"331003"},{"areaName":"路桥区","areaCode":"331004"},{"areaName":"玉环县","areaCode":"331021"},{"areaName":"三门县","areaCode":"331022"},{"areaName":"天台县","areaCode":"331023"},{"areaName":"仙居县","areaCode":"331024"},{"areaName":"温岭市","areaCode":"331081"},{"areaName":"临海市","areaCode":"331082"}]},{"cityName":"杭州市","cityCode":"330100","areaNames":[{"areaName":"上城区","areaCode":"330102"},{"areaName":"下城区","areaCode":"330103"},{"areaName":"江干区","areaCode":"330104"},{"areaName":"拱墅区","areaCode":"330105"},{"areaName":"西湖区","areaCode":"330106"},{"areaName":"滨江区","areaCode":"330108"},{"areaName":"萧山区","areaCode":"330109"},{"areaName":"余杭区","areaCode":"330110"},{"areaName":"桐庐县","areaCode":"330122"},{"areaName":"淳安县","areaCode":"330127"},{"areaName":"建德市","areaCode":"330182"},{"areaName":"富阳市","areaCode":"330183"},{"areaName":"临安市","areaCode":"330185"}]}]},{"provinceName":"广东省","provinceCode":"440000","cityNames":[{"cityName":"深圳市","cityCode":"440300","areaNames":[{"areaName":"罗湖区","areaCode":"440303"},{"areaName":"福田区","areaCode":"440304"},{"areaName":"南山区","areaCode":"440305"},{"areaName":"宝安区","areaCode":"440306"},{"areaName":"龙岗区","areaCode":"440307"},{"areaName":"盐田区","areaCode":"440308"},{"areaName":"光明新区","areaCode":"440320"},{"areaName":"坪山新区","areaCode":"440321"},{"areaName":"大鹏新区","areaCode":"440322"},{"areaName":"龙华新区","areaCode":"440323"}]}]},{"provinceName":"四川省","provinceCode":"510000","cityNames":[{"cityName":"南充市","cityCode":"511300","areaNames":[{"areaName":"顺庆区","areaCode":"511302"},{"areaName":"高坪区","areaCode":"511303"},{"areaName":"嘉陵区","areaCode":"511304"},{"areaName":"南部县","areaCode":"511321"},{"areaName":"营山县","areaCode":"511322"},{"areaName":"蓬安县","areaCode":"511323"},{"areaName":"仪陇县","areaCode":"511324"},{"areaName":"西充县","areaCode":"511325"},{"areaName":"阆中市","areaCode":"511381"}]}]},{"provinceName":"贵州省","provinceCode":"520000","cityNames":[{"cityName":"贵阳市","cityCode":"520100","areaNames":[{"areaName":"南明区","areaCode":"520102"}]}]},{"provinceName":"陕西省","provinceCode":"610000","cityNames":[{"cityName":"西安市","cityCode":"610100","areaNames":[{"areaName":"高陵县","areaCode":"610126"},{"areaName":"新城区","areaCode":"610102"},{"areaName":"碑林区","areaCode":"610103"}]}]}]
     * error : false
     * success : true
     */

    private int code;
    private String message;
    private boolean error;
    private boolean success;
    private List<DataBean> data;

    public CityChangeModel(boolean isHeader, String header) {
        super(isHeader, header);
    }
    public CityChangeModel(DataBean dataBean) {
        super(dataBean);
    }

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
         * provinceCode : 110100
         * cityNames : [{"cityName":"北京市","cityCode":"110110","areaNames":[{"areaName":"东城区","areaCode":"110101"},{"areaName":"西城区","areaCode":"110102"},{"areaName":"朝阳区","areaCode":"110105"},{"areaName":"丰台区","areaCode":"110106"},{"areaName":"石景山区","areaCode":"110107"},{"areaName":"海淀区","areaCode":"110108"},{"areaName":"门头沟区","areaCode":"110109"},{"areaName":"房山区","areaCode":"110111"},{"areaName":"通州区","areaCode":"110112"},{"areaName":"顺义区","areaCode":"110113"},{"areaName":"昌平区","areaCode":"110114"},{"areaName":"大兴区","areaCode":"110115"},{"areaName":"怀柔区","areaCode":"110116"},{"areaName":"平谷区","areaCode":"110117"},{"areaName":"密云县","areaCode":"110228"},{"areaName":"延庆县","areaCode":"110229"}]}]
         */

        private String provinceName;
        private String provinceCode;
        private List<CityNamesBean> cityNames;

//        public DataBean(boolean isHeader, String header) {
//            super(isHeader, header);
//        }
//        public DataBean(CityChangeModel.DataBean dataBean) {
//            super(dataBean);
//        }
        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
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
             * cityCode : 110110
             * areaNames : [{"areaName":"东城区","areaCode":"110101"},{"areaName":"西城区","areaCode":"110102"},{"areaName":"朝阳区","areaCode":"110105"},{"areaName":"丰台区","areaCode":"110106"},{"areaName":"石景山区","areaCode":"110107"},{"areaName":"海淀区","areaCode":"110108"},{"areaName":"门头沟区","areaCode":"110109"},{"areaName":"房山区","areaCode":"110111"},{"areaName":"通州区","areaCode":"110112"},{"areaName":"顺义区","areaCode":"110113"},{"areaName":"昌平区","areaCode":"110114"},{"areaName":"大兴区","areaCode":"110115"},{"areaName":"怀柔区","areaCode":"110116"},{"areaName":"平谷区","areaCode":"110117"},{"areaName":"密云县","areaCode":"110228"},{"areaName":"延庆县","areaCode":"110229"}]
             */

            private String cityName;
            private String cityCode;
            private List<AreaNamesBean> areaNames;

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getCityCode() {
                return cityCode;
            }

            public void setCityCode(String cityCode) {
                this.cityCode = cityCode;
            }

            public List<AreaNamesBean> getAreaNames() {
                return areaNames;
            }

            public void setAreaNames(List<AreaNamesBean> areaNames) {
                this.areaNames = areaNames;
            }

            public static class AreaNamesBean {
                /**
                 * areaName : 东城区
                 * areaCode : 110101
                 */

                private String areaName;
                private String areaCode;

                public String getAreaName() {
                    return areaName;
                }

                public void setAreaName(String areaName) {
                    this.areaName = areaName;
                }

                public String getAreaCode() {
                    return areaCode;
                }

                public void setAreaCode(String areaCode) {
                    this.areaCode = areaCode;
                }
            }
        }
    }
}
