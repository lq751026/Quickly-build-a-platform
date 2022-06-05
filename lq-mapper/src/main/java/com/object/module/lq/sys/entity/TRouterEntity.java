package com.object.module.lq.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @作者：小庆 🌤
 * @date: 2022/6/3
 * @email:1578442339@qq.com
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TRouterEntity {

    private Integer id;
    private String path;
    private String name;
    private TMetaEntity meta;
    private List<TRouterEntity> children;

    public TRouterEntity(Integer id, String path, String name) {
        this.id = id;
        this.path = path;
        this.name = name;
    }
}
