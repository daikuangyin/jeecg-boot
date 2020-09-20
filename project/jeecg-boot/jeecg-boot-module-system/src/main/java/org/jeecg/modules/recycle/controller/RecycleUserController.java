package org.jeecg.modules.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.recycle.entity.RecycleUser;
import org.jeecg.modules.recycle.service.IRecycleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: recycle_user
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@RestController
@RequestMapping("/recycle/recycleUser")
@Slf4j
public class RecycleUserController extends JeecgController<RecycleUser, IRecycleUserService> {
	@Autowired
	private IRecycleUserService recycleUserService;
	
	/**
	 * 分页列表查询
	 *
	 * @param recycleUser
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RecycleUser recycleUser,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RecycleUser> queryWrapper = QueryGenerator.initQueryWrapper(recycleUser, req.getParameterMap());
		Page<RecycleUser> page = new Page<RecycleUser>(pageNo, pageSize);
		IPage<RecycleUser> pageList = recycleUserService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param recycleUser
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RecycleUser recycleUser) {
		recycleUserService.save(recycleUser);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param recycleUser
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RecycleUser recycleUser) {
		recycleUserService.updateById(recycleUser);
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
		recycleUserService.removeById(id);
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
		this.recycleUserService.removeByIds(Arrays.asList(ids.split(",")));
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
		RecycleUser recycleUser = recycleUserService.getById(id);
		if(recycleUser==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(recycleUser);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param recycleUser
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RecycleUser recycleUser) {
        return super.exportXls(request, recycleUser, RecycleUser.class, "recycle_user");
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
        return super.importExcel(request, response, RecycleUser.class);
    }

}
