package br.com.datametrica.net.controller;

import static br.com.datametrica.net.db.ConnectionFactory.getConnection;

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

import br.com.datametrica.net.model.DoneUnder60Seconds;

@ManagedBean(name = "doneUnderSixtySecondsController")
@ViewScoped
public class DoneUnderSixtySecondsController implements Serializable {

	private static final long serialVersionUID = -8826056544062590078L;

	@ManagedProperty("#{doneUnder60SecondsList}")
	public List<DoneUnder60Seconds> doneUnder60SecondsList;

	@PostConstruct
	public void init() {
		doneUnder60SecondsList = new ArrayList<DoneUnder60Seconds>();
		listDoneUnder60SecondsList();
	}

	public void listDoneUnder60SecondsList() {
		try {
			Statement stm;
			ResultSet rs = null;
			Connection con = getConnection();
			String sql = "SELECT g.name as 'supervisor', p.first_name + ' ' + p.last_name as 'nome_agente'," 
					+ " r.agente as matricula, COUNT(*) as quantidade"
					+ " FROM report_estrategia.dbo.rel_tickets_menos_60seg r" 
					+ " inner join gencfg.dbo.cfg_person p on p.user_name = r.agente"
					+ " inner join gencfg.dbo.cfg_agent_group ag on ag.agent_dbid = p.dbid" 
					+ " inner join gencfg.dbo.cfg_group g on g.dbid = ag.group_dbid" 
					+ " where g.name NOT LIKE '%1%Geral%' "
					+ " AND g.name NOT LIKE '%Outage%' " 
					+ " AND g.name != 'Pequenas Cidades'" 
					+ " AND CAST(r.inicioAtendimento AS DATE) = CAST(GETDATE() AS DATE)"
					+ " group by g.name, p.first_name, p.last_name, r.agente order by 4 desc;";
			stm = con.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				doneUnder60SecondsList.add(new DoneUnder60Seconds(rs.getString("supervisor"), rs.getString("nome_agente"), rs.getString("matricula"), rs.getInt("quantidade")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO Veloso - Fix error message.
		}

	}

	public List<DoneUnder60Seconds> getDoneUnder60SecondsList() {
		return doneUnder60SecondsList;
	}

	public void setDoneUnder60SecondsList(List<DoneUnder60Seconds> doneUnder60SecondsList) {
		this.doneUnder60SecondsList = doneUnder60SecondsList;
	}

}
