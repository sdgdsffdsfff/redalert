/**   
* @Title: IdentifyGenDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.base 
* @Description:  
* @author wangyu   
* @date 2014-6-19 上午10:31:50 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.base;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.base.idgen.IdentifyGenDAO;

/**
 * @author wangyu
 *
 */
public class IdentifyGenDAOTest extends BaseDAOTest{

	@Resource
	private IdentifyGenDAO identifyGenDAO;
	
	@Test
	public void genIdTest(){
		String result = identifyGenDAO.genId("b_alert_record");
		System.out.println(result);
	}
}
