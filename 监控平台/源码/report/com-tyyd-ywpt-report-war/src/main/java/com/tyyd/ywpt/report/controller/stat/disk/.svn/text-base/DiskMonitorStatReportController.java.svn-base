/**   
* @Title: DiskMonitorStatReportController.java 
* @Package com.tyyd.ywpt.report.controller.stat.disk 
* @Description:  
* @author wangyu   
* @date 2015-6-2 上午11:33:19 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.report.controller.stat.disk;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;
import com.tyyd.ywpt.dao.core.collect.disk.dataobject.DiskMonitorDomain;
import com.tyyd.ywpt.report.biz.device.DiskMonitorReportManager;
import com.tyyd.ywpt.report.biz.quota.dto.multi.MultipleGeneralReportDataBO;
import com.tyyd.ywpt.report.biz.quota.dto.pie.PieReportOrgFormatDataBO;

/**
 * @author wangyu
 *
 */
@Controller
@RequestMapping("/report/stat/disk")
public class DiskMonitorStatReportController {

	private static final String VIEWS = "/report/stat/disk/disk_stat";
	private static final String ERROR_VIEWS = "/report/stat/disk/disk_error";
	
	@Resource
	private DiskMonitorReportManager diskMonitorReportManager;
	
	
	@RequestMapping(value = "/view/{hostId}", method = RequestMethod.GET)
	public ModelAndView views(@PathVariable("hostId") String hostId,
							HttpServletRequest request, 
							HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		
		HostConfigDomain hostConfigDomain = diskMonitorReportManager.getHostInfo(hostId);
		DiskMonitorDomain diskMonitorDomain = diskMonitorReportManager.totalDiskInfo(hostId);
		if(hostConfigDomain == null || diskMonitorDomain == null){
			 model.setViewName(ERROR_VIEWS);
			 return model;
		}
		
		
		model.addObject("hostConfigDomain", hostConfigDomain);
		model.addObject("diskUsed", diskMonitorDomain.getUsed().toString());
		model.addObject("diskRemain", diskMonitorDomain.getRemain().toString());
		model.addObject("diskusedPercent", diskMonitorDomain.getUsedPercent().toString());
		model.setViewName(VIEWS);
		return model;
	} 

	
	/**
	 * Disk Partition
	 * @param hostId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/disk/info/{hostId}", method = RequestMethod.GET)
	public @ResponseBody List<DiskMonitorDomain[]>  listDiskPartition(
			@PathVariable("hostId") String hostId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		List<DiskMonitorDomain[]> list = diskMonitorReportManager.listDiskMonitor(hostId);
		
		return list;
	}
	
	
	/**
	 * total Disk
	 * @param hostId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/disk/total/{hostId}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO totalDisk(
			@PathVariable("hostId") String hostId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = diskMonitorReportManager.totalDiskReport(hostId);
		
		return multipleGeneralReportDataBO;
	}
	
	
	
	/**
	 * Delta Disk
	 * @param hostId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/disk/delta/{hostId}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO deltaDisk(
			@PathVariable("hostId") String hostId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = diskMonitorReportManager.deltaDiskReport(hostId);
		
		return multipleGeneralReportDataBO;
	}
	
	/**
	 * Delta Disk
	 * @param hostId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/disk/delta/detail/{hostId}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO deltaDiskByName(
			@PathVariable("hostId") String hostId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		String diskName = request.getParameter("diskname");
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = diskMonitorReportManager.deltaDiskReportByDisk(hostId, diskName);
		
		return multipleGeneralReportDataBO;
	}
	
	/**
	 * sum Disk By Disk
	 * @param hostId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/disk/total/detail/{hostId}", method = RequestMethod.GET)
	public @ResponseBody MultipleGeneralReportDataBO sumDiskByName(
			@PathVariable("hostId") String hostId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		String diskName = request.getParameter("diskname");
		MultipleGeneralReportDataBO multipleGeneralReportDataBO = diskMonitorReportManager.totalDiskReportByDisk(hostId, diskName);
		
		return multipleGeneralReportDataBO;
	}
	
	
	/**
	 * total Disk
	 * @param hostId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data/disk/pie/{hostId}", method = RequestMethod.GET)
	public @ResponseBody PieReportOrgFormatDataBO diskPie(
			@PathVariable("hostId") String hostId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		PieReportOrgFormatDataBO result = diskMonitorReportManager.theLastDiskInfo(hostId);
		
		return result;
	}
	
}
