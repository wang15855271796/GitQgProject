package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by ${王涛} on 2020/3/24
 */
public class AddressBean {


    /**
     * code : 1
     * message : 成功
     * data : [{"id":2,"name":"麻辣烫","num":707,"list":[{"id":3,"name":"炸鸡汉堡","num":9,"list":[{"id":7,"name":"简单餐","num":6,"list":null},{"id":6,"name":"便单","num":1,"list":null}]}]},{"id":1,"name":"烧烤汉堡","num":30,"list":null},{"id":8,"name":"中餐","num":19,"list":null},{"id":4,"name":"汤面饭","num":13,"list":null},{"id":9,"name":"西餐","num":10,"list":null},{"id":12,"name":"店铺1","num":10,"list":null},{"id":5,"name":"早餐粥铺","num":5,"list":null},{"id":11,"name":"其他一","num":3,"list":null},{"id":23,"name":"方式页面","num":2,"list":null},{"id":10,"name":"海底捞4","num":1,"list":null},{"id":13,"name":"鲜肉","num":0,"list":null}]
     * error : false
     * success : true
     */

    private int code;
    private String message;
    private boolean error;
    private boolean success;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "AddressBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", error=" + error +
                ", success=" + success +
                ", data=" + data +
                '}';
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
        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", num=" + num +
                    ", list=" + list +
                    '}';
        }

        /**
         * id : 2
         * name : 麻辣烫
         * num : 707
         * list : [{"id":3,"name":"炸鸡汉堡","num":9,"list":[{"id":7,"name":"简单餐","num":6,"list":null},{"id":6,"name":"便单","num":1,"list":null}]}]
         */

        private int id;
        private String name;
        private int num;
        private List<ListBeanX> list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            @Override
            public String toString() {
                return "ListBeanX{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", num=" + num +
                        ", list=" + list +
                        '}';
            }

            /**
             * id : 3
             * name : 炸鸡汉堡
             * num : 9
             * list : [{"id":7,"name":"简单餐","num":6,"list":null},{"id":6,"name":"便单","num":1,"list":null}]
             */

            private int id;
            private String name;
            private int num;
            private List<ListBean> list;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 7
                 * name : 简单餐
                 * num : 6
                 * list : null
                 */

                private int id;
                private String name;
                private int num;
                private Object list;

                @Override
                public String toString() {
                    return "ListBean{" +
                            "id=" + id +
                            ", name='" + name + '\'' +
                            ", num=" + num +
                            ", list=" + list +
                            '}';
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public Object getList() {
                    return list;
                }

                public void setList(Object list) {
                    this.list = list;
                }
            }
        }
    }
}
