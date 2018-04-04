package com.gof.adapter;

public class PSPAdapter2 implements IUSB{

    private PSP psp;
    
    public PSPAdapter2(PSP psp) {
        this.psp = psp;
    }
//    @Override
    public void charge() {
        psp.pspCharge();
    }

}
