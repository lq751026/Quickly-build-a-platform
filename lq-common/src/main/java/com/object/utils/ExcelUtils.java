package com.object.utils;

import com.alibaba.excel.EasyExcel;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 数据导入和导出的封装
 *
 * @param <T>
 */
public class ExcelUtils<T> {

    /**
     * 导出
     *
     * @param list      数据
     * @param response
     * @param sheetName
     * @return
     */
    public Q export(List<T> list, HttpServletResponse response, String sheetName, Class tClass) {
        try {
            EasyExcel.write(response.getOutputStream(), tClass).sheet(sheetName).doWrite(list);
            return Q.ok().put("data", response.getOutputStream());
        } catch (IOException e) {
            return Q.error().message("导出失败");
        }
    }


    /**
     * 导入
     *
     * @param file
     * @return
     */
   /* public Q imports(MultipartFile file) {
        // 读取Excel
        List<SysUtile> list = new ArrayList<>();
        try {
            EasyExcel.read(file.getInputStream(), TUserEntity.class, new AnalysisEventListener<SysUtile>() {
                // 每解析一行数据,该方法会被调用一次
                @Override
                public void invoke(SysUtile data, AnalysisContext analysisContext) {
                    //System.out.println("解析数据为:" + data.toString());
                    list.add(data);
                }

                // 全部解析完成被调用
                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                    //System.out.println("解析完成...");
                    // 可以将解析的数据保存到数据库
                }
            }).sheet().doRead();
        } catch (IOException e) {
            return Q.error().message("数据解析异常数据个有误！！！");
        }
        return Q.ok().put("data", list);
    }*/
}
