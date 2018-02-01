package models;

/**
 * Created by Ajeng on 1/13/2018.
 */

public class Pelanggan {
    String nama;
    String noHp;
    String id_pelanggan;

    public Pelanggan() {
    }

    public Pelanggan(String id_pelanggan,String nama, String noHp) {
        this.nama = nama;
        this.noHp = noHp;
        this.id_pelanggan = id_pelanggan;
    }

    public String getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(String id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
}
