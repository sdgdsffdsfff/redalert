/**   
* @Title: DictDefDAOTest.java 
* @Package test.com.tyyd.ywpt.dao.dict 
* @Description:  
* @author wangyu   
* @date 2014-6-23 上午9:06:33 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package test.com.tyyd.ywpt.dao.dict;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.com.tyyd.ywpt.dao.BaseDAOTest;

import com.tyyd.ywpt.dao.dict.DictDefDAO;
import com.tyyd.ywpt.dao.dict.dataobject.DictDefDomain;

/**
 * @author wangyu
 *
 */
public class DictDefDAOTest extends BaseDAOTest{

	@Resource
	private DictDefDAO dictDefDAO;
	
	
	@Test
	public void listDictDef(){
		List<DictDefDomain> list = dictDefDAO.listDictDef();
		for(DictDefDomain domain : list){
			System.out.println(domain);
		}
	}
	
}
