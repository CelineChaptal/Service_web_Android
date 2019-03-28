package com.example.service_web_android;


public class Medecin {

    //proprietes
    private Integer num;
    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String libelle;
    private double coefNotoriete;

    /**
     * constructeur
     * @param num
     * @param nom
     * @param prenom
     * @param adresse
     * @param codePostal
     * @param ville
     * @param libelle
     * @param coefNotoriete
     */
    public Medecin(Integer num, String nom, String prenom, String adresse, String codePostal, String ville, String libelle, double coefNotoriete) {
        this.num = num;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.libelle = libelle;
        this.coefNotoriete = coefNotoriete;
    }

    /**
     * getter
     * @return num du medecin
     */
    public Integer getNum() {
        return num;
    }

    /**
     * getter
     * @return nom du medecin
     */
    public String getNom() {
        return nom;
    }

    /**
     * getter
     * @return prenom du medecin
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * getter
     * @return adresse du medecin
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * getter
     * @return code postal du medecin
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * getter
     * @return ville du medecin
     */
    public String getVille() {
        return ville;
    }

    /**
     * getter
     * @return libelle de la fonction du medecin
     *
     *
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * getter
     * @return coefficient de la notoriete du medecin
     */
    public double getCoefNotoriete() {
        return coefNotoriete;
    }

    /**
     * setter
     * @param num
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * setter
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * setter
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * setter
     * @param adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * setter
     * @param codePostal
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * setter
     * @param ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * setter
     * @param libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * setter
     * @param coefNotoriete
     */
    public void setCoefNotoriete(double coefNotoriete) {
        this.coefNotoriete = coefNotoriete;
    }
}