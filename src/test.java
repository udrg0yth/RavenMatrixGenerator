
public class test {

	public static void main(String[] args) {
		double eventLat = 45.3743377,
			   eventLng = 26.5704346,
			   userLat = 44.7223444,
			   userLng = 22.8790283;
			   
		double latDistance = Math.toRadians(eventLat - userLat);
	    double lngDistance = Math.toRadians(eventLng - userLng);

	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(
	        eventLat)) * Math.cos(Math.toRadians(userLat)) * Math.sin(lngDistance / 2) * Math.sin(
	            lngDistance / 2);

	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	    double dst = (Math.round(6471 * c));
	    System.out.println(dst);
	}

}
