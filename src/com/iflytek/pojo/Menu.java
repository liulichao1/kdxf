package com.iflytek.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Serializable{
    private Integer id;
    private String name;
    private String ico;
    private String url;
    private List<Menu> children = new ArrayList<Menu>();
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIco() {
        return ico;
    }
    public void setIco(String ico) {
        this.ico = ico;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Menu(Integer id, String name, String ico, String url) {
        super();
        this.id = id;
        this.name = name;
        this.ico = ico;
        this.url = url;
    }
    public Menu() {
        super();
    }
    public List<Menu> getChildren() {
        return children;
    }
    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
