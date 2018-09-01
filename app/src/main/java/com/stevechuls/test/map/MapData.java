package com.stevechuls.test.map;

/**
 * Created by entermate_ksc on 2018. 4. 16..
 */

public class MapData {

    private static MapData mapData;


    private String x;
    private String y;
    private String address;

    private MapData()
    {

    }

    public static MapData getInstance()
    {
        if(mapData == null)
        {
            mapData = new MapData();
        }
        return mapData;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getAddr() {
        return address;
    }

    public void setAddr(String address) {
        this.address = address;
    }
}
