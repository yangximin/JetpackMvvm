package com.yang.jetpack.bean.home;

/**
 * Created by yxm on 2021/1/18.
 */
public class HomeDate {

    Object object;
    /**
     * 0 banner，1 置顶文章，2普通文章
     */
    int type;

    public HomeDate(int type, Object object) {
        this.object = object;
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
