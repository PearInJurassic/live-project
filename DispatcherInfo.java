/**
 * Project Name:live-project

 * File Name:DispatcherInfo.java
 * Package Name:
 * Date:ÏÂÎç1:01:16
 * Copyright (c) 2020, Doris All Rights Reserved.
 *
*/

/**
 * Description: <br/>
 * Date: ÏÂÎç1:01:16 <br/>
 * 
 * @author Doris
 * @version
 * @see
 */
public class DispatcherInfo {

    int dispatcherId;

    String uid;

    String uname;

    String uTel;

    int masknum;

    public DispatcherInfo(int dispatcherId, String uid, String uname, String uTel, int masknum) {

        this.dispatcherId = dispatcherId;
        this.uid = uid;
        this.uname = uname;
        this.uTel = uTel;
        this.masknum = masknum;
    }

    public int getMasknum() {
        return masknum;
    }

    public void setMasknum(int masknum) {
        this.masknum = masknum;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getuTel() {
        return uTel;
    }

    public void setuTel(String uTel) {
        this.uTel = uTel;
    }

    public int getDispatcherId() {
        return dispatcherId;
    }

    public void setDispatcherId(int dispatcherId) {
        this.dispatcherId = dispatcherId;
    }

}
