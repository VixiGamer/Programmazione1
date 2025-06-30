package cronologia;

public class Sito {
	
	private String url;
	
	Sito (String url){
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return url;
	}

}
