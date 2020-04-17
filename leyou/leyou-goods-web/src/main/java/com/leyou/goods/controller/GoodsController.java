package com.leyou.goods.controller;

import com.leyou.goods.service.IGoodsHtmlService;
import com.leyou.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IGoodsHtmlService goodsHtmlService;

    @GetMapping("item/{id}.html")
    public String toItemPage(@PathVariable("id")Long id, Model model) {
        Map<String, Object> map = this.goodsService.loadData(id);
        model.addAllAttributes(map);
        //页面静态化
        this.goodsHtmlService.asyncExcute(id);
        return "item";
    }
}
