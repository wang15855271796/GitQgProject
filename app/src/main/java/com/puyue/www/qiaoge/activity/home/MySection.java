package com.puyue.www.qiaoge.activity.home;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.puyue.www.qiaoge.model.home.CityChangeModel;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MySection extends SectionEntity<CityChangeModel> {
    private boolean isMore;
    public MySection(boolean isHeader, String header, boolean isMroe) {
        super(isHeader, header);
        this.isMore = isMroe;
    }

    public MySection(CityChangeModel t) {
        super(t);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean mroe) {
        isMore = mroe;
    }
}
