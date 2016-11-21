package Modele.Entity;


public class ModelEntity {
    private Long id_modele;
    private String nazwaMod;
    private String producent;
    private String program;

    public Long getId_modele() {
        return id_modele;
    }

    public void setId_modele(Long id_modele) {
        this.id_modele = id_modele;
    }

    public String getNazwaMod() {
        return nazwaMod;
    }

    public void setNazwaMod(String nazwaMod) {
        this.nazwaMod = nazwaMod;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }


}
