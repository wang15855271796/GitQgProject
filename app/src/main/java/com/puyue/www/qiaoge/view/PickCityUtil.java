package com.puyue.www.qiaoge.view;


import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：pickerview工具类
 */

public class PickCityUtil {

    private static ArrayList<String> options1Items = new ArrayList<>();//一级
    private static ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//二级
    private static ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//三级

    private static ArrayList<String> areaId1 = new ArrayList<>();//一级
    private static ArrayList<ArrayList<String>> areaId2 = new ArrayList<>();//二级
    private static ArrayList<ArrayList<ArrayList<String>>> areaId3 = new ArrayList<>();//三级

    private static String mJsonObj;
    private static Gson gson;

    /**
     * 获取城市信息字符串
     *
     * @param context
     * @return
     */
    public static String getCityJson(Context context) {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = context.getAssets().open("json/city.json");
            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "utf-8"));
            }
            is.close();
            mJsonObj = sb.toString();
            return mJsonObj;
        } catch (Exception e) {
            return null;
        }
    }


  /* public static void initPickView(Context context) {
        String cityJson = getCityJson(context);
        if (gson == null) {
            gson = new Gson();
        }
        if (cityJson == null) {
            return;
        }
        try {
            List<CityDataModel> dataModelList=gson.fromJson(cityJson, new TypeToken<List<CityDataModel>>(){}.getType());

            if (dataModelList!=null&&!dataModelList.isEmpty()) {
                options1Items.clear();
                options2Items.clear();
                options3Items.clear();
                areaId1.clear();
                areaId2.clear();
                areaId3.clear();
                for (int i = 0; i < dataModelList.size(); i++) {
                    options1Items.add(dataModelList.get(i).areaName);//省
                    areaId1.add(dataModelList.get(i).areaId);
                    List<CityDataModel.CitiesBean> citiesBeanList = dataModelList.get(i).cities;//市

                    ArrayList<String> options2Items_item = new ArrayList<>();
                    ArrayList<ArrayList<String>> options3Items_item = new ArrayList<>();
                    //区域码
                    ArrayList<String> areaId2Items_item = new ArrayList<>();
                    ArrayList<ArrayList<String>> areaId3Items_item = new ArrayList<>();

                    for (int j = 0; j < citiesBeanList.size(); j++) {//市
                        options2Items_item.add(citiesBeanList.get(j).areaName);
                         areaId2Items_item.add(citiesBeanList.get(j).areaId);

                        List<CityDataModel.CitiesBean.CountiesBean> countiesBeanList = citiesBeanList.get(j).counties;//区

                        ArrayList<String> options3Items_item_item = new ArrayList<>();
                        ArrayList<String> areaId3Items_item_item = new ArrayList<>();
                            for (CityDataModel.CitiesBean.CountiesBean countiesBean : countiesBeanList) {//区
                                options3Items_item_item.add(countiesBean.areaName);
                                areaId3Items_item_item.add(countiesBean.areaId);
                            }




                        options3Items_item.add(options3Items_item_item);
                        areaId3Items_item.add(areaId3Items_item_item);

                    }
                    options2Items.add(options2Items_item);
                    options3Items.add(options3Items_item);
                    //区域编码
                    areaId2.add(areaId2Items_item);
                    areaId3.add(areaId3Items_item);

                }
            }
        } catch (Exception e) {
            Log.e("e:", "" + e.getMessage());
        }

    }*/

//    /**
//     * 城市选择
//     *
//     * @param
//     * @param context
//     */
//    public static void showCityPickView(Context context, final ChooseCityListener listener) {
//
//        if (options1Items.isEmpty() || options2Items.isEmpty() || options3Items.isEmpty()) {
//            return;
//        }
//
//        OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                String p = options1Items.get(options1);
//                String c = options2Items.get(options1).get(options2);
//                String a = options3Items.get(options1).get(options2).get(options3);
//                if (p.equals(c)) {
//                    listener.chooseCity(c + "_" + a);
//                } else {
//                    listener.chooseCity(p + "_" + c + "_" + a);
//                }
//            }
//        })
//                .setTitleText("选择城市")
//                .build();
//        pvOptions.setPicker(options1Items, options2Items, options3Items);
//        pvOptions.show();
//    }

    /**
     * 分开传输地址
     */
//    public static void showCityPickViewDivide(Context context, final ChooseDivideCityListener listener) {
//
//        if (options1Items.isEmpty() || options2Items.isEmpty() || options3Items.isEmpty()) {
//            return;
//        }
//
//        OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                String p = options1Items.get(options1);
//                String c = options2Items.get(options1).get(options2);
//                String a = options3Items.get(options1).get(options2).get(options3);
//                if (p.equals(c)) {
//                    listener.chooseCity(c, a);
//                } else {
//                    listener.chooseCity(p, c, a);
//                }
//            }
//        })
//                .setTitleText("选择城市")
//                .build();
//        pvOptions.setPicker(options1Items, options2Items, options3Items);
//        pvOptions.show();
//    }


    /**
     * 单列表
     *
     * @param
     * @param context
     */
