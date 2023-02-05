
public class Avenger implements Comparable <Avenger>{

	// TODO: Implement the Avenger Class
	
	private String heroAlias; 
	private String heroName;
	private int frequency; 
	
	public Avenger(String heroName, String heroAlias) {
        this.heroName = heroName;
        this.heroAlias = heroAlias;
        this.frequency = 1;
    }

    public void incrementFrequency() {
        this.frequency++;
    }

    
	
	@Override
	public int compareTo(Avenger o) {
		return this.heroAlias.compareTo(o.heroName);
	}
	
	
	
	@Override
	public String toString() { 
		String format = heroName + " aka " + heroAlias
                + " mentioned " + frequency + " time(s)";
		return format;
	}

}
