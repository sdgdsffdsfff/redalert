package com.tyyd.ywpt.dao.dict.impl;

import java.util.List;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.dict.DictDefDAO;
import com.tyyd.ywpt.dao.dict.dataobject.DictDefDomain;

@SuppressWarnings("rawtypes")
public class DictDefDAOImpl extends TyydBaseDAO implements DictDefDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.dict.dataobject.DictDefDomain";
	
	@Override
	public List<DictDefDomain> listDictDef() {
		return this.getSqlSessionTemplate().selectList(context_space +".list_dict_def");
	}

}