//    public static void showSinglePickView(Context context, final List<String> list, String title, final ChoosePositionListener listener) {
//
//        if (list.isEmpty()) {
//            return;
//        }
//
//        OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                listener.choosePosition(options1, list.get(options1));
//            }
//        })
//                .setTitleText("请选择配送时间段")
//                .setTitleColor(Color.parseColor("#F56D23"))
//                .setOutSideCancelable(false)
//                .setCancelColor(Color.parseColor("#F56D23"))
//                .setSubmitColor(Color.parseColor("#F56D23"))
//                .build();
//
//
//        View tvTitle = pvOptions.findViewById(R.id.tvTitle);
//        tvTitle.setVisibility(View.VISIBLE);
//        pvOptions.setPicker(list);
//        pvOptions.show();
//
//
//    }

    /**
     * 单列表
     *
     * @param
     * @param context
     */
//    public static void showSinglePickViewTwo(Context context, final List<String> list, String title, final ChoosePositionListener listener) {
//
//        if (list.isEmpty()) {
//            return;
//        }
//
//        OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                listener.choosePosition(options1, list.get(options1));
//            }
//        })
//                .setTitleText("请选择配送时间段")
//                .setTitleColor(Color.parseColor("#F56D23"))
//                .setOutSideCancelable(false)
//                .setCancelColor(Color.parseColor("#F56D23"))
//                .setSubmitColor(Color.parseColor("#FFFFFF"))
//                .build();
//
//
//        View tvTitle = pvOptions.findViewById(R.id.tvTitle);
//        tvTitle.setVisibility(View.VISIBLE);
//        //居中
//        //
//   View btnSubmit = pvOptions.findViewById(R.id.btnCancel);
//btnSubmit.setVisibility(View.GONE);
//
//
//
//     /*   params.setMargins(10, 10, 6, 10);
//        btnSubmit.setBackgroundResource(R.drawable.bg_deliver_ok);
//        // btnSubmit.setPadding(20, 5, 20, 5);
//        View btnCancel = pvOptions.findViewById(R.id.btnCancel);
//        LinearLayout.LayoutParams paramCancel = (LinearLayout.LayoutParams) btnCancel.getLayoutParams();
//
//
//        // btnCancel.setPadding(20, 5, 20, 5);
//        paramCancel.setMargins(6, 10, 10, 10);
//        btnCancel.setBackgroundResource(R.drawable.bg_deliver_time);*/
//
//
//        pvOptions.setPicker(list);
//        pvOptions.show();
//
//
//    }
    /**
     * 双列表
     *
     * @param
     * @param context
     */
    public static void showDoublePickView(Context context, final List<String> list1, final List<List<String>> list2, String title, final ChooseDPositionListener listener) {

//
    OptionsPickerView pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                listener.choosePosition(options1, options2, list1.get(options1) + "_" + list2.get(options1).get(options2));

                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()
//                        + options2Items.get(options1).get(options2)
//                        /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
//                btn_Options.setText(tx);
            }
        })
                .setTitleText("请选择自提时间段")
                .setTitleColor(Color.parseColor("#F56D23"))
                .setFlag(true)
                .setCancelColor(Color.parseColor("#F56D23"))
                .setSubmitColor(Color.parseColor("#F56D23"))
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {

                    }
                })
                .build();

        pvOptions.setPicker(list1, list2);
        pvOptions.show();


//        if (list1.isEmpty() || list2.isEmpty()) {
//            return;
//        }
//
//        OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                listener.choosePosition(options1, options2, list1.get(options1) + "_" + list2.get(options1).get(options2));
//            }
//        })
//                .setTitleText(title)
//                .setTitleColor(Color.parseColor("#F56D23"))
//
//                .setCancelColor(Color.parseColor("#F56D23"))
//                .setSubmitColor(Color.parseColor("#F56D23"))
//                .build();
//        pvOptions.setPicker(list1, list2);
//        pvOptions.show();


    }

    private void initOptionPicker() {//条件选择器初始化

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */



//        pvOptions.setSelectOptions(1,1);
        /*pvOptions.setPicker(options1Items);//一级选择器*/
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器
//        pvOptions.show();
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
    }



    /**
     * @param
     * @param
     */
//    public static void showCityPickView(Context context, final ChooseCityListener listener, final ChooseCityAreaIdListener areaIdListener) {
//
//        if (options1Items.isEmpty() || options2Items.isEmpty() || options3Items.isEmpty()) {
//            return;
//        }
//
//        OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                String p = options1Items.get(options1);
//                String c = options2Items.get(options1).get(options2);
//                String a = options3Items.get(options1).get(options2).get(options3);
//                if (p.equals(c)) {
//                    listener.chooseCity(c + "_" + a);
//                } else {
//                    listener.chooseCity(p + "_" + c + "_" + a);
//                }
//             /*   String ap= areaId1.get(options1);
//                String ac=areaId2.get(options1).get(options2);*/
//                String aa = areaId3.get(options1).get(options2).get(options3);
//                areaIdListener.chooseAreaId(/*ap+"_"+ac+"_"+*/aa);
//            }
//        })
//                .setTitleText("选择城市")
//                .build();
//        pvOptions.setPicker(options1Items, options2Items, options3Items);
//        pvOptions.show();
//    }

    public interface ChooseCityListener {
        void chooseCity(String s);
    }

    public interface ChooseDivideCityListener {
        void chooseCity(String c, String a);

        void chooseCity(String p, String c, String a);
    }

    public interface ChoosePositionListener {
        void choosePosition(int position, String s);
    }

    public interface ChooseDPositionListener {
        void choosePosition(int position1, int position2, String s);
    }

    public interface ChooseCityAreaIdListener {
        void chooseAreaId(String s);
    }
}
