package br.com.datametrica.net.model;

import java.io.Serializable;
import java.util.Comparator;

public class DoneByAgent implements Serializable {
	private static final long serialVersionUID = -9107204446004290807L;
	private String supervisorName;
	private String agentCode;
	private String agentName;
	private String agentLastName;
	private String classification;
	private Integer pv1 = 0;
	private Integer pv2 = 0;
	private Integer pv3 = 0;
	private Integer canceled = 0;
	private Integer total = 0;

	public void somaTipificacao(String tipificacao) {
		if ("CANCELADO".equals(tipificacao)) {
			this.canceled = this.canceled + 1;
		} else if ("PV1".equals(tipificacao)) {
			this.pv1 = this.pv1 + 1;
		} else if ("PV2".equals(tipificacao)) {
			this.pv2 = this.pv2 + 1;
		} else if ("PV3".equals(tipificacao)) {
			this.pv3 = this.pv3 + 1;
		}
	}

	public void totalizaTipificacao() {
		this.total = this.canceled + this.pv1 + this.pv2 + this.pv3;
	}

	public DoneByAgent(String supervisor, String agentCode, String agentName, String agentLastName, String classification) {
		this.supervisorName = supervisor;
		this.agentCode = agentCode;
		this.agentName = agentName;
		this.agentLastName = agentLastName;
		this.classification = classification;
	}

	public DoneByAgent() {
	}

	public class Ordenator implements Comparator<DoneByAgent> {
		@Override
		public int compare(DoneByAgent doneByAgent1, DoneByAgent doneByAgent2) {
			int c = 0;
			c = doneByAgent1.getSupervisorName().compareTo(doneByAgent2.getSupervisorName());
			if (c == 0) {
				c = doneByAgent2.getTotal().compareTo(doneByAgent1.getTotal());
			}
			return c;
		}
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

	public String getAgentLastName() {
		return this.agentLastName;
	}

	public void setAgentLastName(String agentLastName) {
		this.agentLastName = agentLastName;
	}

	public String getClassification() {
		return this.classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public Integer getPv1() {
		return this.pv1;
	}

	public void setPv1(Integer pv1) {
		this.pv1 = pv1;
	}

	public Integer getPv2() {
		return this.pv2;
	}

	public void setPv2(Integer pv2) {
		this.pv2 = pv2;
	}

	public Integer getPv3() {
		return this.pv3;
	}

	public void setPv3(Integer pv3) {
		this.pv3 = pv3;
	}

	public Integer getCanceled() {
		return this.canceled;
	}

	public void setCanceled(Integer canceled) {
		this.canceled = canceled;
	}

	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getAgentCode() {
		return this.agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
}
