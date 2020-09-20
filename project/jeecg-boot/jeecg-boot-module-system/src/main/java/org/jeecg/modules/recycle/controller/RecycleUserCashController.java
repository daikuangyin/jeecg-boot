package org.jeecg.modules.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.recycle.entity.RecycleUserCash;
import org.jeecg.modules.recycle.service.IRecycleUserCashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: recycle_user_cash
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@RestController
@RequestMapping("/recycle/recycleUserCash")
@Slf4j
public class RecycleUserCashController extends JeecgController<RecycleUserCash, IRecycleUserCashService> {
	@Autowired
	private IRecycleUserCashService recycleUserCashService;
	
	/**
	 * 分页列表查询
	 *
	 * @param recycleUserCash
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RecycleUserCash recycleUserCash,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RecycleUserCash> queryWrapper = QueryGenerator.initQueryWrapper(recycleUserCash, req.getParameterMap());
		Page<RecycleUserCash> page = new Page<RecycleUserCash>(pageNo, pageSize);
		IPage<RecycleUserCash> pageList = recycleUserCashService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param recycleUserCash
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RecycleUserCash recycleUserCash) {
		recycleUserCashService.save(recycleUserCash);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param recycleUserCash
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RecycleUserCash recycleUserCash) {
		recycleUserCashService.updateById(recycleUserCash);
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
		recycleUserCashService.removeById(id);
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
		this.recycleUserCashService.removeByIds(Arrays.asList(ids.split(",")));
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
		RecycleUserCash recycleUserCash = recycleUserCashService.getById(id);
		if(recycleUserCash==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(recycleUserCash);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param recycleUserCash
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RecycleUserCash recycleUserCash) {
        return super.exportXls(request, recycleUserCash, RecycleUserCash.class, "recycle_user_cash");
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
        return super.importExcel(request, response, RecycleUserCash.class);
    }

}
