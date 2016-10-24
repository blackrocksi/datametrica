package br.com.datametrica.net.model;

import java.io.Serializable;

public class DoneUnder60Seconds implements Serializable {
	private static final long serialVersionUID = -5612027287087868864L;
	private String supervisorName;
	private String agentName;
	private String agentCode;
	private Integer total;

	public DoneUnder60Seconds(String supervisorName, String agentName, String agentCode, Integer total) {
		this.supervisorName = supervisorName;
		this.agentName = agentName;
		this.agentCode = agentCode;
		this.total = total;
	}

	public String getSupervisorName() {
		return this.supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getAgentName() {
		return this.agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentCode() {
		return this.agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}