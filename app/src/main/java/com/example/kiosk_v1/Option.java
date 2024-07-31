package com.example.kiosk_v1;

public class Option {
    private final String option_name;
    private final int option_charge;

    public Option(String option_name, int option_charge) {
        this.option_name = option_name;
        this.option_charge = option_charge;
    }

    public String getName() {
        return option_name;
    }

    public int getExtraCharge() {
        return option_charge;
    }
}

