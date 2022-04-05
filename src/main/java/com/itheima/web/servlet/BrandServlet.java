package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用Service查询
        List<Brand> brands = brandService.selectAll();
        //2.转为JSON
        String jsonString = JSON.toJSONString(brands);
        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.接受品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //转换为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //2.调用Service插入
        brandService.add(brand);

        //3.响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.接受品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //转换为int[]
        int[] ids = JSON.parseObject(params, int[].class);

        //2.调用Service批量删除
        brandService.deleteByIds(ids);

        //3.响应成功的标识
        response.getWriter().write("success");
    }
}
