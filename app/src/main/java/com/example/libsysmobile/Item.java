package com.example.libsysmobile;

public class Item {
    private String title;
    private int itemID;
    private int isbn;
    private String upc;

    public Item(int itemID, String title, int isbn, String upc) {
        this.itemID = itemID;
        this.title = title;
        this.isbn = isbn;
        this.upc = upc;
    }

    public int getItemID() {
        return itemID;
    }

    public String getTitle() {
        return this.title;
    }

    public int getISBN() {
        return isbn;
    }

    public String getUpc() {
        return upc;
    }

}
