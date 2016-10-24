package br.com.datametrica.net.model;

import br.com.datametrica.net.model.datatype.IntervalType;
import java.io.Serializable;
import java.util.Comparator;
import org.joda.time.DateTime;

public class SqueduledByInterval implements Serializable {
	private static final long serialVersionUID = -8153214411870608435L;
	private String skill;
	private IntervalType interval;
	private int amount;
	private DateTime horario;

	public SqueduledByInterval() {
	}

	public SqueduledByInterval(String skill, DateTime horario) {
		this.skill = skill;
		this.horario = horario;
	}

	public IntervalType getInterval() {
		return this.interval;
	}

	public void setInterval(IntervalType interval) {
		this.interval = interval;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public int hashCode() {
		int result = 1;
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
		SqueduledByInterval other = (SqueduledByInterval) obj;
		if (this.interval != other.interval)
			return false;
		if (this.skill == null)
			if (other.skill != null)
				return false;
			else if (!(this.skill.equals(other.skill)))
				return false;
		return true;
	}

	public SqueduledByInterval create() {
		this.interval = IntervalType.fromHour(this.horario.getHourOfDay());
		return this;
	}

	public void sum() {
		this.amount += 1;
	}

	public class Ordenator implements Comparator<SqueduledByInterval> {
		public int compare(SqueduledByInterval amount1, SqueduledByInterval amount2) {
			int c = 0;
			c = amount1.getInterval().compareTo(amount2.getInterval());
			return c;
		}
	}
}
