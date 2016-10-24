package br.com.datametrica.net.model;

import br.com.datametrica.net.model.datatype.CallStatusType;
import br.com.datametrica.net.model.datatype.IntervalType;
import java.io.Serializable;
import java.util.Comparator;
import org.joda.time.DateTime;

public class AmountBySkill implements Serializable {
	private static final long serialVersionUID = 4666679058554701986L;
	private String skill;
	private DateTime horario;
	private CallStatusType callStatusType;
	private IntervalType interval;
	private int inAmount;
	private int answeredAmount;

	public AmountBySkill() {
	}

	public AmountBySkill(String skill, DateTime horario, CallStatusType callStatusType) {
		this.skill = skill;
		this.horario = horario;
		this.callStatusType = callStatusType;
	}

	public AmountBySkill(String skill, CallStatusType callStatusType, IntervalType interval) {
		this.skill = skill;
		this.callStatusType = callStatusType;
		this.interval = interval;
	}

	public String getSkill() {
		return this.skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public DateTime getHorario() {
		return this.horario;
	}

	public void setHorario(DateTime horario) {
		this.horario = horario;
	}

	public CallStatusType getCallStatusType() {
		return this.callStatusType;
	}

	public void setCallStatusType(CallStatusType callStatusType) {
		this.callStatusType = callStatusType;
	}

	public IntervalType getInterval() {
		return this.interval;
	}

	public void setInterval(IntervalType interval) {
		this.interval = interval;
	}

	public int getInAmount() {
		return this.inAmount;
	}

	public void setInAmount(int inAmount) {
		this.inAmount = inAmount;
	}

	public int getAnsweredAmount() {
		return this.answeredAmount;
	}

	public void setAnsweredAmount(int answeredAmount) {
		this.answeredAmount = answeredAmount;
	}

	public int hashCode() {
		int result = 1;
		result = 31 * result + ((this.callStatusType == null) ? 0 : this.callStatusType.hashCode());
		result = 31 * result + ((this.interval == null) ? 0 : this.interval.hashCode());
		result = 31 * result + ((this.skill == null) ? 0 : this.skill.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (super.getClass() != obj.getClass())
			return false;
		AmountBySkill other = (AmountBySkill) obj;
		if (this.callStatusType != other.callStatusType)
			return false;
		if (this.interval == null)
			if (other.interval != null)
				return false;
			else if (!(this.interval.equals(other.interval)))
				return false;
		if (this.skill == null)
			if (other.skill != null)
				return false;
			else if (!(this.skill.equals(other.skill)))
				return false;
		return true;
	}

	public AmountBySkill create() {
		this.interval = IntervalType.fromHour(this.horario.getHourOfDay());
		return this;
	}

	public void sum() {
		if (CallStatusType.IN == this.callStatusType)
			this.inAmount += 1;
		else
			this.answeredAmount += 1;
	}

	public class Ordenator implements Comparator<AmountBySkill> {
		public int compare(AmountBySkill amount1, AmountBySkill amount2) {
			int c = 0;
			c = amount1.getInterval().compareTo(amount2.getInterval());
			return c;
		}
	}
}
