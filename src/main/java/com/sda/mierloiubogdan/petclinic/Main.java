package com.sda.mierloiubogdan.petclinic;

import com.sda.mierloiubogdan.petclinic.utils.SessionManager;

public class Main {
    public static void main(String[] args) {
        SessionManager.getSessionFactory();
        SessionManager.shutdown();


    }
}