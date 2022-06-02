package com.object.module.lq.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  权限
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TPurview {
 private String id;
 private String name;
 private String purview;
 private List<TPurview> list;
 public TPurview(String name, String purview, String id){
  this.name=name;this.purview=purview;this.id=id;
 }
}
