package pti.sb_squash_mvc.dto;

public class LocationDto {

	private int locationId;
	private String locationName;
	
	
	public LocationDto(int locationId, String locationName) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
	}
	
	
	public int getLocationId() {
		return locationId;
	}
	
	
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	
	public String getLocationName() {
		return locationName;
	}
	
	
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	
	@Override
	public String toString() {
		return "LocationDto [locationId=" + locationId + ", locationName=" + locationName + "]";
	}
	
}
