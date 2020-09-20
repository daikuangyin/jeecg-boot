package org.jeecg.modules.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.recycle.entity.RecyclePlanOrder;
import org.jeecg.modules.recycle.service.IRecyclePlanOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: recycle_plan_order
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@RestController
@RequestMapping("/recycle/recyclePlanOrder")
@Slf4j
public class RecyclePlanOrderController extends JeecgController<RecyclePlanOrder, IRecyclePlanOrderService> {
	@Autowired
	private IRecyclePlanOrderService recyclePlanOrderService;
	
	/**
	 * 分页列表查询
	 *
	 * @param recyclePlanOrder
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RecyclePlanOrder recyclePlanOrder,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RecyclePlanOrder> queryWrapper = QueryGenerator.initQueryWrapper(recyclePlanOrder, req.getParameterMap());
		Page<RecyclePlanOrder> page = new Page<RecyclePlanOrder>(pageNo, pageSize);
		IPage<RecyclePlanOrder> pageList = recyclePlanOrderService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param recyclePlanOrder
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RecyclePlanOrder recyclePlanOrder) {
		recyclePlanOrderService.save(recyclePlanOrder);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param recyclePlanOrder
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RecyclePlanOrder recyclePlanOrder) {
		recyclePlanOrderService.updateById(recyclePlanOrder);
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
		recyclePlanOrderService.removeById(id);
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
		this.recyclePlanOrderService.removeByIds(Arrays.asList(ids.split(",")));
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
		RecyclePlanOrder recyclePlanOrder = recyclePlanOrderService.getById(id);
		if(recyclePlanOrder==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(recyclePlanOrder);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param recyclePlanOrder
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RecyclePlanOrder recyclePlanOrder) {
        return super.exportXls(request, recyclePlanOrder, RecyclePlanOrder.class, "recycle_plan_order");
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
        return super.importExcel(request, response, RecyclePlanOrder.class);
    }

}
