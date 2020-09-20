package org.jeecg.modules.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.recycle.entity.RecycleRecord;
import org.jeecg.modules.recycle.service.IRecycleRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: recycle_record
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@RestController
@RequestMapping("/recycle/recycleRecord")
@Slf4j
public class RecycleRecordController extends JeecgController<RecycleRecord, IRecycleRecordService> {
	@Autowired
	private IRecycleRecordService recycleRecordService;
	
	/**
	 * 分页列表查询
	 *
	 * @param recycleRecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RecycleRecord recycleRecord,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RecycleRecord> queryWrapper = QueryGenerator.initQueryWrapper(recycleRecord, req.getParameterMap());
		Page<RecycleRecord> page = new Page<RecycleRecord>(pageNo, pageSize);
		IPage<RecycleRecord> pageList = recycleRecordService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param recycleRecord
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RecycleRecord recycleRecord) {
		recycleRecordService.save(recycleRecord);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param recycleRecord
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RecycleRecord recycleRecord) {
		recycleRecordService.updateById(recycleRecord);
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
		recycleRecordService.removeById(id);
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
		this.recycleRecordService.removeByIds(Arrays.asList(ids.split(",")));
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
		RecycleRecord recycleRecord = recycleRecordService.getById(id);
		if(recycleRecord==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(recycleRecord);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param recycleRecord
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RecycleRecord recycleRecord) {
        return super.exportXls(request, recycleRecord, RecycleRecord.class, "recycle_record");
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
        return super.importExcel(request, response, RecycleRecord.class);
    }

}
