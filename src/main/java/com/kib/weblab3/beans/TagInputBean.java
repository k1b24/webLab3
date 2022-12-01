package com.kib.weblab3.beans;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
@Data
public class TagInputBean {
    private String tags;
}
