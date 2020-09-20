package org.jeecg.modules.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.recycle.entity.RecyclePaymentTerm;
import org.jeecg.modules.recycle.service.IRecyclePaymentTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: recycle_payment_term
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@RestController
@RequestMapping("/recycle/recyclePaymentTerm")
@Slf4j
public class RecyclePaymentTermController extends JeecgController<RecyclePaymentTerm, IRecyclePaymentTermService> {
	@Autowired
	private IRecyclePaymentTermService recyclePaymentTermService;
	
	/**
	 * 分页列表查询
	 *
	 * @param recyclePaymentTerm
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RecyclePaymentTerm recyclePaymentTerm,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RecyclePaymentTerm> queryWrapper = QueryGenerator.initQueryWrapper(recyclePaymentTerm, req.getParameterMap());
		Page<RecyclePaymentTerm> page = new Page<RecyclePaymentTerm>(pageNo, pageSize);
		IPage<RecyclePaymentTerm> pageList = recyclePaymentTermService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param recyclePaymentTerm
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RecyclePaymentTerm recyclePaymentTerm) {
		recyclePaymentTermService.save(recyclePaymentTerm);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param recyclePaymentTerm
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RecyclePaymentTerm recyclePaymentTerm) {
		recyclePaymentTermService.updateById(recyclePaymentTerm);
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
		recyclePaymentTermService.removeById(id);
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
		this.recyclePaymentTermService.removeByIds(Arrays.asList(ids.split(",")));
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
		RecyclePaymentTerm recyclePaymentTerm = recyclePaymentTermService.getById(id);
		if(recyclePaymentTerm==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(recyclePaymentTerm);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param recyclePaymentTerm
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RecyclePaymentTerm recyclePaymentTerm) {
        return super.exportXls(request, recyclePaymentTerm, RecyclePaymentTerm.class, "recycle_payment_term");
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
        return super.importExcel(request, response, RecyclePaymentTerm.class);
    }

}
