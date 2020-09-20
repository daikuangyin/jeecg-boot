package org.jeecg.modules.recycle.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.recycle.entity.RecycleUserOrder;
import org.jeecg.modules.recycle.service.IRecycleUserOrderService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: recycle_user_order
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@RestController
@RequestMapping("/recycle/recycleUserOrder")
@Slf4j
public class RecycleUserOrderController extends JeecgController<RecycleUserOrder, IRecycleUserOrderService> {
	@Autowired
	private IRecycleUserOrderService recycleUserOrderService;
	
	/**
	 * 分页列表查询
	 *
	 * @param recycleUserOrder
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RecycleUserOrder recycleUserOrder,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RecycleUserOrder> queryWrapper = QueryGenerator.initQueryWrapper(recycleUserOrder, req.getParameterMap());
		Page<RecycleUserOrder> page = new Page<RecycleUserOrder>(pageNo, pageSize);
		IPage<RecycleUserOrder> pageList = recycleUserOrderService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param recycleUserOrder
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RecycleUserOrder recycleUserOrder) {
		recycleUserOrderService.save(recycleUserOrder);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param recycleUserOrder
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RecycleUserOrder recycleUserOrder) {
		recycleUserOrderService.updateById(recycleUserOrder);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		recycleUserOrderService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.recycleUserOrderService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		RecycleUserOrder recycleUserOrder = recycleUserOrderService.getById(id);
		if(recycleUserOrder==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(recycleUserOrder);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param recycleUserOrder
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RecycleUserOrder recycleUserOrder) {
        return super.exportXls(request, recycleUserOrder, RecycleUserOrder.class, "recycle_user_order");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, RecycleUserOrder.class);
    }

}
