/**   
* @Title: TaskController.java 
* @Package com.tyyd.ywpt.tasklist 
* @Description:  
* @author wangyu   
* @date 2014-11-6 下午2:47:52 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.monitor.controller.tasklist;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.caucho.hessian.client.HessianProxyFactory;
import com.tyyd.ywpt.biz.dict.SysTypeEnum;
import com.tyyd.ywpt.biz.quartzschedule.TaskMonitorManager;
import com.tyyd.ywpt.biz.quartzschedule.TaskRefreshRegeditManager;
import com.tyyd.ywpt.biz.quartzschedule.dataobject.QuartzScheduleJobBO;
import com.tyyd.ywpt.dao.configration.db.dataobject.DbConfigDomain;
import com.tyyd.ywpt.dao.configration.host.dataobject.HostConfigDomain;
import com.tyyd.ywpt.dao.configration.schedule.dataobject.DeamonScheduleconfigDomain;
import com.tyyd.ywpt.dao.server.dataobject.ServerRegDomain;
import com.tyyd.ywpt.monitor.controller.tasklist.dto.TaskDTO;

/**
 * @author wangyu
 *
 */
@Controller
@RequestMapping("/monitor/task")
public class TaskController {

	@Resource
	private TaskMonitorManager taskMonitorManager;
	
	private static final String REG_TASKS = "/task/reg";
	private static final String RUNNING_TASKS = "/task/running";
	private static final String RUN_TASKS_INFO = "/task/runinfo";
	
	
	/**
	 * 已注册的任务
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/reg/tasklist", method = RequestMethod.GET)
	public ModelAndView regTasks(
			HttpServletRequest request, 
			HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		
		List<QuartzScheduleJobBO> noRegTask = taskMonitorManager.allJobs();
		
		model.addObject("regList", convertDTO(noRegTask));
		
		int total = 0;
		if(CollectionUtils.isNotEmpty(noRegTask)){
			total = noRegTask.size();
		}
		model.addObject("total", total);
		
		model.setViewName(REG_TASKS);
		
		return model;
	}
	
	

	/**
	 * 正在运行的任务
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/reg/running", method = RequestMethod.GET)
	public ModelAndView runningTasks(
			HttpServletRequest request, 
			HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		
		List<QuartzScheduleJobBO> list = taskMonitorManager.runningTasks();
		
		model.addObject("regList", convertDTO(list));
		
		int total = 0;
		if(CollectionUtils.isNotEmpty(list)){
			total = list.size();
		}
		model.addObject("total", total);
		
		model.setViewName(RUNNING_TASKS);
		return model;
		
	}
	
	/**
	 * 任务运行情况
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/reg/runinfo/{serverId}", method = RequestMethod.GET)
	public ModelAndView runTaskInfo(
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable("serverId") String serverId){
		
		ModelAndView model = new ModelAndView();
		List<QuartzScheduleJobBO> noRegTask = taskMonitorManager.runTaskInfo(serverId);
		
		model.addObject("regList", convertDTO(noRegTask));
		
		int total = 0;
		if(CollectionUtils.isNotEmpty(noRegTask)){
			total = noRegTask.size();
		}
		model.addObject("total", total);
		
		
		model.setViewName(RUN_TASKS_INFO);
		return model;
	}

	
	private List<TaskDTO> convertDTO(List<QuartzScheduleJobBO> jobs){
		List<TaskDTO> result = new ArrayList<TaskDTO>();
		if(CollectionUtils.isNotEmpty(jobs)){
			for(QuartzScheduleJobBO job : jobs){
				TaskDTO dto = new TaskDTO();
				try {
					BeanUtils.copyProperties(dto, job);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				result.add(dto);
			}
		}
		return result;
	}


}
