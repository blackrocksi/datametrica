package br.com.datametrica.net.model.datatype;

public enum IntervalType {
	ZERO_TILL_ONE,
	ONE_TILL_TWO,
	TWO_TILL_THREE,
	THREE_TILL_FOUR,
	FOUR_TILL_FIVE,
	FIVE_TILL_SIX,
	SIX_TILL_SEVEN,
	SEVEN_TILL_EIGHT,
	EIGHT_TILL_NINE,
	NINE_TILL_TEN,
	TEN_TILL_ELEVEN,
	ELEVEN_TILL_TWELVE,
	TWELVE_TILL_THIRTEEN,
	THIRTEEN_TILL_FOURTEEN,
	FOURTEEN_TILL_FIFTEEN,
	FIFTENN_TILL_SIXTEEN,
	SIXTEEN_TILL_SEVENTEEN,
	SEVENTEEN_TILL_EIGHTEEN,
	EIGHTEEN_TILL_NINETEEN,
	NINETEEN_TILL_TWENTY,
	TWENTY_TILL_TWENTY_ONE,
	TWENTY_ONE_TILL_TWENTY_TWO,
	TWENTY_TWO_TILL_TWENTY_THREE,
	TWENTY_THREE_TILL_ZERO;

	private String interval;
	private int hourOfDay;

	public String toString() {
		return this.interval;
	}

	public int getHourOfDay() {
		return this.hourOfDay;
	}

	public static IntervalType fromHour(int hourOfDay) {
		for (IntervalType interval : values()) {
			if (interval.getHourOfDay() == hourOfDay) {
				return interval;
			}
		}
		return null;
	}

	public String getInterval() {
		return this.interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}
}