package cn.itcast.web.controller.cargo;

import cn.itcast.domain.cargo.CoPackingList;
import cn.itcast.domain.cargo.CoPackingListExample;
import cn.itcast.domain.cargo.Export;
import cn.itcast.domain.cargo.ExportExample;
import cn.itcast.service.cargo.ExportService;
import cn.itcast.service.cargo.CoPackingListService;
import cn.itcast.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping(value = "cargo/packing", name = "装箱单模块")
public class CoPackingListController extends BaseController {

    @Reference
    private CoPackingListService coPackingListService;

    @Reference
    private ExportService exportService;

    @RequestMapping(value = "list", name = "装箱单展示")
    public String findPage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "3") int pageSize) {
        CoPackingListExample coPackingListExample = new CoPackingListExample();
        CoPackingListExample.Criteria criteria = coPackingListExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        PageInfo page = coPackingListService.findAll(coPackingListExample, pageNum, pageSize);
        request.setAttribute("page", page);
        return "cargo/packing/packing-list";
    }

    @RequestMapping(value = "toPacking", name = "报运单申报装箱")
    public String toPacking(String id) {
        //获取所选报运单的lcno号
        String[] split = id.split(",");
        List list = new ArrayList();
        StringBuffer ids = new StringBuffer();
        for (int i = 0; i < split.length; i++) {
            String[] strings = split[i].split("/");
            list.add(strings[0]);
            ids.append(strings[0]).append(",");
        }
        String s = ids.substring(0, ids.length() - 1);
        ExportExample exportExample = new ExportExample();
        ExportExample.Criteria criteria = exportExample.createCriteria();
        criteria.andIdIn(list);
        StringBuffer buffer = new StringBuffer();
        List<Export> exports = exportService.findExample(exportExample);
        for (Export export : exports) {
            String exportLcno = export.getLcno();
            buffer.append(exportLcno).append(" ");
        }
        request.setAttribute("nos", buffer);
        request.setAttribute("id", s);
        return "cargo/packing/packing-add";
    }

    @RequestMapping(value = "toUpdate", name = "到装箱单修改")
    public String toUpdate(String id) {
        CoPackingList coPackingList = coPackingListService.findById(id);
        request.setAttribute("coPackingList", coPackingList);
        //获取装箱单下的报运单
        String exportIds = coPackingList.getExportIds();
        if (!StringUtils.isEmpty(exportIds)) {
            String[] exportArr = exportIds.split(",");
            ExportExample exportExample = new ExportExample();
            ExportExample.Criteria criteria = exportExample.createCriteria();
            criteria.andIdIn(Arrays.asList(exportArr));
            List<Export> exports = exportService.findExample(exportExample);
            request.setAttribute("exports", exports);
        }
        return "cargo/packing/packing-update";
    }

    @RequestMapping(value = "edit", name = "装箱单添加")
    public String edit(CoPackingList coPackingList) {
        coPackingList.setCreateBy(user.getEmail());
        coPackingList.setCreateDept(user.getDeptName());
        coPackingList.setCompanyId(companyId);
        if (StringUtils.isEmpty(coPackingList.getPackingListId())) {
            coPackingListService.save(coPackingList);
        } else {
            coPackingListService.update(coPackingList);
        }
        return "redirect:/cargo/packing/list.do";
    }

    @RequestMapping(value = "deleteExport", name = "报运单移除")
    public String deleteExport(String pid, String id) {
        CoPackingList coPackingList = coPackingListService.findById(pid);
        String exportIds = coPackingList.getExportIds();
        String[] exportArr = exportIds.split(",");
        ExportExample exportExample = new ExportExample();
        ExportExample.Criteria criteria = exportExample.createCriteria();
        criteria.andIdIn(Arrays.asList(exportArr));
        List<Export> exports = exportService.findExample(exportExample);
        Export exportDel = exportService.findById(id);
        Iterator<Export> iterator = exports.iterator();
        while (iterator.hasNext()) {
            Export export = iterator.next();
            if (exportDel.equals(export)) {
                iterator.remove();
            }
        }
        StringBuffer buffer = new StringBuffer();
        for (Export export : exports) {
            String exportId = export.getId();
            buffer.append(exportId).append(",");
        }
        coPackingList.setExportIds(new String(buffer));
        coPackingListService.update(coPackingList);
        return "redirect:/cargo/packing/toUpdate.do?id=" + pid;
    }

    @RequestMapping(value = "submit", name = "装箱单提交")
    public String submit(String id) {
        CoPackingList coPackingList = coPackingListService.findById(id);
        coPackingList.setState(1l);
        coPackingListService.update(coPackingList);
        return "redirect:/cargo/packing/list.do";
    }

    @RequestMapping(value = "cancel", name = "装箱单提交")
    public String cancel(String id) {
        CoPackingList coPackingList = coPackingListService.findById(id);
        if (coPackingList.getState() != 2) {
            coPackingList.setState(0l);
        }
        coPackingListService.update(coPackingList);
        return "redirect:/cargo/packing/list.do";
    }

    @RequestMapping(value = "delete", name = "装箱单删除")
    public String delete(String id) {
        coPackingListService.delete(id);
        return "redirect:/cargo/packing/list.do";
    }

    @RequestMapping(value = "toView", name = "装箱单查看")
    public String toView(String id) {
        CoPackingList coPackingList = coPackingListService.findById(id);
        request.setAttribute("coPackingList", coPackingList);
        //获取装箱单下的报运单
        String exportIds = coPackingList.getExportIds();
        if (!StringUtils.isEmpty(exportIds)) {
            String[] exportArr = exportIds.split(",");
            ExportExample exportExample = new ExportExample();
            ExportExample.Criteria criteria = exportExample.createCriteria();
            criteria.andIdIn(Arrays.asList(exportArr));
            List<Export> exports = exportService.findExample(exportExample);
            request.setAttribute("exports", exports);
        }
        return "cargo/packing/packing-view";
    }

    @RequestMapping(value = "toAdd", name = "新建装箱单")
    public String toAdd(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
        ExportExample exportExample = new ExportExample();
        ExportExample.Criteria criteria = exportExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        criteria.andStateEqualTo(2l);
        PageInfo page = exportService.findAll(exportExample, pageNum, pageSize);
        request.setAttribute("page", page);
        return "cargo/packing/packing-add";
    }
}
