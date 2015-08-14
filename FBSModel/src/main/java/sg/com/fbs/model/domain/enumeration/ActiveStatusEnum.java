package sg.com.fbs.model.domain.enumeration;

public enum ActiveStatusEnum {
   YES,
   NO;
   
   @Override
   public String toString(){
	 
		switch (this) {
		case YES:
			return "Y";
		case NO:
			return "N";
		default:
			throw new IllegalStateException();
		}
	}
}
