package com.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.b2c.mapper.TbItemMapper;
import com.b2c.pojo.TbItem;
import com.b2c.pojo.TbItemExample;
import com.b2c.service.ItemService;
import com.b2c.service.imp.ItemServiceImp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TestPageHelper {
    //创建一个spring容器
    //从spring容器中获得Mapper的代理对象
    static ApplicationContext applicationContext = null;
    static {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
    }
    
    @Test
    public void testPageHelper() {
        TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
        //执行查询，并分页
        TbItemExample example = new TbItemExample();
        //分页处理
        PageHelper.startPage(2, 10);
        List<TbItem> list = mapper.selectByExample(example);
        //取商品列表
        for (TbItem tbItem : list) {
            System.out.println(tbItem.getTitle());
        }
        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        System.out.println("共有商品："+ total);
        
    }
}
