package com.tyyd.ywpt.dao.server.impl;

import java.util.List;

import com.tyyd.ywpt.dao.TyydBaseDAO;
import com.tyyd.ywpt.dao.server.ServerRegDAO;
import com.tyyd.ywpt.dao.server.dataobject.ServerRegDomain;

@SuppressWarnings("rawtypes")
public class ServerRegDAOImpl extends TyydBaseDAO implements ServerRegDAO {

	private final static String context_space = "com.tyyd.ywpt.dao.server.dataobject.ServerRegDomain";
	
	@Override
	public void addServerReg(ServerRegDomain domain) {
		this.getSqlSessionTemplate().insert(context_space +".add_server_reg", domain);
	}

	@Override
	public void updateServerRegInfo(ServerRegDomain domain) {
		this.getSqlSessionTemplate().update(context_space +".update_server_reg_info", domain);
	}

	@Override
	public void deleteServerReg(String serverId) {
		this.getSqlSessionTemplate().delete(context_space +".delete_server_reg", serverId);
	}

	@Override
	public List<ServerRegDomain> listServerReg() {
		return this.getSqlSessionTemplate().selectList(context_space +".list_server_reg");
	}

	@Override
	public ServerRegDomain getServerRegById(String serverId) {
		return this.getSqlSessionTemplate().selectOne(context_space +".get_server_reg_by_id",serverId);
	}

	@Override
	public ServerRegDomain getServerRegByName(String serverName) {
		return this.getSqlSessionTemplate().selectOne(context_space +".get_server_reg_by_name",serverName);
	}

}
