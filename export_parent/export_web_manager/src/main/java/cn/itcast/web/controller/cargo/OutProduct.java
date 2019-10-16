package cn.itcast.web.controller.cargo;

import cn.itcast.service.cargo.ContractProductService;
import cn.itcast.utils.DownloadUtil;
import cn.itcast.vo.ContractProductVo;
import cn.itcast.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/cargo/contract")
public class OutProduct extends BaseController{

    @Reference
    private ContractProductService contractProductService;

    @RequestMapping(value = "/print")
    public String print(){
        // 到出货表界面
        return "/cargo/print/contract-print";
    }

    //出货表
    /*
    *  自己设置样式 过于频繁和繁琐 自己控制excel的样式
    *       企业开发：会提供excel的模板 导入数据即可
    *
    * */
    @RequestMapping(value = "/printExcel")
    public void printExcel(String inputDate) throws IOException {
        // 根据船期去数据库查询数据 List<对象>
        List<ContractProductVo> voList=contractProductService.findByInputDate(companyId,inputDate);
        // 使用poi将数据写入excel
        // 创建工作薄
        Workbook wb=new SXSSFWorkbook();
        // 创建工作表
        Sheet st = wb.createSheet();
        // 统一设置列宽
        st.setColumnWidth(1,26*256);
        st.setColumnWidth(2,12*256);
        st.setColumnWidth(3,29*256);
        st.setColumnWidth(4,12*256);
        st.setColumnWidth(5,15*256);
        st.setColumnWidth(6,10*256);
        st.setColumnWidth(7,10*256);
        st.setColumnWidth(8,8*256);
        // 基本参数设置
        int rowIndex=0;
        Row row=null;
        Cell cell=null;
        // 创建行
        row=st.createRow(rowIndex++); //0   0+1
            //设置行高
        row.setHeightInPoints(36f);
            // 创建单元格
        cell=row.createCell(1);
            // 放入内容  2015-01 2015-11
        String value = inputDate.replaceAll("-0", "-").replaceAll("-", "年");
        cell.setCellValue(value+"月份出货表");
           // 设置单元格的样式
        cell.setCellStyle(bigTitle(wb));
          // 合并单元格
        st.addMergedRegion(new CellRangeAddress(0,0,1,8));

        // 创建第二行的小标题
        row=st.createRow(rowIndex++); //1  1+1
        row.setHeightInPoints(26.25f);
        // 创建单元格
        String[] arr={"","客户","订单号","货号","数量","工厂","工厂交期","船期","贸易条款"};
        for(int i=1;i<arr.length;i++){
            cell=row.createCell(i);
            cell.setCellValue(arr[i]);
            cell.setCellStyle(title(wb));
        }
        // 创建所有的数据行
        for (ContractProductVo vo : voList) {

            for(int i=0;i<5000;i++){
            // 创建行
            row=st.createRow(rowIndex++);
            // 行高
            row.setHeightInPoints(24f);
            // 单元格
            cell=row.createCell(1);
            // 设置内容
            cell.setCellValue(vo.getCustomName());
            // 设置样式
            //cell.setCellStyle(text(wb));


            // 单元格
            cell=row.createCell(2);
            // 设置内容
            cell.setCellValue(vo.getContractNo());
            // 设置样式
                //cell.setCellStyle(text(wb));


            // 单元格
            cell=row.createCell(3);
            // 设置内容
            cell.setCellValue(vo.getProductNo());
            // 设置样式
                //cell.setCellStyle(text(wb));

            // 单元格
            cell=row.createCell(4);
            // 设置内容
            cell.setCellValue(vo.getCnumber());
            // 设置样式
                //cell.setCellStyle(text(wb));

            // 单元格
            cell=row.createCell(5);
            // 设置内容
            cell.setCellValue(vo.getFactoryName());
            // 设置样式
                //cell.setCellStyle(text(wb));


            // 单元格
            cell=row.createCell(6);
            // 设置内容
            cell.setCellValue(vo.getDeliveryPeriod());
            // 设置样式
                //cell.setCellStyle(text(wb));

            // 单元格
            cell=row.createCell(7);
            // 设置内容
            cell.setCellValue(vo.getShipTime());
            // 设置样式
                //cell.setCellStyle(text(wb));

            // 单元格
            cell=row.createCell(8);
            // 设置内容
            cell.setCellValue(vo.getTradeTerms());
            // 设置样式
                //cell.setCellStyle(text(wb));
            }
        }
        // 提供excel下载给用户
        // 工具类
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        new DownloadUtil().download(os,response,"出货表.xlsx");
    }


