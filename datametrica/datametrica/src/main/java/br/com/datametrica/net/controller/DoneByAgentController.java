package br.com.datametrica.net.controller;

import static br.com.datametrica.net.db.ConnectionFactory.getConnection;
import static java.util.Collections.sort;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.datametrica.net.model.DoneByAgent;

@ManagedBean(name = "doneByAgentController")
@ViewScoped
public class DoneByAgentController implements Serializable {

	private static final long serialVersionUID = -2694087038202127830L;

	@ManagedProperty("#{doneByAgentList}")
	public List<DoneByAgent> doneByAgentList = new ArrayList<DoneByAgent>();

	private List<DoneByAgent> doneByAgentTempList = new ArrayList<DoneByAgent>();
	
	@PostConstruct
	public void init(){
		listResultActivitiesDoneByAgent();
	}
	

	public void listResultActivitiesDoneByAgent() {
		try {
			Statement stm;
			ResultSet rs = null;
			Connection con = getConnection();
			String sql = "select g.name as 'supervisor'," 
					+ "i.assigned_to as matricula,"
					+ " p.first_name as 'nome_agente', p.last_name as 'sobrenome_agente'," 
					+ " i.DispositionCode 'tipificacao'"
					+ " from genixn.dbo.interactions i " 
					+ " inner join gencfg.dbo.cfg_person p on p.[user_name]=i.assigned_to" 
					+ " inner join gencfg.dbo.cfg_agent_group ag on ag.agent_dbid = p.dbid"
					+ " inner join gencfg.dbo.cfg_group g on g.dbid = ag.group_dbid" 
					+ " where i.queue = 'iWD_Completed' AND" //iWD_Queued
					+ " g.name != '1 - Geral VT' AND" 
					+ " g.name NOT LIKE '%Outage%' AND"
					+ " g.name != 'Pequenas Cidades' AND" 
					+ " CAST(i.assigned_at AS DATE) = CAST(GETDATE() AS DATE);";
			stm = con.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				doneByAgentTempList.add(new DoneByAgent(rs.getString("supervisor"), rs.getString("matricula"), rs.getString("nome_agente"), rs.getString("sobrenome_agente"), rs.getString("tipificacao")));
			}

			arrangeList();
			
			orderList();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO Veloso - Fix error message.
		}
	}

	private void orderList() {
		sort(doneByAgentList, new DoneByAgent().new Ordenator());
		sort(doneByAgentList, new DoneByAgent().new Ordenator());
		
		
	}

	private void arrangeList() {

		doneByAgentList = new ArrayList<DoneByAgent>();
		
		for (DoneByAgent doneByAgent : doneByAgentTempList) {

			DoneByAgent consolida = new DoneByAgent();
			DoneByAgent byAgent = new DoneByAgent();

			if (doneByAgentList.isEmpty()) {
				consolida.setSupervisorName(doneByAgent.getSupervisorName());
				consolida.setAgentCode(doneByAgent.getAgentCode());
				consolida.setAgentName(doneByAgent.getAgentName());
				consolida.setAgentLastName(doneByAgent.getAgentLastName());
			} else {
				for (DoneByAgent doneByAgentTmp : doneByAgentList) {
					if (doneByAgentList.isEmpty()) {
						consolida.setSupervisorName(doneByAgent.getSupervisorName());
						consolida.setAgentCode(doneByAgent.getAgentCode());
						consolida.setAgentName(doneByAgent.getAgentName());
						consolida.setAgentLastName(doneByAgent.getAgentLastName());
					} else if (doneByAgentTmp.getAgentLastName().equals(doneByAgent.getAgentLastName()) && doneByAgentTmp.getAgentName().equals(doneByAgent.getAgentName())) {
						byAgent = doneByAgentTmp;
						consolida = byAgent;
					} else {
						consolida.setSupervisorName(doneByAgent.getSupervisorName());
						consolida.setAgentCode(doneByAgent.getAgentCode());
						consolida.setAgentName(doneByAgent.getAgentName());
						consolida.setAgentLastName(doneByAgent.getAgentLastName());
					}
				}
			}

			doneByAgentList.remove(byAgent);
			consolida.somaTipificacao(doneByAgent.getClassification());
			consolida.totalizaTipificacao();
			doneByAgentList.add(consolida);

		}
	}

	public List<DoneByAgent> getDoneByAgentList() {
		return doneByAgentList;
	}

	public void setDoneByAgentList(List<DoneByAgent> doneByAgentList) {
		this.doneByAgentList = doneByAgentList;
	}

}
