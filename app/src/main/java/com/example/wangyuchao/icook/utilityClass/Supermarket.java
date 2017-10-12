package com.example.wangyuchao.icook.utilityClass;

/**
 * Created by WangYuchao on 2017/7/2.
 */

public class Supermarket {

    private String name;
    private int imageId;

    public Supermarket(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
