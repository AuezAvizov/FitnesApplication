package com.example.fitnesapplication.Yoga;

import java.io.Serializable;

public class Category implements Serializable {
    private int id;
    private String categoryName;
    private String englishName;
    private String sanskritName;
    private String sanskritNameAdapted;
    private String translationName;
    private String poseDescription;
    private String poseBenefits;
    private String urlSvg;
    private String urlPng;
    private String urlSvgAlt;

    // Геттеры и сеттеры

    public String getCategoryName() {
        return categoryName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getPoseDescription() {
        return poseDescription;
    }

    public String getUrlPng() {
        return urlPng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getSanskritName() {
        return sanskritName;
    }

    public void setSanskritName(String sanskritName) {
        this.sanskritName = sanskritName;
    }

    public String getSanskritNameAdapted() {
        return sanskritNameAdapted;
    }

    public void setSanskritNameAdapted(String sanskritNameAdapted) {
        this.sanskritNameAdapted = sanskritNameAdapted;
    }

    public String getTranslationName() {
        return translationName;
    }

    public void setTranslationName(String translationName) {
        this.translationName = translationName;
    }

    public void setPoseDescription(String poseDescription) {
        this.poseDescription = poseDescription;
    }

    public String getPoseBenefits() {
        return poseBenefits;
    }

    public void setPoseBenefits(String poseBenefits) {
        this.poseBenefits = poseBenefits;
    }

    public String getUrlSvg() {
        return urlSvg;
    }

    public void setUrlSvg(String urlSvg) {
        this.urlSvg = urlSvg;
    }

    public void setUrlPng(String urlPng) {
        this.urlPng = urlPng;
    }

    public String getUrlSvgAlt() {
        return urlSvgAlt;
    }

    public void setUrlSvgAlt(String urlSvgAlt) {
        this.urlSvgAlt = urlSvgAlt;
    }
}
