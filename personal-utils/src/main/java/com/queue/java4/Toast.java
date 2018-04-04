package com.queue.java4;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by q3178 on 2017/6/19.
 */
public class Toast {

    public enum Status { DRY,BUTTERD,JAMMED}
    private  Status status = Status.DRY;
    private  final int id;

    public Toast(int id) {
        this.id = id;
    }

    public void butter(){
        status = Status.BUTTERD;
    }

    public  void jam(){
        status = Status.JAMMED;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
