package com.sda.mierloiubogdan.petclinic.utils;

public enum UserOption {
    ADD_VET(1, "Add a new vet"),
    VIEW_ALL_VETS(2, "View all vets"),
    VIEW_VET_BY_ID(3, "View vet by id"),
    UPDATE_VET_BY_ID(4, "Update vet by id"),
    DELETE_VET_BY_ID(5, "Delete vet by id"),
    EXIT(99, "Exit"),
    UNKNOWN(9999999, "Invalid option selected");

    private final int numericOption;

    private final String displayValue;

    UserOption(int numericOption, String displayValue) {
        this.numericOption = numericOption;
        this.displayValue = displayValue;
    }

    public static void displayAllOptions() {
        System.out.println();
        for (UserOption value : values()) {
            if (value != UNKNOWN) {
                System.out.println(value.getNumericOption() + "->" + value.getDisplayValue());
            }
        }

    }

    public static UserOption findByNumericOption(int numericOption) {
        for (UserOption value : UserOption.values()) {
            if (numericOption == value.getNumericOption()) {
                return value;
            }
        }
        return UNKNOWN;
    }

    public int getNumericOption() {
        return numericOption;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
