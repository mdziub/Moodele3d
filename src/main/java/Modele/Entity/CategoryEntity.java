package Modele.Entity;


public class CategoryEntity {

    private Long id_kategoria;
    private String nazwaKat;
    private String opis;
    private int numer;
    private Long idmod;

    public Long getId_kategoria() {
        return id_kategoria;
    }

    public void setId_kategoria(Long id_kategoria) {
        this.id_kategoria = id_kategoria;
    }

    public String getNazwaKat() {
        return nazwaKat;
    }

    public void setNazwaKat(String nazwaKat) {
        this.nazwaKat = nazwaKat;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public Long getIdmod() {
        return idmod;
    }

    public void setIdmod(Long idmod) {
        this.idmod = idmod;
    }


}
