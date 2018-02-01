package models;

/**
 * Created by Ajeng on 1/13/2018.
 */

public class MenuCafe {
    String id_menu,nama_menu,harga,stok;

    public MenuCafe() {
    }

    public MenuCafe(String id_menu, String nama_menu, String harga, String stok) {
        this.id_menu = id_menu;
        this.nama_menu = nama_menu;
        this.harga = harga;
        this.stok = stok;
    }

    public String getId_menu() {
        return id_menu;
    }

    public void setId_menu(String id_menu) {
        this.id_menu = id_menu;
    }

    public String getNama_menu() {
        return nama_menu;
    }

    public void setNama_menu(String nama_menu) {
        this.nama_menu = nama_menu;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }
}
