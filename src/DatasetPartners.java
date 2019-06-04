import java.util.ArrayList;

public class DatasetPartners implements Filter {

	private ArrayList<Institution> partners;
	
	
	public DatasetPartners(ArrayList<Institution> partners) {
		super();
		this.partners = partners;
	}

	@Override
	public Partner MostFrequently() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Institution> getPartners() {
		return partners;
	}

	public void setPartners(ArrayList<Institution> partners) {
		this.partners = partners;
	}

}