    //大标题的样式
    public CellStyle bigTitle(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)16);
        font.setBold(true);//字体加粗
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);				//横向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);		//纵向居中
        return style;
    }

    //小标题的样式
    public CellStyle title(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short)12);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);				//横向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);		//纵向居中
        style.setBorderTop(BorderStyle.THIN);						//上细线
        style.setBorderBottom(BorderStyle.THIN);					//下细线
        style.setBorderLeft(BorderStyle.THIN);						//左细线
        style.setBorderRight(BorderStyle.THIN);						//右细线
        return style;
    }

    //文字样式
    public CellStyle text(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short)10);

        style.setFont(font);

        style.setAlignment(HorizontalAlignment.LEFT);				//横向居左
        style.setVerticalAlignment(VerticalAlignment.CENTER);		//纵向居中
        style.setBorderTop(BorderStyle.THIN);						//上细线
        style.setBorderBottom(BorderStyle.THIN);					//下细线
        style.setBorderLeft(BorderStyle.THIN);						//左细线
        style.setBorderRight(BorderStyle.THIN);						//右细线

        return style;
    }


    /*@RequestMapping(value = "/printExcel")
    public void printExcel(String inputDate) throws IOException {
        // 根据船期去数据库查询数据 List<对象>
        List<ContractProductVo> voList=contractProductService.findByInputDate(companyId,inputDate);
        // 使用poi将数据写入excel(模板方式)
        // 读取模板工作薄
        String path=session.getServletContext().getRealPath("/")+"/make/xlsprint/tOUTPRODUCT.xlsx";
        Workbook wb=new XSSFWorkbook(path);
        // 获取工作页
        Sheet st = wb.getSheetAt(0);
        // 设置基本数据
        int rowIndex=0;
        Row row=null;
        Cell cell=null;
        // 第一行
        row=st.getRow(rowIndex++);
        // 写内容
        cell=row.getCell(1);
        String value = inputDate.replaceAll("-0", "-").replaceAll("-", "年");
        cell.setCellValue(value+"月份出货表");
        // 略过第二行
        rowIndex++;  //+1 第三行
        // 第三行
        CellStyle[] css=new CellStyle[9];
        for(int i=1;i<css.length;i++){
            // 获取样式
            row=st.getRow(rowIndex);
            cell=row.getCell(i);
            css[i]=cell.getCellStyle();
        }

        // 写内容
        for (ContractProductVo vo : voList) {

            for(int i=0;i<3000;i++){
            row=st.createRow(rowIndex++);
            // 单元格
            cell=row.createCell(1);
            // 设置内容
            cell.setCellValue(vo.getCustomName());
            // 设置样式
            cell.setCellStyle(css[1]);


            // 单元格
            cell=row.createCell(2);
            // 设置内容
            cell.setCellValue(vo.getContractNo());
            // 设置样式
            cell.setCellStyle(css[2]);


            // 单元格
            cell=row.createCell(3);
            // 设置内容
            cell.setCellValue(vo.getProductNo());
            // 设置样式
            cell.setCellStyle(css[3]);

            // 单元格
            cell=row.createCell(4);
            // 设置内容
            cell.setCellValue(vo.getCnumber());
            // 设置样式
            cell.setCellStyle(css[4]);

            // 单元格
            cell=row.createCell(5);
            // 设置内容
            cell.setCellValue(vo.getFactoryName());
            // 设置样式
            cell.setCellStyle(css[5]);


            // 单元格
            cell=row.createCell(6);
            // 设置内容
            cell.setCellValue(vo.getDeliveryPeriod());
            // 设置样式
            cell.setCellStyle(css[6]);

            // 单元格
            cell=row.createCell(7);
            // 设置内容
            cell.setCellValue(vo.getShipTime());
            // 设置样式
            cell.setCellStyle(css[7]);

            // 单元格
            cell=row.createCell(8);
            // 设置内容
            cell.setCellValue(vo.getTradeTerms());
            // 设置样式
            cell.setCellStyle(css[8]);
            }
        }
        // 提供excel下载给用户
        // 工具类
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        new DownloadUtil().download(os,response,"出货表.xlsx");
    }*/


}
