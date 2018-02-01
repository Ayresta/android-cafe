package models;

/**
 * Created by mkhamdank on 16/01/2018.
 */

public class Pesanan {
    String idPesanan,namaPelanggan,namaMenu,jumlahMenu,hargaMenu,status;

    public Pesanan() {
    }

    public Pesanan(String idPesanan,String namaPelanggan, String namaMenu, String jumlahMenu, String hargaMenu,String status) {
        this.idPesanan = idPesanan;
        this.namaPelanggan = namaPelanggan;
        this.namaMenu = namaMenu;
        this.jumlahMenu = jumlahMenu;
        this.hargaMenu = hargaMenu;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(String idPesanan) {
        this.idPesanan = idPesanan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public String getJumlahMenu() {
        return jumlahMenu;
    }

    public void setJumlahMenu(String jumlahMenu) {
        this.jumlahMenu = jumlahMenu;
    }

    public String getHargaMenu() {
        return hargaMenu;
    }

    public void setHargaMenu(String hargaMenu) {
        this.hargaMenu = hargaMenu;
    }
}
