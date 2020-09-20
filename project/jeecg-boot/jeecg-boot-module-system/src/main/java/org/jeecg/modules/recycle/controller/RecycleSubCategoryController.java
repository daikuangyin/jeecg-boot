package org.jeecg.modules.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.recycle.entity.RecycleSubCategory;
import org.jeecg.modules.recycle.service.IRecycleSubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: recycle_sub_category
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@RestController
@RequestMapping("/recycle/recycleSubCategory")
@Slf4j
public class RecycleSubCategoryController extends JeecgController<RecycleSubCategory, IRecycleSubCategoryService> {
	@Autowired
	private IRecycleSubCategoryService recycleSubCategoryService;
	
	/**
	 * 分页列表查询
	 *
	 * @param recycleSubCategory
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RecycleSubCategory recycleSubCategory,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RecycleSubCategory> queryWrapper = QueryGenerator.initQueryWrapper(recycleSubCategory, req.getParameterMap());
		Page<RecycleSubCategory> page = new Page<RecycleSubCategory>(pageNo, pageSize);
		IPage<RecycleSubCategory> pageList = recycleSubCategoryService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param recycleSubCategory
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RecycleSubCategory recycleSubCategory) {
		recycleSubCategoryService.save(recycleSubCategory);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param recycleSubCategory
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RecycleSubCategory recycleSubCategory) {
		recycleSubCategoryService.updateById(recycleSubCategory);
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
		recycleSubCategoryService.removeById(id);
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
		this.recycleSubCategoryService.removeByIds(Arrays.asList(ids.split(",")));
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
		RecycleSubCategory recycleSubCategory = recycleSubCategoryService.getById(id);
		if(recycleSubCategory==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(recycleSubCategory);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param recycleSubCategory
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RecycleSubCategory recycleSubCategory) {
        return super.exportXls(request, recycleSubCategory, RecycleSubCategory.class, "recycle_sub_category");
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
        return super.importExcel(request, response, RecycleSubCategory.class);
    }

}
