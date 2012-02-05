package com.bis.domain;

public enum AlertTypeCode {

   SUA(1),SUS(2),SNR(3),HPNR(4),HBNG(5),VPND(6),VBNG(7);

    private int code;

    AlertTypeCode(int c) {
        this.code = c;
    }

    public int getCode() {
        return code;
    }

    public static String  getNameByCode(int code){
        for (AlertTypeCode alertTypeCode : AlertTypeCode.values()) {
            if(alertTypeCode.getCode()==code){
                return alertTypeCode.name();
            }
        }
        throw new RuntimeException("Invalid code");
    }
}
// SUA('1'), - Stock Un-availability Alert
// SUS('2'), - Stock Un-sold Alert
// SNR('3'), - Stock not-returned Alert
// HPNR('4'),- Hawker payment not received
// HBNG('5'),- Hawker bill not generated
// VPND('6'),- Vendor payment not done
// VBNG('7') - Vendor bill not generated