package com.leyou.goods.service.impl;

import com.leyou.common.util.ThreadUtils;
import com.leyou.goods.service.IGoodsHtmlService;
import com.leyou.goods.service.IGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@Service
public class GoodsHtmlServiceImpl  implements IGoodsHtmlService {
    @Autowired
    private TemplateEngine   engine;

    @Autowired
    private IGoodsService goodsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsHtmlServiceImpl.class);

    @Override
    public void createHtml(Long spuId) {
        //初始化thymeleaf上下文
        Context context = new Context();
        //设置数据模型
        context.setVariables(this.goodsService.loadData(spuId));



        PrintWriter printWriter = null;
        try {
            //静态文件生成，存到服务器本地
            File file = new File("D:\\Program Files (x86)\\nginx-1.14.0\\nginx-1.14.0\\html\\item\\"+spuId+".html");
            printWriter = new PrintWriter(file);
            this.engine.process("item",context,printWriter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }

    }

    /**
     * 新建线程处理页面静态化
     * @param spuId
     */
    public void asyncExcute(Long spuId) {
        ThreadUtils.execute(()->createHtml(spuId));
        /*ThreadUtils.execute(new Runnable() {
            @Override
            public void run() {
                createHtml(spuId);
            }
        });*/
    }

    @Override
    public void deleteHtml(Long id) {
        File file = new File("D:\\Program Files (x86)\\nginx-1.14.0\\nginx-1.14.0\\html\\item\\" + id + ".html");
        //存在则删除
        file.deleteOnExit();
    }
}
