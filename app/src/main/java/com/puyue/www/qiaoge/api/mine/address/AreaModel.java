package com.puyue.www.qiaoge.api.mine.address;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by ${王涛} on 2020/3/9
 */
public class AreaModel {
    /**
     * code : 1
     * message : 成功
     * data : [{"code":"110100","name":"北京市","children":[{"code":"110110","name":"北京市","children":[{"code":"110101","name":"东城区","children":null},{"code":"110102","name":"西城区","children":null},{"code":"110105","name":"朝阳区","children":null},{"code":"110106","name":"丰台区","children":null},{"code":"110107","name":"石景山区","children":null},{"code":"110108","name":"海淀区","children":null},{"code":"110109","name":"门头沟区","children":null},{"code":"110111","name":"房山区","children":null},{"code":"110112","name":"通州区","children":null},{"code":"110113","name":"顺义区","children":null},{"code":"110114","name":"昌平区","children":null},{"code":"110115","name":"大兴区","children":null},{"code":"110116","name":"怀柔区","children":null},{"code":"110117","name":"平谷区","children":null},{"code":"110228","name":"密云县","children":null},{"code":"110229","name":"延庆县","children":null}]}]},{"code":"820000","name":"澳门特别行政区","children":[{"code":"820201","name":"澳门特别行政区","children":[{"code":"820100","name":"澳门半岛","children":null},{"code":"820200","name":"离岛","children":null}]}]}]
     * success : true
     * error : false
     */

    private int code;
    private String message;
    private boolean success;
    private boolean error;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements IPickerViewData{
        /**
         * code : 110100
         * name : 北京市
         * children : [{"code":"110110","name":"北京市","children":[{"code":"110101","name":"东城区","children":null},{"code":"110102","name":"西城区","children":null},{"code":"110105","name":"朝阳区","children":null},{"code":"110106","name":"丰台区","children":null},{"code":"110107","name":"石景山区","children":null},{"code":"110108","name":"海淀区","children":null},{"code":"110109","name":"门头沟区","children":null},{"code":"110111","name":"房山区","children":null},{"code":"110112","name":"通州区","children":null},{"code":"110113","name":"顺义区","children":null},{"code":"110114","name":"昌平区","children":null},{"code":"110115","name":"大兴区","children":null},{"code":"110116","name":"怀柔区","children":null},{"code":"110117","name":"平谷区","children":null},{"code":"110228","name":"密云县","children":null},{"code":"110229","name":"延庆县","children":null}]}]
         */

        private String code;
        private String name;
        private List<ChildrenBeanX> children;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildrenBeanX> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBeanX> children) {
            this.children = children;
        }

        @Override
        public String getPickerViewText() {
            return name;
        }

        public static class ChildrenBeanX implements IPickerViewData {
            @Override
            public String toString() {
                return name;
            }

            /**
             * code : 110110
             * name : 北京市
             * children : [{"code":"110101","name":"东城区","children":null},{"code":"110102","name":"西城区","children":null},{"code":"110105","name":"朝阳区","children":null},{"code":"110106","name":"丰台区","children":null},{"code":"110107","name":"石景山区","children":null},{"code":"110108","name":"海淀区","children":null},{"code":"110109","name":"门头沟区","children":null},{"code":"110111","name":"房山区","children":null},{"code":"110112","name":"通州区","children":null},{"code":"110113","name":"顺义区","children":null},{"code":"110114","name":"昌平区","children":null},{"code":"110115","name":"大兴区","children":null},{"code":"110116","name":"怀柔区","children":null},{"code":"110117","name":"平谷区","children":null},{"code":"110228","name":"密云县","children":null},{"code":"110229","name":"延庆县","children":null}]
             */

            private String code;
            private String name;
            private List<ChildrenBean> children;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            @Override
            public String getPickerViewText() {
                return name;
            }

            public static class ChildrenBean implements IPickerViewData{
                @Override
                public String toString() {
                    return name;
                }

                /**
                 * code : 110101
                 * name : 东城区
                 * children : null
                 */

                private String code;
                private String name;
                private Object children;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Object getChildren() {
                    return children;
                }

                public void setChildren(Object children) {
                    this.children = children;
                }

                @Override
                public String getPickerViewText() {
                    return name;
                }
            }
        }
    }


//    /**--------------------------------------------------------------------
//     * code : 110100
//     * name : 北京市
//     * children : [{"code":"110110","name":"北京市","children":[{"code":"110101","name":"东城区","children":null},{"code":"110102","name":"西城区","children":null},{"code":"110105","name":"朝阳区","children":null},{"code":"110106","name":"丰台区","children":null},{"code":"110107","name":"石景山区","children":null},{"code":"110108","name":"海淀区","children":null},{"code":"110109","name":"门头沟区","children":null},{"code":"110111","name":"房山区","children":null},{"code":"110112","name":"通州区","children":null},{"code":"110113","name":"顺义区","children":null},{"code":"110114","name":"昌平区","children":null},{"code":"110115","name":"大兴区","children":null},{"code":"110116","name":"怀柔区","children":null},{"code":"110117","name":"平谷区","children":null},{"code":"110228","name":"密云县","children":null},{"code":"110229","name":"延庆县","children":null}]}]
//     */
//
//    private String code;
//    private String name;
//    private List<ChildrenBeanX> children;
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<ChildrenBeanX> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<ChildrenBeanX> children) {
//        this.children = children;
//    }
//
////    @Override
////    public String getPickerViewText() {
////        return name;
////    }
//
//    public static class ChildrenBeanX {
//        /**
//         * code : 110110
//         * name : 北京市
//         * children : [{"code":"110101","name":"东城区","children":null},{"code":"110102","name":"西城区","children":null},{"code":"110105","name":"朝阳区","children":null},{"code":"110106","name":"丰台区","children":null},{"code":"110107","name":"石景山区","children":null},{"code":"110108","name":"海淀区","children":null},{"code":"110109","name":"门头沟区","children":null},{"code":"110111","name":"房山区","children":null},{"code":"110112","name":"通州区","children":null},{"code":"110113","name":"顺义区","children":null},{"code":"110114","name":"昌平区","children":null},{"code":"110115","name":"大兴区","children":null},{"code":"110116","name":"怀柔区","children":null},{"code":"110117","name":"平谷区","children":null},{"code":"110228","name":"密云县","children":null},{"code":"110229","name":"延庆县","children":null}]
//         */
//
//        private String code;
//        private String name;
//        private List<ChildrenBean> children;
//
//        public String getCode() {
//            return code;
//        }
//
//        public void setCode(String code) {
//            this.code = code;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public List<ChildrenBean> getChildren() {
//            return children;
//        }
//
//        public void setChildren(List<ChildrenBean> children) {
//            this.children = children;
//        }
//
//        public static class ChildrenBean {
//            /**
//             * code : 110101
//             * name : 东城区
//             * children : null
//             */
//
//            private String code;
//            private String name;
//            private Object children;
//
//            public String getCode() {
//                return code;
//            }
//
//            public void setCode(String code) {
//                this.code = code;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public Object getChildren() {
//                return children;
//            }
//
//            public void setChildren(Object children) {
//                this.children = children;
//            }
//        }
//    }
}
