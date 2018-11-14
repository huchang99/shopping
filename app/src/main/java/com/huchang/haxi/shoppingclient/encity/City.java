package com.huchang.haxi.shoppingclient.encity;

public class City {
    private String id;
    private String name;
    private String sortKey;

    public City() {
    }
    public City(String id, String name, String sortKey) {
        this.id = id;
        this.name = name;
        this.sortKey = sortKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }
}
