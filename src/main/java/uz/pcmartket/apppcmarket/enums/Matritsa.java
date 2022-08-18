package uz.pcmartket.apppcmarket.enums;

public enum Matritsa {
    IPS(1,"IPS"),
    TN(2,"TN"),
    TFT_IPS(3,"TFT IPS"),
    VA(4,"VA");

    Integer id;
    String value;

    Matritsa(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
