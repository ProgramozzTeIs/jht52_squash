package hm.sb_squash_rest.dto;

public class ChangeDto {

	private double eur;

	public ChangeDto(double eur) {
		super();
		this.eur = eur;
	}

	public double getEur() {
		return eur;
	}

	public void setEur(double eur) {
		this.eur = eur;
	}

}
