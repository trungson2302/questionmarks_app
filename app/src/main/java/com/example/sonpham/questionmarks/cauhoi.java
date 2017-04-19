package com.example.sonpham.questionmarks;

/**
 * Created by Son Pham on 4/12/2017.
 */

public class cauhoi {
    private int _id;
    private String cau_hoi,dap_an;

    public cauhoi(){
        _id=0;
        cau_hoi="";
        dap_an="";
    }
    public cauhoi(int id, String cauhoi,String dapan){
        _id=id;
        cau_hoi=cauhoi;

        dap_an=dapan;
    }

    public int getID(){return _id;}

public void setID(int id){ _id=id;}

    public String getCauhoi(){return cau_hoi;}

    public void setCauhoi(String cauhoi){ cau_hoi=cauhoi;};

    public String getDap_an(){return dap_an;}

    public void setDap_an(String dapan){dap_an=dapan;}
}
