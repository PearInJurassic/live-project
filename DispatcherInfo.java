/**
 * Project Name:live-project

 * File Name:DispatcherInfo.java
 * Package Name:
 * Date:обнГ1:01:16
 * Copyright (c) 2020, Doris All Rights Reserved.
 *
*/

/**
 * Description: <br/>
 * Date: обнГ1:01:16 <br/>
 * 
 * @author Doris
 * @version
 * @see
 */
public class DispatcherInfo {
    int dispatcherId;

    boolean dispatcher;

    DispatcherHandler dh;

    int masknum = 0;

    public DispatcherInfo(DispatcherHandler dh) {
        this.dh = dh;

    }

    public int getDispatcherId() {
        return dispatcherId;
    }

    public void setDispatcherId(int dispatcherId) {
        this.dispatcherId = dispatcherId;
    }

    public boolean isDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(boolean dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void isDrawed(int ordernum) {
        if (dh.hs.contains(dispatcherId)) {
            dispatcher = true;
            masknum = ordernum;
        }
    }

}
